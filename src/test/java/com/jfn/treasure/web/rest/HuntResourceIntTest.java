package com.jfn.treasure.web.rest;

import com.jfn.treasure.TreasureHuntApp;

import com.jfn.treasure.domain.Hunt;
import com.jfn.treasure.repository.HuntRepository;
import com.jfn.treasure.service.HuntService;
import com.jfn.treasure.service.dto.HuntDTO;
import com.jfn.treasure.service.mapper.HuntMapper;
import com.jfn.treasure.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


import static com.jfn.treasure.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the HuntResource REST controller.
 *
 * @see HuntResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreasureHuntApp.class)
public class HuntResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private HuntRepository huntRepository;

    @Autowired
    private HuntMapper huntMapper;

    @Autowired
    private HuntService huntService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restHuntMockMvc;

    private Hunt hunt;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HuntResource huntResource = new HuntResource(huntService);
        this.restHuntMockMvc = MockMvcBuilders.standaloneSetup(huntResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hunt createEntity() {
        Hunt hunt = new Hunt()
            .name(DEFAULT_NAME);
        return hunt;
    }

    @Before
    public void initTest() {
        huntRepository.deleteAll();
        hunt = createEntity();
    }

    @Test
    public void createHunt() throws Exception {
        int databaseSizeBeforeCreate = huntRepository.findAll().size();

        // Create the Hunt
        HuntDTO huntDTO = huntMapper.toDto(hunt);
        restHuntMockMvc.perform(post("/api/hunts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(huntDTO)))
            .andExpect(status().isCreated());

        // Validate the Hunt in the database
        List<Hunt> huntList = huntRepository.findAll();
        assertThat(huntList).hasSize(databaseSizeBeforeCreate + 1);
        Hunt testHunt = huntList.get(huntList.size() - 1);
        assertThat(testHunt.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    public void createHuntWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = huntRepository.findAll().size();

        // Create the Hunt with an existing ID
        hunt.setId("existing_id");
        HuntDTO huntDTO = huntMapper.toDto(hunt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHuntMockMvc.perform(post("/api/hunts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(huntDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hunt in the database
        List<Hunt> huntList = huntRepository.findAll();
        assertThat(huntList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = huntRepository.findAll().size();
        // set the field null
        hunt.setName(null);

        // Create the Hunt, which fails.
        HuntDTO huntDTO = huntMapper.toDto(hunt);

        restHuntMockMvc.perform(post("/api/hunts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(huntDTO)))
            .andExpect(status().isBadRequest());

        List<Hunt> huntList = huntRepository.findAll();
        assertThat(huntList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllHunts() throws Exception {
        // Initialize the database
        huntRepository.save(hunt);

        // Get all the huntList
        restHuntMockMvc.perform(get("/api/hunts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hunt.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    public void getHunt() throws Exception {
        // Initialize the database
        huntRepository.save(hunt);

        // Get the hunt
        restHuntMockMvc.perform(get("/api/hunts/{id}", hunt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hunt.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    public void getNonExistingHunt() throws Exception {
        // Get the hunt
        restHuntMockMvc.perform(get("/api/hunts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateHunt() throws Exception {
        // Initialize the database
        huntRepository.save(hunt);

        int databaseSizeBeforeUpdate = huntRepository.findAll().size();

        // Update the hunt
        Hunt updatedHunt = huntRepository.findById(hunt.getId()).get();
        updatedHunt
            .name(UPDATED_NAME);
        HuntDTO huntDTO = huntMapper.toDto(updatedHunt);

        restHuntMockMvc.perform(put("/api/hunts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(huntDTO)))
            .andExpect(status().isOk());

        // Validate the Hunt in the database
        List<Hunt> huntList = huntRepository.findAll();
        assertThat(huntList).hasSize(databaseSizeBeforeUpdate);
        Hunt testHunt = huntList.get(huntList.size() - 1);
        assertThat(testHunt.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    public void updateNonExistingHunt() throws Exception {
        int databaseSizeBeforeUpdate = huntRepository.findAll().size();

        // Create the Hunt
        HuntDTO huntDTO = huntMapper.toDto(hunt);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHuntMockMvc.perform(put("/api/hunts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(huntDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Hunt in the database
        List<Hunt> huntList = huntRepository.findAll();
        assertThat(huntList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteHunt() throws Exception {
        // Initialize the database
        huntRepository.save(hunt);

        int databaseSizeBeforeDelete = huntRepository.findAll().size();

        // Get the hunt
        restHuntMockMvc.perform(delete("/api/hunts/{id}", hunt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Hunt> huntList = huntRepository.findAll();
        assertThat(huntList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hunt.class);
        Hunt hunt1 = new Hunt();
        hunt1.setId("id1");
        Hunt hunt2 = new Hunt();
        hunt2.setId(hunt1.getId());
        assertThat(hunt1).isEqualTo(hunt2);
        hunt2.setId("id2");
        assertThat(hunt1).isNotEqualTo(hunt2);
        hunt1.setId(null);
        assertThat(hunt1).isNotEqualTo(hunt2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HuntDTO.class);
        HuntDTO huntDTO1 = new HuntDTO();
        huntDTO1.setId("id1");
        HuntDTO huntDTO2 = new HuntDTO();
        assertThat(huntDTO1).isNotEqualTo(huntDTO2);
        huntDTO2.setId(huntDTO1.getId());
        assertThat(huntDTO1).isEqualTo(huntDTO2);
        huntDTO2.setId("id2");
        assertThat(huntDTO1).isNotEqualTo(huntDTO2);
        huntDTO1.setId(null);
        assertThat(huntDTO1).isNotEqualTo(huntDTO2);
    }
}

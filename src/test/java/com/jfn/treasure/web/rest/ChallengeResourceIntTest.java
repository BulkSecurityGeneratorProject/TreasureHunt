package com.jfn.treasure.web.rest;

import com.jfn.treasure.TreasureHuntApp;

import com.jfn.treasure.domain.Challenge;
import com.jfn.treasure.repository.ChallengeRepository;
import com.jfn.treasure.service.ChallengeService;
import com.jfn.treasure.service.dto.ChallengeDTO;
import com.jfn.treasure.service.mapper.ChallengeMapper;
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

import com.jfn.treasure.domain.enumeration.ChallengeType;
import com.jfn.treasure.domain.enumeration.DifficultyType;
/**
 * Test class for the ChallengeResource REST controller.
 *
 * @see ChallengeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TreasureHuntApp.class)
public class ChallengeResourceIntTest {

    private static final String DEFAULT_CHALLENGE = "AAAAAAAAAA";
    private static final String UPDATED_CHALLENGE = "BBBBBBBBBB";

    private static final String DEFAULT_SOLUTION = "AAAAAAAAAA";
    private static final String UPDATED_SOLUTION = "BBBBBBBBBB";

    private static final ChallengeType DEFAULT_TYPE = ChallengeType.RIDDLE;
    private static final ChallengeType UPDATED_TYPE = ChallengeType.RIDDLE;

    private static final DifficultyType DEFAULT_DIFFICULTY = DifficultyType.EASY;
    private static final DifficultyType UPDATED_DIFFICULTY = DifficultyType.COMPLEX;

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private ChallengeMapper challengeMapper;

    @Autowired
    private ChallengeService challengeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    private MockMvc restChallengeMockMvc;

    private Challenge challenge;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChallengeResource challengeResource = new ChallengeResource(challengeService);
        this.restChallengeMockMvc = MockMvcBuilders.standaloneSetup(challengeResource)
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
    public static Challenge createEntity() {
        Challenge challenge = new Challenge()
            .challenge(DEFAULT_CHALLENGE)
            .solution(DEFAULT_SOLUTION)
            .type(DEFAULT_TYPE)
            .difficulty(DEFAULT_DIFFICULTY);
        return challenge;
    }

    @Before
    public void initTest() {
        challengeRepository.deleteAll();
        challenge = createEntity();
    }

    @Test
    public void createChallenge() throws Exception {
        int databaseSizeBeforeCreate = challengeRepository.findAll().size();

        // Create the Challenge
        ChallengeDTO challengeDTO = challengeMapper.toDto(challenge);
        restChallengeMockMvc.perform(post("/api/challenges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(challengeDTO)))
            .andExpect(status().isCreated());

        // Validate the Challenge in the database
        List<Challenge> challengeList = challengeRepository.findAll();
        assertThat(challengeList).hasSize(databaseSizeBeforeCreate + 1);
        Challenge testChallenge = challengeList.get(challengeList.size() - 1);
        assertThat(testChallenge.getChallenge()).isEqualTo(DEFAULT_CHALLENGE);
        assertThat(testChallenge.getSolution()).isEqualTo(DEFAULT_SOLUTION);
        assertThat(testChallenge.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testChallenge.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
    }

    @Test
    public void createChallengeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = challengeRepository.findAll().size();

        // Create the Challenge with an existing ID
        challenge.setId("existing_id");
        ChallengeDTO challengeDTO = challengeMapper.toDto(challenge);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChallengeMockMvc.perform(post("/api/challenges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(challengeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Challenge in the database
        List<Challenge> challengeList = challengeRepository.findAll();
        assertThat(challengeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void checkChallengeIsRequired() throws Exception {
        int databaseSizeBeforeTest = challengeRepository.findAll().size();
        // set the field null
        challenge.setChallenge(null);

        // Create the Challenge, which fails.
        ChallengeDTO challengeDTO = challengeMapper.toDto(challenge);

        restChallengeMockMvc.perform(post("/api/challenges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(challengeDTO)))
            .andExpect(status().isBadRequest());

        List<Challenge> challengeList = challengeRepository.findAll();
        assertThat(challengeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkSolutionIsRequired() throws Exception {
        int databaseSizeBeforeTest = challengeRepository.findAll().size();
        // set the field null
        challenge.setSolution(null);

        // Create the Challenge, which fails.
        ChallengeDTO challengeDTO = challengeMapper.toDto(challenge);

        restChallengeMockMvc.perform(post("/api/challenges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(challengeDTO)))
            .andExpect(status().isBadRequest());

        List<Challenge> challengeList = challengeRepository.findAll();
        assertThat(challengeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = challengeRepository.findAll().size();
        // set the field null
        challenge.setType(null);

        // Create the Challenge, which fails.
        ChallengeDTO challengeDTO = challengeMapper.toDto(challenge);

        restChallengeMockMvc.perform(post("/api/challenges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(challengeDTO)))
            .andExpect(status().isBadRequest());

        List<Challenge> challengeList = challengeRepository.findAll();
        assertThat(challengeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void checkDifficultyIsRequired() throws Exception {
        int databaseSizeBeforeTest = challengeRepository.findAll().size();
        // set the field null
        challenge.setDifficulty(null);

        // Create the Challenge, which fails.
        ChallengeDTO challengeDTO = challengeMapper.toDto(challenge);

        restChallengeMockMvc.perform(post("/api/challenges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(challengeDTO)))
            .andExpect(status().isBadRequest());

        List<Challenge> challengeList = challengeRepository.findAll();
        assertThat(challengeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    public void getAllChallenges() throws Exception {
        // Initialize the database
        challengeRepository.save(challenge);

        // Get all the challengeList
        restChallengeMockMvc.perform(get("/api/challenges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(challenge.getId())))
            .andExpect(jsonPath("$.[*].challenge").value(hasItem(DEFAULT_CHALLENGE.toString())))
            .andExpect(jsonPath("$.[*].solution").value(hasItem(DEFAULT_SOLUTION.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].difficulty").value(hasItem(DEFAULT_DIFFICULTY.toString())));
    }
    
    @Test
    public void getChallenge() throws Exception {
        // Initialize the database
        challengeRepository.save(challenge);

        // Get the challenge
        restChallengeMockMvc.perform(get("/api/challenges/{id}", challenge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(challenge.getId()))
            .andExpect(jsonPath("$.challenge").value(DEFAULT_CHALLENGE.toString()))
            .andExpect(jsonPath("$.solution").value(DEFAULT_SOLUTION.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.difficulty").value(DEFAULT_DIFFICULTY.toString()));
    }

    @Test
    public void getNonExistingChallenge() throws Exception {
        // Get the challenge
        restChallengeMockMvc.perform(get("/api/challenges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateChallenge() throws Exception {
        // Initialize the database
        challengeRepository.save(challenge);

        int databaseSizeBeforeUpdate = challengeRepository.findAll().size();

        // Update the challenge
        Challenge updatedChallenge = challengeRepository.findById(challenge.getId()).get();
        updatedChallenge
            .challenge(UPDATED_CHALLENGE)
            .solution(UPDATED_SOLUTION)
            .type(UPDATED_TYPE)
            .difficulty(UPDATED_DIFFICULTY);
        ChallengeDTO challengeDTO = challengeMapper.toDto(updatedChallenge);

        restChallengeMockMvc.perform(put("/api/challenges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(challengeDTO)))
            .andExpect(status().isOk());

        // Validate the Challenge in the database
        List<Challenge> challengeList = challengeRepository.findAll();
        assertThat(challengeList).hasSize(databaseSizeBeforeUpdate);
        Challenge testChallenge = challengeList.get(challengeList.size() - 1);
        assertThat(testChallenge.getChallenge()).isEqualTo(UPDATED_CHALLENGE);
        assertThat(testChallenge.getSolution()).isEqualTo(UPDATED_SOLUTION);
        assertThat(testChallenge.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testChallenge.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
    }

    @Test
    public void updateNonExistingChallenge() throws Exception {
        int databaseSizeBeforeUpdate = challengeRepository.findAll().size();

        // Create the Challenge
        ChallengeDTO challengeDTO = challengeMapper.toDto(challenge);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChallengeMockMvc.perform(put("/api/challenges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(challengeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Challenge in the database
        List<Challenge> challengeList = challengeRepository.findAll();
        assertThat(challengeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteChallenge() throws Exception {
        // Initialize the database
        challengeRepository.save(challenge);

        int databaseSizeBeforeDelete = challengeRepository.findAll().size();

        // Get the challenge
        restChallengeMockMvc.perform(delete("/api/challenges/{id}", challenge.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Challenge> challengeList = challengeRepository.findAll();
        assertThat(challengeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Challenge.class);
        Challenge challenge1 = new Challenge();
        challenge1.setId("id1");
        Challenge challenge2 = new Challenge();
        challenge2.setId(challenge1.getId());
        assertThat(challenge1).isEqualTo(challenge2);
        challenge2.setId("id2");
        assertThat(challenge1).isNotEqualTo(challenge2);
        challenge1.setId(null);
        assertThat(challenge1).isNotEqualTo(challenge2);
    }

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChallengeDTO.class);
        ChallengeDTO challengeDTO1 = new ChallengeDTO();
        challengeDTO1.setId("id1");
        ChallengeDTO challengeDTO2 = new ChallengeDTO();
        assertThat(challengeDTO1).isNotEqualTo(challengeDTO2);
        challengeDTO2.setId(challengeDTO1.getId());
        assertThat(challengeDTO1).isEqualTo(challengeDTO2);
        challengeDTO2.setId("id2");
        assertThat(challengeDTO1).isNotEqualTo(challengeDTO2);
        challengeDTO1.setId(null);
        assertThat(challengeDTO1).isNotEqualTo(challengeDTO2);
    }
}

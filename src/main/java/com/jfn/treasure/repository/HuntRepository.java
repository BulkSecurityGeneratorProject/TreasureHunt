package com.jfn.treasure.repository;

import com.jfn.treasure.domain.Hunt;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Hunt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HuntRepository extends MongoRepository<Hunt, String> {

}

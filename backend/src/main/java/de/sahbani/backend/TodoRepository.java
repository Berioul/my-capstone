package de.sahbani.backend;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends MongoRepository<Item,String> {
    List<Item> findByCategory(String category);
    void deleteByIdAndPrivatFalse (String id);
}

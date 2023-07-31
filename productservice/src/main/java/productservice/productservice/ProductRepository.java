package productservice.productservice;

import java.util.*;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, UUID> {    
    public List<Product> findByProductNameContainingOrDescriptionContaining(String txt, String txt2);
}
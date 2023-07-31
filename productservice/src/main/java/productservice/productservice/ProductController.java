package productservice.productservice;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/Product")
public class ProductController {
    @Autowired
    private ProductRepository ProductRepository;

    @GetMapping(path = "/test")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Product> findAllProducts(){
        return ProductRepository.findAll();
    }

    @GetMapping(path = "/search/{searchText}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Product> searchProducts (@PathVariable(required = true) String searchText){
        return ProductRepository.findByProductNameContainingOrDescriptionContaining(searchText, searchText);
    }

    @GetMapping(path = "/{ProductUUID}")
    @ResponseStatus(code = HttpStatus.OK)
    public Product getProduct(@PathVariable UUID ProductUUID) {
        return ProductRepository.findById(ProductUUID).orElseThrow(() -> new NoSuchElementException());
    }

    @PutMapping(path = "/{ProductUUID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String updateProduct(@PathVariable(required = true) UUID ProductUUID, @RequestBody Product Product) {
        if (!Product.getProductGuid().equals(ProductUUID)) {
            throw new RuntimeException(
                    String.format("Path itemId %s did not match body itemId %s", ProductUUID, Product.getProductGuid()));
        }
        String returnMessage = "Product Updated";
        ProductRepository.save(Product);
        return returnMessage;
    }

    @DeleteMapping(path = "/{ProductUUID}")
    @ResponseStatus(HttpStatus.OK)
    public void DeleteItem(@PathVariable(required = true) UUID ProductUUID) {
        ProductRepository.deleteById(ProductUUID);
    }

    @PostMapping(path = "")
    @ResponseStatus(code = HttpStatus.OK)
    public void createProduct(@RequestBody Product Product){
        Product.setProductGuid(UUID.randomUUID());
        ProductRepository.save(Product);
    }
}  


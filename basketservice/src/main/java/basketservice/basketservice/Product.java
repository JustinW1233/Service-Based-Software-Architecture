package basketservice.basketservice;

import java.util.*;
import org.springframework.data.annotation.*;
import java.io.Serializable;


public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private UUID productGuid;
    private String productName;
    private String description;
    private String rating;
    private int price;

    public UUID getProductGuid() {
        return productGuid;
    }
    public void setProductGuid(UUID productGuid) {
        this.productGuid = productGuid;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}

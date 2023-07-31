package basketservice.basketservice;

import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/basket")
public class BasketController{

    @Autowired
    private RedisTemplate<String, Basket> redisTemplate;

    @PostMapping(path = "/{basketId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Basket addSingleProductToBasket(@PathVariable String basketId, @RequestBody Product product) {
        Basket basket = null;
        product.setProductGuid(UUID.randomUUID());

        if ("new".equals(basketId)) {
            basket = new Basket(UUID.randomUUID().toString());
        } else {
            basket = redisTemplate.opsForValue().get(basketId);
        }

        basket.getProducts().add(product);
        redisTemplate.opsForValue().set(basket.getBasketGuid(), basket);

        return basket;
    }

    @GetMapping(path = "/{basketId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Basket getBasket(@PathVariable String basketId) {
        Basket basket = redisTemplate.opsForValue().get(basketId);
        return basket;
    }

    @DeleteMapping(path = "/{basketId}/{productUUID}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteProdcutFromBasket(@PathVariable String basketId, @PathVariable UUID productUUID) {
        Basket basket = redisTemplate.opsForValue().get(basketId);

        basket.setproducts(
            basket.getProducts().stream().filter(p -> !p.getProductGuid().equals(productUUID)).collect(Collectors.toList()));

        redisTemplate.opsForValue().set(basketId, basket);
    }

    @DeleteMapping(path = "/{basketId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteBasket(@PathVariable String basketId) {
        redisTemplate.delete(basketId);
    }

}
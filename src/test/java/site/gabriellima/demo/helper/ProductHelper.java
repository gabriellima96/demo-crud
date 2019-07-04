package site.gabriellima.demo.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import site.gabriellima.demo.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ProductHelper {

  private static final Random RANDOM = new Random();

  public static Product newProduct() {
    return new Product(RANDOM.nextLong(), UUID.randomUUID().toString(), RANDOM.nextDouble());
  }

  public static Long generateId() {
    return RANDOM.nextLong();
  }

  public static Page<Product> newProductPage() {
    List<Product> productList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      productList.add(newProduct());
    }

    return new PageImpl<>(productList);
  }
}

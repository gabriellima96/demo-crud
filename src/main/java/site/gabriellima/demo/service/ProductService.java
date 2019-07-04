package site.gabriellima.demo.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import site.gabriellima.demo.model.Product;
import site.gabriellima.demo.repository.ProductRepository;
import site.gabriellima.demo.service.exception.ObjectNotFoundException;

import static java.util.Objects.requireNonNull;

@Service
public class ProductService {

  private static final String PRODUCT_NOT_FOUND = "Product %s not found";

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product save(Product product) {
    product.setId(null);
    requireNonNull(product.getDescription());
    return productRepository.save(product);
  }

  public Product findById(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new ObjectNotFoundException(String.format(PRODUCT_NOT_FOUND, id)));
  }

  public void deleteById(Long id) {
    Product product = findById(id);
    productRepository.delete(product);
  }

  public Product update(Product updatedProduct) {
    Product product = findById(updatedProduct.getId());

    BeanUtils.copyProperties(updatedProduct, product, "id");

    return productRepository.save(product);
  }

  public Page<Product> findAllBySearch(
      Long id, String description, Double price, Pageable pageable) {

    return productRepository.findAll(createExample(id, description, price), pageable);
  }

  public Example<Product> createExample(Long id, String description, Double price) {
    ExampleMatcher exampleMatcher =
        ExampleMatcher.matchingAll()
            .withIgnoreNullValues()
            .withMatcher("id", m -> m.exact())
            .withMatcher("description", m -> m.contains())
            .withMatcher("price", m -> m.exact());

    return Example.of(new Product(id, description, price), exampleMatcher);
  }
}

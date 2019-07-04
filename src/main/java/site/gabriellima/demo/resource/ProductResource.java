package site.gabriellima.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.gabriellima.demo.model.Product;
import site.gabriellima.demo.service.ProductService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductResource {

  @Autowired private ProductService productService;

  @GetMapping
  public ResponseEntity<Page<Product>> findAll(
      @RequestParam(value = "id", required = false) Long id,
      @RequestParam(value = "description", required = false) String description,
      @RequestParam(value = "price", required = false) Double price,
      Pageable pageable) {
    return ResponseEntity.ok(productService.findAllBySearch(id, description, price, pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(productService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProduct(
      @Valid @RequestBody Product product, @PathVariable("id") Long id) {
    product.setId(id);
    productService.update(product);
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
    productService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}

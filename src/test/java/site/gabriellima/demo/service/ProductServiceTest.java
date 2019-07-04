package site.gabriellima.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import site.gabriellima.demo.helper.ProductHelper;
import site.gabriellima.demo.model.Product;
import site.gabriellima.demo.repository.ProductRepository;
import site.gabriellima.demo.service.exception.ObjectNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {

  @Mock private ProductRepository productRepository;

  @InjectMocks private ProductService productService;

  @Test
  public void shouldCreateProduct() {
    final Product product = ProductHelper.newProduct();
    product.setId(null);

    when(productRepository.save(eq(product))).thenReturn(product);

    final Product productSaved = productService.save(product);

    assertEquals(productSaved, product);
  }

  @Test
  public void shouldFindProductById() {
    final Product product = ProductHelper.newProduct();

    when(productRepository.findById(eq(product.getId()))).thenReturn(Optional.of(product));

    final Product productFound = productService.findById(product.getId());

    assertEquals(productFound, product);
  }

  @Test(expected = ObjectNotFoundException.class)
  public void shouldNotFoundProductById() {
    final Product product = ProductHelper.newProduct();
    final Long randomId = ProductHelper.generateId();

    when(productRepository.findById(eq(product.getId()))).thenReturn(Optional.of(product));

    productService.findById(randomId);
  }

  @Test
  public void shouldDeleteProductById() {
    final Product product = ProductHelper.newProduct();

    when(productRepository.findById(eq(product.getId()))).thenReturn(Optional.of(product));

    productService.deleteById(product.getId());
  }

  @Test(expected = ObjectNotFoundException.class)
  public void shouldDeleteNotFoundProductById() {
    final Product product = ProductHelper.newProduct();
    final Long generateId = ProductHelper.generateId();

    when(productRepository.findById(eq(product.getId()))).thenReturn(Optional.of(product));

    productService.deleteById(generateId);
  }

  @Test(expected = ObjectNotFoundException.class)
  public void shouldUpdateNotFoundProductById() {
    final Product product = ProductHelper.newProduct();
    final Product updatedProduct = ProductHelper.newProduct();
    final Long generateId = ProductHelper.generateId();
    updatedProduct.setId(generateId);

    when(productRepository.findById(eq(product.getId()))).thenReturn(Optional.of(product));
    when(productRepository.save(eq(updatedProduct))).thenReturn(updatedProduct);

    productService.update(updatedProduct);
  }

  @Test
  public void shouldUpdateProductById() {
    final Product product = ProductHelper.newProduct();
    final Product updatedProduct = ProductHelper.newProduct();
    updatedProduct.setId(product.getId());

    when(productRepository.findById(eq(product.getId()))).thenReturn(Optional.of(product));
    when(productRepository.save(eq(updatedProduct))).thenReturn(updatedProduct);

    Product productSavedAndUpdated = productService.update(updatedProduct);

    assertEquals(updatedProduct, productSavedAndUpdated);
  }

  @Test
  public void shouldFindAllProductByPage() {
    final Page<Product> productPage = ProductHelper.newProductPage();

    when(productRepository.findAll(
            eq(productService.createExample(null, null, null)), eq(PageRequest.of(1, 5))))
        .thenReturn(productPage);

    Page<Product> products = productService.findAllBySearch(null, null, null, PageRequest.of(1, 5));

    assertEquals(productPage, products);
  }
}

package ProductService.Service;

import ProductService.Model.ProductEntity;
import ProductService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity getProductById(int id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public void SaveProduct(ProductEntity product)
    {
        productRepository.save(product);
    }

    public String DeleteProduct(int id) {
        if(getProductById(id) != null)
        {
            productRepository.deleteById(id);
            return "Product deleted ...";
        }
        else
        {
            return "Product not found !!!";
        }
    }
}

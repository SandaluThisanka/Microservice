package ProductService.Controller;

import ProductService.Model.ProductEntity;
import ProductService.Model.CategoryEntity;
import ProductService.Repository.CategoryRepository;
import ProductService.Service.ProductService;
import ProductService.Service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/getproducts")
    public List<ProductEntity> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/getproduct/{id}")
    public ProductEntity getProduct(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping("/newproduct")
    public void addProduct(@RequestPart("product") ProductEntity product){

        productService.SaveProduct(product);
    }

    @DeleteMapping("/deleteproduct/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.DeleteProduct(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody CategoryEntity category) {
        categoryService.saveCategory(category);
        return ResponseEntity.ok("Category added successfully!");
    }


    @GetMapping("/all")
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable int id) {
        CategoryEntity category = categoryService.getCategoryById(id);
        if(category != null) {
            return ResponseEntity.ok(category);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        String result = categoryService.deleteCategory(id);
        if(result.equals("Category deleted successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}
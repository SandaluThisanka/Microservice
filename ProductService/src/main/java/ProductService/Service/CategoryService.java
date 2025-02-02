package ProductService.Service;

import ProductService.Model.CategoryEntity;
import ProductService.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity saveCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    public CategoryEntity getCategoryById(int id) {
        Optional<CategoryEntity> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    public String deleteCategory(int id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return "Category deleted successfully";
        }
        return "Category not found";
    }
}
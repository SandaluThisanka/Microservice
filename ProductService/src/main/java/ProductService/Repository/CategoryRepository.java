package ProductService.Repository;

import ProductService.Model.CategoryEntity;
import ProductService.Model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    }



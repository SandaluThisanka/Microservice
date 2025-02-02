package ProductService.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Category name is required")
    private String name;


    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean isActive = true;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public boolean isActive() { return isActive; }
    public void setActive(boolean active) {
        this.isActive = active;  // Fix: Use "this" to correctly assign the value
    }
}
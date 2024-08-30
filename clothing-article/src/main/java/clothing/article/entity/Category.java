package clothing.article.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	private String categoryName;
	
	private String categoryPosition;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true) // Causes any Clothing connected to be deleted should a Category be deleted
	private Set<Clothing> clothes = new HashSet<Clothing>(); // Connects the class Category to the class Clothing
}

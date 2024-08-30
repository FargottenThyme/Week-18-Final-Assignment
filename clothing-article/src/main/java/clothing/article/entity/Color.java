package clothing.article.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long colorId;
	
	private String colorName;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude // Prevents recursion
	@ManyToMany(mappedBy = "colors")
	private Set<Clothing> clothes = new HashSet<Clothing>(); // Connects the class Color to the class Clothing
}

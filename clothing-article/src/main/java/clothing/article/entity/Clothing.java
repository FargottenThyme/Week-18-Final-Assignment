package clothing.article.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Clothing {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clothingId;

	@EqualsAndHashCode.Exclude
	private String clothingName;

	@EqualsAndHashCode.Exclude
	private String clothingMaterial;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude // Prevents recursion from entity class "Category"
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude // Prevents recursion from entity class "Color"
	@ManyToMany(cascade = CascadeType.PERSIST) // Causes the row Color to stay should a Clothing row be deleted
	@JoinTable(name = "clothing_color", joinColumns = @JoinColumn(name = "clothing_id"), inverseJoinColumns = @JoinColumn(name = "color_id"))
	private Set<Color> colors = new HashSet<Color>(); // Connects the class Clothing to the class Color
}

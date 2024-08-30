package clothing.article.controller.model;

import java.util.HashSet;
import java.util.Set;

import clothing.article.entity.Category;
import clothing.article.entity.Clothing;
import clothing.article.entity.Color;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryData {
	
	private Long categoryId;
	private String categoryName;
	private String categoryPosition;
	private Set<ClothingData> clothes = new HashSet<>();

	public CategoryData(Category category) {
		this.categoryId = category.getCategoryId();
		this.categoryName = category.getCategoryName();
		this.categoryPosition = category.getCategoryPosition();
		
		for(Clothing clothingArticle : category.getClothes()) {
			this.clothes.add(new ClothingData(clothingArticle));
		}
	}
	
	public CategoryData(Long categoryId, String categoryName, String categoryPosition) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryPosition = categoryPosition;
	}
	
	public Category toCategory() {
		Category category = new Category();
		
		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);
		category.setCategoryPosition(categoryPosition);
		
		for (ClothingData clothingData : clothes) {
			category.getClothes().add(clothingData.toClothing());
		}
		
		return category;
	}
	
	@Data
	@NoArgsConstructor
	public static class ClothingData {
		
		private Long clothingId;
		private String clothingName;
		private String clothingMaterial;
		private Set<ColorData> colors = new HashSet<>();
		
		public ClothingData(Clothing clothing) {
			this.clothingId = clothing.getClothingId();
			this.clothingName = clothing.getClothingName();
			this.clothingMaterial = clothing.getClothingMaterial();
			
			for(Color color : clothing.getColors()) {
				this.colors.add(new ColorData(color));
			}
		}
		
		public Clothing toClothing() {
			
			Clothing clothing = new Clothing();
			
			clothing.setClothingId(clothingId);
			clothing.setClothingName(clothingName);
			clothing.setClothingMaterial(clothingMaterial);
			
			for(ColorData colorData : colors) {
				clothing.getColors().add(colorData.toColor());
			}
			
			return clothing;
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class ColorData {
		
		private Long colorId;
		private String colorName;
		
		public ColorData(Color color) {
			this.colorId = color.getColorId();
			this.colorName = color.getColorName();
		}
		
		public Color toColor() {
			
			Color color = new Color();
			
			color.setColorId(colorId);
			color.setColorName(colorName);
			
			return color;
		}
	}
}

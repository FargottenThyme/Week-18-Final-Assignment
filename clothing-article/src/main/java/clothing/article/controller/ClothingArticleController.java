package clothing.article.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import clothing.article.controller.model.CategoryData;
import clothing.article.controller.model.CategoryData.ClothingData;
import clothing.article.controller.model.CategoryData.ColorData;
import clothing.article.service.ClothingArticleService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/clothing_article")
@Slf4j
public class ClothingArticleController {
	
	@Autowired
	private ClothingArticleService clothingArticleService;

	@PostMapping("/category")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CategoryData createCategory(@RequestBody CategoryData categoryData) {
		log.info("Creating Category {}", categoryData);
		return clothingArticleService.saveCategory(categoryData);
	}

	@PutMapping("/category/{categoryId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CategoryData updateCategory(@PathVariable Long categoryId, @RequestBody CategoryData categoryData) {
		categoryData.setCategoryId(categoryId);
		log.info("Updating category with ID={}", categoryId);
		return clothingArticleService.saveCategory(categoryData);
	}

	@GetMapping("/category/{categoryId}")
	@ResponseStatus(code = HttpStatus.OK)
	public CategoryData showCatergoryById(@PathVariable Long categoryId) {
		log.info("Showing category with ID={}", categoryId);
		return clothingArticleService.showCategoryById(categoryId);
	}

	@GetMapping("/category")
	@ResponseStatus(code = HttpStatus.OK)
	public List<CategoryData> showAllCategories() {
		log.info("Showing All Categories");
		return clothingArticleService.showAllCategories();
	}

	@DeleteMapping("/category/{categoryId}")
	public Map<String, String> deleteCategoryById(@PathVariable Long categoryId) {
		log.info("Deleting category with ID={}", categoryId);

		clothingArticleService.deleteCategoryById(categoryId);

		return Map.of("Message", "Deletion of category with ID=" + categoryId + " was successful");
	}

	@DeleteMapping("/category")
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public void deleteAllCategories() {
		log.info("Attempting to delete all categories");
		throw new UnsupportedOperationException("Deleting all categories is not allowed!");
	}
/* End of Category focused methods
 * ==============================================================================================================================
 */
	@PostMapping("/category/{categoryId}/clothing")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClothingData createClothing(@PathVariable Long categoryId, @RequestBody ClothingData clothingData) {
		log.info("Creating clothing within category with ID={}", categoryId);
		return clothingArticleService.saveClothing(categoryId, clothingData);
	}
	
	@PutMapping("/category/{categoryId}/clothing/{clothingId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClothingData updateClothingById(@PathVariable Long categoryId, @PathVariable Long clothingId, @RequestBody ClothingData clothingData) {
		clothingData.setClothingId(clothingId);
		log.info("Updating clothing with ID={}, within category with ID={}", clothingId, categoryId);
		return clothingArticleService.saveClothing(categoryId, clothingData);
	}
	
	@GetMapping("/category/{categoryId}/clothing/{clothingId}")
	@ResponseStatus(code = HttpStatus.OK)
	public ClothingData showClothingById(@PathVariable Long categoryId, @PathVariable Long clothingId) {
		log.info("Showing Clothing with ID={} within Category with ID={}", clothingId, categoryId);
		return clothingArticleService.showClothingById(clothingId);
	}
	
	@GetMapping("/category/{categoryId}/clothing")
	@ResponseStatus(code = HttpStatus.OK)
	public CategoryData showClothingWithinCategory(@PathVariable Long categoryId) {
		log.info("Showing all clothing options within category with ID={}", categoryId);
		return clothingArticleService.showClothingWithinCategory(categoryId);
	}
	
	@DeleteMapping("/category/{categoryId}/clothing/{clothingId}")
	public Map<String, String> deleteClothingById(@PathVariable Long categoryId, @PathVariable Long clothingId) {
		log.info("Deleting clothing with ID={} within category with ID={}", clothingId, categoryId);
		
		clothingArticleService.deleteClothingById(clothingId);
		
		return Map.of("Message", "Deletion of clothing with ID=" + clothingId + " was successful");
	}
	
	@DeleteMapping("/category/{categoryId}/clothing")
	@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
	public void deleteAllClothingByCategoryId(@PathVariable Long categoryId) {
		log.info("Attempting to delete all clothing within category with ID={}", categoryId);
		throw new UnsupportedOperationException("Deleting all clothing within a category is not allowed!");
	}
/* End of Clothing focused methods
 * ==============================================================================================================================	
 */
	@PostMapping("/category/{categoryId}/clothing/{clothingId}/color/{colorId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ColorData assignColorToClothingById(@PathVariable Long categoryId, @PathVariable Long clothingId, @PathVariable Long colorId) {
		log.info("Assigning Color with ID={} to Clothing with ID={}", colorId, clothingId);
		return clothingArticleService.assignColorToClothingByID(clothingId, colorId);
	}
	
	@GetMapping("/color")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ColorData> showAllColors() {
		log.info("Showing all colors available");
		return clothingArticleService.showAllColors();
	}
}

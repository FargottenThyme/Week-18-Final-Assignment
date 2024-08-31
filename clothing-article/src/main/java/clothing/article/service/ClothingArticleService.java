package clothing.article.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clothing.article.controller.model.CategoryData;
import clothing.article.controller.model.CategoryData.ClothingData;
import clothing.article.controller.model.CategoryData.ColorData;
import clothing.article.dao.CategoryDao;
import clothing.article.dao.ClothingDao;
import clothing.article.dao.ColorDao;
import clothing.article.entity.Category;
import clothing.article.entity.Clothing;
import clothing.article.entity.Color;

@Service
public class ClothingArticleService {

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ClothingDao clothingDao;

	@Autowired
	private ColorDao colorDao;

	@Transactional(readOnly = false)
	public CategoryData saveCategory(CategoryData categoryData) {
		Long categoryId = categoryData.getCategoryId();
		Category category = findOrCreateCategory(categoryId);

		setFieldsInCategory(category, categoryData);
		return new CategoryData(categoryDao.save(category));
	}

	private void setFieldsInCategory(Category category, CategoryData categoryData) {
		category.setCategoryName(categoryData.getCategoryName());
		category.setCategoryPosition(categoryData.getCategoryPosition());
	}

	private Category findOrCreateCategory(Long categoryId) {
		Category category;

		if (Objects.isNull(categoryId)) {
			category = new Category();
		} else {
			category = findCategoryById(categoryId);
		}

		return category;
	}

	private Category findCategoryById(Long categoryId) {
		return categoryDao.findById(categoryId)
				.orElseThrow(() -> new NoSuchElementException("Category with ID=" + categoryId + " was not found."));
	}

	@Transactional(readOnly = true)
	public CategoryData showCategoryById(Long categoryId) {
		CategoryData categoryData = new CategoryData(findCategoryById(categoryId));
		categoryData.getClothes().clear();
		return categoryData;
	}
	
	@Transactional(readOnly = true)
	public List<CategoryData> showAllCategories() {
		List<CategoryData> result = new LinkedList<CategoryData>();

		for (Category category : categoryDao.findAll()) {
			CategoryData categoryData = new CategoryData(category);

			categoryData.getClothes().clear();

			result.add(categoryData);
		}

		return result;
	}
	
	@Transactional(readOnly = false)
	public void deleteCategoryById(Long categoryId) {
		Category category = findCategoryById(categoryId);
		categoryDao.delete(category);
	}

	@Transactional(readOnly = false)
	public ClothingData saveClothing(Long categoryId, ClothingData clothingData) {
		Category category = findCategoryById(categoryId);
		Clothing clothing = findOrCreateClothing(clothingData.getClothingId());

		setFieldsInClothing(clothing, clothingData);
		clothing.setCategory(category);
		category.getClothes().add(clothing);
		
		Clothing dbClothing = clothingDao.save(clothing);

		return new ClothingData(dbClothing);
	}

	private void setFieldsInClothing(Clothing clothing, ClothingData clothingData) {
		clothing.setClothingName(clothingData.getClothingName());
		clothing.setClothingMaterial(clothingData.getClothingMaterial());
	}

	private Clothing findOrCreateClothing(Long clothingId) {
		Clothing clothing;

		if (Objects.isNull(clothingId)) {
			clothing = new Clothing();
		} else {
			clothing = findClothingById(clothingId);
		}

		return clothing;
	}

	private Clothing findClothingById(Long clothingId) {
		return clothingDao.findById(clothingId)
				.orElseThrow(() -> new NoSuchElementException("Clothing with ID=" + clothingId + " does not exist."));
	}

	@Transactional(readOnly = true)
	public ClothingData showClothingById(Long clothingId) {
		return new ClothingData(findClothingById(clothingId));

	}

	@Transactional(readOnly = true)
	public CategoryData showClothingWithinCategory(Long categoryId) {
		return new CategoryData(findCategoryById(categoryId));
	}

	@Transactional(readOnly = false)
	public void deleteClothingById(Long clothingId) {
		Clothing clothing = findClothingById(clothingId);
		clothingDao.delete(clothing);
	}

	@Transactional(readOnly = false)
	public ColorData assignColorToClothingByID(Long clothingId, Long colorId) {
		Clothing clothing = findClothingById(clothingId);
		Color color = findColorById(colorId);

		color.getClothes().add(clothing);
		clothing.getColors().add(color);

		return new ColorData(colorDao.save(color));
	}

	private Color findColorById(Long colorId) {
		return colorDao.findById(colorId)
				.orElseThrow(() -> new NoSuchElementException("Color with ID=" + colorId + " does not exist."));
	}

	@Transactional(readOnly = true)
	public List<ColorData> showAllColors() {
		List<ColorData> result = new LinkedList<>();

		for (Color color : colorDao.findAll()) {
			ColorData colorData = new ColorData(color);
			result.add(colorData);
		}

		return result;
	}
}

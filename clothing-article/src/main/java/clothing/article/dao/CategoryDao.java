package clothing.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import clothing.article.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {
	
}

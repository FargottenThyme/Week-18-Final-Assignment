package clothing.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import clothing.article.entity.Clothing;

public interface ClothingDao extends JpaRepository<Clothing, Long> {

}

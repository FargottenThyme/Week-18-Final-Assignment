package clothing.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import clothing.article.entity.Color;

public interface ColorDao extends JpaRepository<Color, Long> {

}

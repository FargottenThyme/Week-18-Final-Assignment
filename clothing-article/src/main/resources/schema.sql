DROP TABLE IF EXISTS clothing_color;
DROP TABLE IF EXISTS color;
DROP TABLE IF EXISTS clothing;
DROP TABLE IF EXISTS category;

CREATE TABLE category (
	category_id int NOT NULL AUTO_INCREMENT,
	category_name varchar(64) NOT NULL,
	category_position varchar(64) NOT NULL,
	PRIMARY KEY (category_id)
);

CREATE TABLE clothing (
	clothing_id int NOT NULL AUTO_INCREMENT,
	category_id int NOT NULL,
	clothing_name varchar(64) NOT NULL,
	clothing_material varchar(64),
	PRIMARY KEY (clothing_id),
	FOREIGN KEY (category_id) REFERENCES category (category_id) ON DELETE CASCADE
);

CREATE TABLE color (
	color_id int NOT NULL AUTO_INCREMENT,
	color_name varchar(64) NOT NULL,
	PRIMARY KEY (color_id)
);

CREATE TABLE clothing_color (
	clothing_id int NOT NULL,
	color_id int NOT NULL,
	FOREIGN KEY (clothing_id) REFERENCES clothing (clothing_id) ON DELETE CASCADE,
	FOREIGN KEY (color_id) REFERENCES color (color_id) ON DELETE CASCADE
);
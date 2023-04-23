CREATE TABLE cocktail_shopping_list (
  id VARCHAR(100)  PRIMARY KEY,
  shopping_list_id VARCHAR(100)  REFERENCES shopping_list(id),
  cocktail_id VARCHAR(100) REFERENCES cocktail(id),
  CONSTRAINT cocktail_shopping_list_uq UNIQUE (shopping_list_id, cocktail_id)
);

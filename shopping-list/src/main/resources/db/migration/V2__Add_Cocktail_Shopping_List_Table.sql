CREATE TABLE cocktail_shopping_list (
  id UUID PRIMARY KEY,
  shopping_list_id UUID REFERENCES shopping_list(id),
  cocktail_id UUID REFERENCES cocktail(id),
  CONSTRAINT cocktail_shopping_list_uq UNIQUE (shopping_list_id, cocktail_id)
);
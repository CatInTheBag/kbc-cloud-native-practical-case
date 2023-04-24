create table IF NOT EXISTS SHOPPING_LIST
(
    id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

create table IF NOT EXISTS COCKTAIL
(
    id VARCHAR(100)  PRIMARY KEY,
    id_drink VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    glass VARCHAR(100) NOT NULL,
    image VARCHAR(100),
    instructions VARCHAR(100),
    ingredients VARCHAR(100) NOT NULL
);
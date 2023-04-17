delete from Ingredient_Ref;
delete from Taco;
delete from Taco_Order;
delete from Ingredient;

insert into Ingredient (id, name, type) VALUES ('FLTO', 'Flour Tortilla', 'WRAP');
insert into Ingredient (id, name, type) VALUES ('COTO', 'Corn Tortilla', 'WRAP');
insert into Ingredient (id, name, type) VALUES ('GRBF', 'Ground Beef', 'PROTEIN');
insert into Ingredient (id, name, type) VALUES ('CARN', 'Carnitas', 'PROTEIN');
insert into Ingredient (id, name, type) VALUES ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into Ingredient (id, name, type) VALUES ('LETC', 'Lettuce', 'VEGGIES');
insert into Ingredient (id, name, type) VALUES ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type) VALUES ('JACK', 'Monterrey Jack', 'CHEESE');
insert into Ingredient (id, name, type) VALUES ('SLSA', 'Salsa', 'SAUCE');
insert into Ingredient (id, name, type) VALUES ('SRCR', 'Sour Cream', 'SAUCE');

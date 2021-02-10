use theblog;

insert into Category(categoryName) value ('Football');
insert into Category(categoryName) value ('Soccer');
insert into Category(categoryName) value ('Basketball');
insert into Category(categoryName) value ('Hockey');
insert into Category(categoryName) value ('Boxing');

insert into ItemCategory(FK_itemId , FK_categoryId) value (1, 1); 
insert into ItemCategory(FK_itemId , FK_categoryId) value (2, 3); 
insert into ItemCategory(FK_itemId , FK_categoryId) value (3, 1); 
insert into ItemCategory(FK_itemId , FK_categoryId) value (4, 5); 
insert into ItemCategory(FK_itemId , FK_categoryId) value (5, 2); 


insert into Item (itemName, description, featuredItem, imageTitle) value ('#SuperBowl','CHAMPIONSHIP game its the FINAL', true,'superBowl');
insert into Item (itemName, description, featuredItem, imageTitle) value ('#WorldCup','CHAMPIONSHIP game its the FINAL', true,'cup Game');
insert into Item (itemName, description, featuredItem, imageTitle) value ('#Playoffs','game to the FINAL', true,'playoff games');
insert into Item (itemName, description, featuredItem, imageTitle) value ('#Hockey','rough game played in the cold arena', true,'hockey life');
insert into Item (itemName, description, featuredItem, imageTitle) value ('#FightNight','its Fight time to see whos the best', true,'Fight Life');
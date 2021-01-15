create database blogdb;

use blogdb;

create table `User`
(
user_id int not null auto_increment,
user_name varchar(20) not null unique,
password varchar(100) not null,
enabled boolean not null,
primary key (user_id)
)engine=InnoDB default charset=latin1 auto_increment=1;


create table Tag
(
tag_id int not null auto_increment,
tag_name varchar(25) not null,
primary key(tag_id)
)engine=InnoDB default charset=latin1 auto_increment=1;

create table `Role`
(
role_id int not null auto_increment,
role_name varchar(45) not null,
primary key(role_id)
)engine=InnoDB default charset=latin1 auto_increment=1;

create table Category
(
category_id int not null auto_increment,
category_name varchar(45) not null,
primary key(category_id)
)engine=InnoDB default charset=latin1 auto_increment=1;

create table Post
(
post_id int not null auto_increment,
title varchar(100) not null,
post_date datetime not null,
content longtext not null,
state tinyint not null,
reference_id int,
user_id int not null,
category_id int not null,
primary key (post_id),
foreign key (user_id) references `User`(user_id),
foreign key (category_id) references Category(category_id)
)engine=InnoDB default charset=latin1 auto_increment=1;

-- Bridge Tables

create table User_Role
(
user_id int not null,
role_id int not null,
primary key(user_id, role_id),
foreign key(user_id) references `User`(user_id),
foreign key(role_id) references `Role`(role_id)
)engine=InnoDB default charset=latin1 auto_increment=1;

create table Post_Tag
(
post_id int not null,
tag_id int not null,
primary key(post_id, tag_id),
foreign key(post_id) references Post(post_id),
foreign key(tag_id) references Tag(tag_id)
)engine=InnoDB default charset=latin1 auto_increment=1;

insert into `User` (user_id,user_name,password,enabled)
values(1,'admin','password',true),
(2,'user','password',true);

insert into Category(category_name)
values ('database');

select * from Category;

insert into Post (title,post_date,content,state,reference_id,user_id,category_id)
values ('databse post','2021-01-13','this is a post printed from the database',1,1,1,7);

insert into Post (title,post_date,content,state,reference_id,user_id,category_id)
values ('2nd databse post','2021-01-13','this is another post printed from the database',1,1,1,6);

insert into Post (title,post_date,content,state,reference_id,user_id,category_id)
values ('3rd databse post','2021-01-13','this is the 3rd a post printed from the database',1,1,1,5);
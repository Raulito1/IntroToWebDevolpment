-- create database hotel_reservation;

use hotel_reservation;


create table address 
(
address_id int not null auto_increment,
address varchar(100) not null,
city varchar(50) not null,
state char(2) not null,
zip_code int(5) not null,
primary key(address_id)
);

create table guest
(
guest_id int not null auto_increment,
first_name varchar(30) not null,
last_name varchar(30) not null,
email varchar(50) not null,
phone_number int(10) not null,
address_id int,
primary key(guest_id),
foreign key(address_id) references address(address_id)
);

create table amenity
(
amenity_id int not null auto_increment,
amenity_Type varchar(50),
primary key(amenity_id)
); 

create table promo
(
promo_id int not null auto_increment,
promo_type char not null,
primary key(promo_id)
);

create table room
(
room_id int not null auto_increment,
room_type varchar(20) not null,
handicap_accessible boolean not null,
standard_occupancy int not null,
max_occupancy int not null,
base_price decimal(5,2) not null,
extra_person smallint null,
amenity_id int,
guest_id int,
primary key(room_id),
foreign key (guest_id) references guest(guest_id),
foreign key (amenity_id) references amenity(amenity_id)
);

create table reservation
(
reservation_id int not null auto_increment,
num_adult int not null,
num_children int not null,
start_date date not null,
end_date date not null,
room_id int ,
guest_id int,
promo_id int,
primary key(reservation_id),
foreign key (room_id) references room(room_id),
foreign key (guest_id) references guest(guest_id),
foreign key (promo_id) references promo(promo_id)
);

-- -- -- -- Bridge tables -- -- -- -- 
-- guest_reservation
-- amenity_room
-- room_reservation

create table guest_reservation
(
guest_id int not null,
reservation_id int not null,
primary key(guest_id, reservation_id)
);
-- constraint for guest_reservation bridge table
alter table guest_reservation
add constraint fk_guest_reservation_guest
foreign key (guest_id) references guest(guest_id);

alter table guest_reservation
add constraint fk_guest_reservation_reservation
foreign key (reservation_id) references reservation(reservation_id);

create table amenity_room
(
amenity_id int not null,
room_id int not null,
primary key(amenity_id, room_id)
);
-- Constraints for amenity_room bridge table
alter table amenity_room
add constraint fk_amenity_room_amenity
foreign key (amenity_id) references amenity(amenity_id);

alter table amenity_room
add constraint fk_amenity_room_room
foreign key (room_id) references room(room_id);

create table room_reservation
(
room_id int not null,
reservation_id int not null,
primary key(room_id, reservation_id)
);
-- constraints for room_reservation bridge table
alter table room_reservation
add constraint fk_room_reservation_room
foreign key (room_id) references room(room_id);

alter table room_reservation
add constraint fk_room_reservation_reservation
foreign key (reservation_id) references reservation(reservation_id);

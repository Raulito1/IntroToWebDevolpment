-- create database SuperHeroSighting;

use SuperHeroSighting;

create table persons
(
`personId` int not null auto_increment,
`name` varchar(45) not null,
`description` varchar(200),
primary key(`personId`)
)engine=InnoDB default charset=latin1 auto_increment=1;

create table locations
(
`locationId` int not null auto_increment,
`name` varchar(45) not null,
`description` varchar(200) not null,
`street` varchar(45) not null,
`city` varchar(45) not null,
`state` varchar(45) not null,
`country` varchar(45) not null,
`latitude` decimal(11,8),
`longitude` decimal(11,8),
primary key(`locationId`)
)engine=InnoDB default charset=latin1 auto_increment=1;

create table organizations
(
`organizationId` int not null auto_increment,
`name` varchar(45) not null,
`description` varchar(45) not null,
`phone` varchar(45) not null,
`email` varchar(45) null,
`locationId` int,
primary key(`organizationId`),
foreign key(`locationId`) references locations(`locationId`)
);

create table superpowers
(
`superpowerId` int not null auto_increment,
`name` varchar(45) not null,
`description` varchar(45) not null,
primary key(`superpowerId`)
)engine=InnoDB default charset=latin1 auto_increment=1;

create table sightings
(
`sightingId` int not null auto_increment,
`date` date not null,
`locationId` int not null,
primary key(`sightingId`),
foreign key(`locationId`) references locations(`locationId`)
);

--  Bridge Tables

create table persons_superpowers
(
`personId` int not null,
`superpowerId` int not null,
primary key(`personId`, `superpowerId`)
);

create table persons_organizations
(
`personId` int not null,
`organizationId` int not null,
primary key(`personId`,`organizationId`)
);

create table sightings_persons
(
`sightingId` int not null,
`personId` int not null,
primary key(`sightingId`, `personId`)
);

-- adding Constraints

alter table persons_superpowers
add constraint fk_persons_superpowers_persons
foreign key (`personId`) references persons(`personId`);

alter table persons_superpowers
add constraint fk_persons_superpowers_superpowers
foreign key (`superpowerId`) references superpowers(`superpowerId`);

alter table persons_organizations
add constraint fk_persons_organizations_organizations
foreign key (`organizationId`) references organizations(`organizationId`);

alter table persons_organizations
add constraint fk_persons_organizations_persons
foreign key (`personId`) references persons(`personId`);

alter table sightings_persons
add constraint fk_sightings_persons_sightings
foreign key (`sightingId`) references sightings(`sightingId`);

alter table sightings_persons
add constraint fk_sightings_persons_persons
foreign key (`personId`) references persons(`personId`);
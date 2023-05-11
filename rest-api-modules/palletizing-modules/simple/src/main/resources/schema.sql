create table ITEM(
  ID varchar(30) not null ,
  NAME varchar(100) not null,
  length int,
  width int,
  height int,
  weight int,
    PRIMARY KEY ( ID )
);

create table PALLET(
  ID varchar(30) not null ,
  NAME varchar(100) not null,
  length int,
  width int,
  height int,
  weight int,
  max_height int,
  max_weight int,
    PRIMARY KEY ( ID )
);
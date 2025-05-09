CREATE SCHEMA IF NOT EXISTS person;

CREATE TABLE IF NOT EXISTS person.users(
   user_id int,
   user_name varchar(15),
   user_email varchar(80) unique,
   user_password varchar(125),
   user_handphone varchar(15) unique,
   created_date timestamp default current_timestamp,
   modified_date timestamp,
   CONSTRAINT pk_users PRIMARY KEY (user_id)
);
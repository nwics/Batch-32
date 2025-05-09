
alter table hr.regions
add column created_date timestamp default current_timestamp,
add column modified_date timestamp;
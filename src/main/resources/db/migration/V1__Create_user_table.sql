create table user
(
	id int auto_increment,
	account_id varchar(100),
	name varchar(50),
	token varchar(50),
	gem_create bigint,
	gem_modified int,
	constraint user_pk
		primary key (id)
);



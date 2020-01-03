create table user
(
	id int auto_increment,
	account_id varchar(100),
	avatar_url varchar(100),
	bio varchar(256),
	name varchar(50),
	token varchar(50),
	gem_create bigint,
	gem_modified bigint,
	constraint user_pk
		primary key (id)
);






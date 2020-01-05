create table comment
(
	id int auto_increment,
	parent_id int not null,
	type int not null,
	commentator int not null,
	gem_create bigint,
	gem_modified bigint,
	like_count bigint default 0,
	constraint comment_pk
		primary key (id)
);
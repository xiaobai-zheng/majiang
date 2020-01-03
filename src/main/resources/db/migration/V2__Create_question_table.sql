create table question
(
	id int auto_increment,
	title varchar(50),
	description text,
	tag varchar(256),
	gem_create bigint,
	gem_modified bigint,
	creator int,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	constraint question_pk
		primary key (id)
);


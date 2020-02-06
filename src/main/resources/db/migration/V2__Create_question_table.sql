create table question
(
	id bigint auto_increment,
	title varchar(50),
	description text,
	tag varchar(256),
	gem_create bigint,
	gem_modified bigint,
	creator bigint,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	constraint question_pk
		primary key (id)
);


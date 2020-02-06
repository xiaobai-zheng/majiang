create table comment
(
	id bigint auto_increment,
	parent_id bigint not null,
	commentator bigint not null,
	gem_create bigint,
	gem_modified bigint,
	like_count int,
	constraint comment_pk
		primary key (id)
);
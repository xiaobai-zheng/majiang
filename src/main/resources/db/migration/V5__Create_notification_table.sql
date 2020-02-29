create table NOTIFICATION
(
    ID         BIGINT  auto_increment,
    NOTIFIER   BIGINT            not null,
    RECEIVER   BIGINT            not null,
    TYPE       INTEGER           not null,
    GEM_CREATE BIGINT            not null,
    STATUS     INTEGER default 0 not null,
    OUTERID    BIGINT            not null,
    constraint NOTIFICATION_PK
        primary key (ID)
);
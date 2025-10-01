--liquibase formatted sql

--changeset flori:init
create table test (
    description varchar(255)
);
--rollback drop table test;
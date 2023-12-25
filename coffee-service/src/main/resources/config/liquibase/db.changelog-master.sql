--liquibase formatted sql

--changeset dima:1

CREATE SEQUENCE IF NOT EXISTS public.coffee_id_seq
  CYCLE
  INCREMENT 1
  START 1
  MINVALUE 1
  MAXVALUE 2147483647
  CACHE 371717;

--changeset dima:2

CREATE SEQUENCE IF NOT EXISTS public.coffee_descriptions_id_seq
  CYCLE
  INCREMENT 1
  START 1
  MINVALUE 1
  MAXVALUE 2147483647
  CACHE 371717;

--changeset dima:3

CREATE TABLE IF NOT EXISTS public.coffee 
(
  id bigint NOT NULL DEFAULT nextval('coffee_id_seq'::regclass),
  name varchar(255) NOT NULL,
  author_id bigint NOT NULL,
  CONSTRAINT coffee_pkey PRIMARY KEY (id)
);

--changeset dima:4

CREATE TABLE IF NOT EXISTS public.coffee_descriptions
(
  id bigint NOT NULL DEFAULT nextval('coffee_descriptions_id_seq'),
  description varchar(255),
  name varchar(255),
  performance varchar(255),
  value varchar(255),
  coffee_id bigint,
  CONSTRAINT coffee_descriptions_pkey PRIMARY KEY (id)
);


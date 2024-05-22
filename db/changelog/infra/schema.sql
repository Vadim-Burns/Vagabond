--liquibase formatted sql

--changeset len2367:create-schema-infra
create schema if not exists infra;
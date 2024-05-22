--liquibase formatted sql

--changeset len2367:create-environment-table
create table if not exists infra.environment
(
    name  text not null,
    value text not null
);

--changeset len2367:environmnet-table-comments runOnChange:true
comment on table infra.environment is 'Runtime environment variables table';
comment on column infra.environment.name is 'Variable name(not unique to store list)';
comment on column infra.environment.value is 'Variable value';

--liquibase formatted sql

--changeset len2367:create-vagabond-status-view runOnChange:true
drop view if exists util.vagabond_status;
create or replace view util.vagabond_status as
(
SELECT schema_name,
       relname,
       pg_size_pretty(table_size) AS size,
       table_size
FROM (SELECT pg_catalog.pg_namespace.nspname           AS schema_name,
             relname,
             pg_relation_size(pg_catalog.pg_class.oid) AS table_size
      FROM pg_catalog.pg_class
               JOIN pg_catalog.pg_namespace ON relnamespace = pg_catalog.pg_namespace.oid
      WHERE pg_catalog.pg_namespace.nspname = 'vagabond') t
ORDER BY table_size DESC
    );
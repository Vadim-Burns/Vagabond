--liquibase formatted sql

--changeset len2367:create-source-type
create type vagabond.uri_source_type as enum ('custom', 'indexer');

--changeset len2367:create-uri-status-type
create type vagabond.uri_status as enum ('new', 'checked', 'ready_to_recheck');

--changeset len2367:create-uri-table
create table if not exists vagabond.uri
(
    uri        text primary key,
    status     vagabond.uri_status      default 'new'             not null,
    source     vagabond.uri_source_type                           not null,
    created_at timestamp with time zone default current_timestamp not null,
    updated_at timestamp with time zone default current_timestamp not null
);

--changeset len2367:add-lock-uri
alter table vagabond.uri
    add column is_locked bool default false not null;

--changeset len2367:add-lock-uri-at
alter table vagabond.uri
    add column locked_at timestamp with time zone;

--changeset len2367:updated_at_trigger_func splitStatements:false stripComments:false
create or replace function vagabond.uri_set_updated_at()
    returns trigger as
$$
begin
    new.updated_at = now();
    return new;
end
$$ language plpgsql;

--changeset len2367:updated_at_trigger
create or replace trigger uri_set_update_at_trigger
    before update
    on vagabond.uri
    for each row
execute procedure vagabond.uri_set_updated_at();

--changeset len2367:create-idx-uri-status
create index if not exists idx_uri_status on vagabond.uri using hash (status);

--changeset len2367:create-idx-uri-status-not-locked
create index if not exists idx_uri_status_is_locked on vagabond.uri (status) where not is_locked;

--changeset len2367:uri-comments runOnChange:true
comment on table vagabond.uri is 'Табличка для хранения начальных uri для обработки';
comment on column vagabond.uri.uri is 'Uri';
comment on column vagabond.uri.status is 'Status';
comment on column vagabond.uri.source is 'Source type';
comment on column vagabond.uri.is_locked is 'Is uri locked';
comment on column vagabond.uri.locked_at is 'When uri locked in';

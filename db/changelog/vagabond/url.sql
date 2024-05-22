--liquibase formatted sql

--changeset len2367:create-url-status-type
create type vagabond.url_status as enum ('new', 'analyzed');

--changeset len2367:create-url-table
create table if not exists vagabond.url
(
    id          bigserial primary key,
    url         text unique                                        not null,
    status      vagabond.url_status      default 'new'             not null,
    analyzed_at timestamp with time zone,
    saved_at    varchar(255),
    created_at  timestamp with time zone default current_timestamp not null,
    updated_at  timestamp with time zone default current_timestamp not null
);

--changeset len2367:create-idx-url-url
create index if not exists idx_url_url on vagabond.url (url);

--changeset len2367:url_updated_at_trigger_func splitStatements:false stripComments:false
create or replace function vagabond.url_set_updated_at()
    returns trigger as
$$
begin
    new.updated_at = now();
    return new;
end
$$ language plpgsql;

--changeset len2367:url_updated_at_trigger
create or replace trigger url_set_update_at_trigger
    before update
    on vagabond.url
    for each row
execute procedure vagabond.url_set_updated_at();

--changeset len2367:url-is-deleted
alter table vagabond.url
    add column is_deleted bool default false not null;

--changeset len2367:url-is-locked
alter table vagabond.url
    add column is_locked bool default false not null;

--changeset len2367:url-is-locked_at
alter table vagabond.url
    add column locked_at timestamp with time zone;

--changeset len2367:url-idx-is-locked
create index if not exists idx_url_is_locked on vagabond.url (is_locked) where status = 'new';

--changeset len2367:url-drop-unique-on-all-url
alter table vagabond.url
    drop constraint url_url_key;

--changeset len2367:url-create-unique-constraint
create unique index if not exists url_url_unique_alive on vagabond.url (url) where not is_deleted;

--changeset len2367:url-comments runOnChange:true
comment on table vagabond.url is 'Табличка для хранения найденных url-ов';
comment on column vagabond.url.url is 'Url';
comment on column vagabond.url.status is 'Url status';
comment on column vagabond.url.saved_at is 'Когда статья была сохранена';
comment on column vagabond.url.analyzed_at is 'Last analyzed at';

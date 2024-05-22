--liquibase formatted sql

--changeset len2367:create-sequence-external-url
create sequence vagabond.s_external_url_id start with 1;

--changeset len2367:create-external-url-enum
create type vagabond.external_url_type as enum ('external_disk', 'social_network', 'telegram_join_link', 'ip', 'connect_link', 'other');

--changeset len2367:create-external_url-table
create table if not exists vagabond.external_url
(
    id         bigint                   default nextval('vagabond.s_external_url_id'::regclass) primary key,
    url        text unique                                        not null,
    type       vagabond.external_url_type                         not null,
    created_at timestamp with time zone default current_timestamp not null,
    updated_at timestamp with time zone default current_timestamp not null
);

--changeset len2367:create-idx-external_url_type
create index if not exists idx_external_url_type on vagabond.external_url (type);

--changeset len2367:external_urls_updated_at_trigger_func splitStatements:false stripComments:false
create or replace function vagabond.external_urls_set_updated_at()
    returns trigger as
$$
begin
    new.updated_at = now();
    return new;
end
$$ language plpgsql;

--changeset len2367:external_urls_updated_at_trigger
create or replace trigger external_urls_set_update_at_trigger
    before update
    on vagabond.external_url
    for each row
execute procedure vagabond.external_urls_set_updated_at();

--changeset len2367:rename-external-url-telegram-item
alter type vagabond.external_url_type rename value 'telegram_join_link' to 'telegram_link';

--changeset len2367:external_urls-comments runOnChange:true
comment on table vagabond.external_url is 'Хранение найденных внешних ссылок';
comment on column vagabond.external_url.id is 'Внутренний id';
comment on column vagabond.external_url.url is 'Найденная ссылка';
comment on column vagabond.external_url.type is 'Тип найденной ссылки';

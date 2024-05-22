--liquibase formatted sql

--changeset len2367:create-url_url-table
create table if not exists vagabond.url_url
(
    source_url_id bigint not null,
    target_url_id bigint not null
);

--changeset len2367:create-foreign-key-url-url
alter table vagabond.url_url
    add foreign key (source_url_id) references vagabond.url (id);
alter table vagabond.url_url
    add foreign key (target_url_id) references vagabond.url (id);

--changeset len2367:create-idx-url_url-source
create index if not exists idx_url_url_source on vagabond.url_url using hash (source_url_id);

--changeset len2367:create-idx-url_url-target
create index if not exists idx_url_url_target on vagabond.url_url using hash (target_url_id);

--changeset len2367:create-unique-constraint-url-url
alter table vagabond.url_url
    add constraint unq_constraint_url_url unique (source_url_id, target_url_id);

--changeset len2367:url_url-comments runOnChange:true
comment on table vagabond.url_url is 'Хранение связей между статьями telegra.ph';
comment on column vagabond.url_url.source_url_id is 'Статья на которой нашли ссылку';
comment on column vagabond.url_url.target_url_id is 'Статья на которую привела ссылка';

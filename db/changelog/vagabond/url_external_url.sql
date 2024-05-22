--liquibase formatted sql

--changeset len2367:create-url_external_url-table
create table if not exists vagabond.url_external_url
(
    source_url_id   bigint not null,
    external_url_id bigint not null
);

--changeset len2367:create-foreign-key-url_external_url
alter table vagabond.url_external_url
    add foreign key (source_url_id) references vagabond.url (id);
alter table vagabond.url_external_url
    add foreign key (external_url_id) references vagabond.external_url (id);

--changeset len2367:create-url_external_url-idx-source-url-id
create index if not exists idx_url_external_url_source_url_id on vagabond.url_external_url using hash (source_url_id);

--changeset len2367:create-url_external_url-idx-external-url-id
create index if not exists idx_url_external_url_external_url_id on vagabond.url_external_url using hash (external_url_id);

--changeset len2367:create-unique-constraint-url-external-url
alter table vagabond.url_external_url
    add constraint unq_constraint_url_external_url unique (source_url_id, external_url_id);

--changeset len2367:comments-url_external_url runOnChange:true
comment on table vagabond.url_external_url is 'Хранение связей между статьями telegra.ph и внешними ссылками';
comment on column vagabond.url_external_url.source_url_id is 'Id статьи telegra.ph, где нашли ссылку';
comment on column vagabond.url_external_url.external_url_id is 'Id внешней ссылки';

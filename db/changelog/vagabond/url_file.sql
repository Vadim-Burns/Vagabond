--liquibase formatted sql

--changeset len2367:create-url_file-table
create table if not exists vagabond.url_file
(
    url_id     bigint                                             not null,
    file_id    bigint                                             not null,
    created_at timestamp with time zone default current_timestamp not null
);

--changeset len2367:create-foreign-key-url_file
alter table vagabond.url_file
    add foreign key (url_id) references vagabond.url (id);
alter table vagabond.url_file
    add foreign key (file_id) references vagabond.file (id);

--changeset len2367:create-url_file-idx-url
create index if not exists idx_url_file_url on vagabond.url_file using hash (url_id);

--changeset len2367:create-url_file-idx-file
create index if not exists idx_url_file_file on vagabond.url_file using hash (file_id);

--changeset len2367:create-unique-constraint-url-file
alter table vagabond.url_file
    add constraint unq_constraint_url_file unique (url_id, file_id);

--changeset len2367:url-file-comments runOnChange:true
comment on table vagabond.url_file is 'Хранение связей между файлами и url-ами';
comment on column vagabond.url_file.url_id is 'Vagabond.url.id';
comment on column vagabond.url_file.file_id is 'Vagabond.file.id';

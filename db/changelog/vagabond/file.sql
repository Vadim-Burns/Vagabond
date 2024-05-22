--liquibase formatted sql

--changeset len2367:create-id-files-sequence
create sequence if not exists vagabond.s_files start with 1;

--changeset len2367:create-files-table
create table if not exists vagabond.files
(
    id         bigint                   default nextval('vagabond.s_files'::regclass) primary key,
    url        text unique                                        not null,
    created_at timestamp with time zone default current_timestamp not null,
    updated_at timestamp with time zone default current_timestamp not null
);

--changeset len2367:create-idx-files-url
create index if not exists idx_files_url on vagabond.files (url);

--changeset len2367:files_updated_at_trigger_func splitStatements:false stripComments:false
create or replace function vagabond.files_set_updated_at()
    returns trigger as
$$
begin
    new.updated_at = now();
    return new;
end
$$ language plpgsql;

--changeset len2367:files_updated_at_trigger
create or replace trigger files_set_update_at_trigger
    before update
    on vagabond.files
    for each row
execute procedure vagabond.files_set_updated_at();

--changeset len2367:files-fix-name
alter table if exists vagabond.files
    rename to file;

--changeset len2367:file-comments runOnChange:true
comment on table vagabond.file is 'Таблица для хранения файлов, сохраненных в telegra.ph';
comment on column vagabond.file.id is 'Внутренний id';
comment on column vagabond.file.url is 'Полный url файла';
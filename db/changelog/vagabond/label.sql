--liquibase formatted sql

--changeset len2367:create-label-table
create table if not exists vagabond.label
(
    id            bigserial primary key,
    source_url_id bigint                                             not null,
    reason        text                                               not null,
    created_at    timestamp with time zone default current_timestamp not null
);

--changeset len2367:add-label-foreign-key
alter table vagabond.label
    add foreign key (source_url_id) references vagabond.url (id);

--changeset len2367:create-label-reason-idx
create index if not exists idx_label_reason on vagabond.label (reason);

--changeset len2367:create-label-source_url_id-idx
create index if not exists idx_label_source_url_id on vagabond.label (source_url_id);

--changeset len2367:create-constraint-source-url-id-reason
alter table vagabond.label
    add constraint unq_constraint_source_reason unique (source_url_id, reason);

--changeset len2367:add-column-vagabond-label-count
alter table vagabond.label
    add column cnt bigint default 1 not null;

--changeset len2367:add-check-constraint-label-count
alter table vagabond.label
    add constraint constraint_label_count_check check (cnt > 0);

--changeset len2367:comment-on-vagabond-label runOnChange:true
comment on table vagabond.label is 'Предположения, что может находиться в этой статье';
comment on column vagabond.label.id is 'Id метки';
comment on column vagabond.label.source_url_id is 'Id статьи';
comment on column vagabond.label.reason is 'Метка возможного содержимого';
comment on column vagabond.label.cnt is 'Сколько раз содержимое находится в статье';

--liquibase formatted sql

--changeset len2367:create-command-table
create table if not exists infra.command
(
    id          bigserial primary key,
    command     varchar(255)                                       not null,
    created_at  timestamp with time zone default current_timestamp not null,
    started_at  timestamp with time zone,
    finished_at timestamp with time zone,
    result      text
);

--changeset len2367:comment-on-infra-command runOnChange:true
comment on table infra.command is 'Таблица для запуска команд на бэке';
comment on column infra.command.command is 'Название команды';
comment on column infra.command.started_at is 'Время запуска команды';
comment on column infra.command.finished_at is 'Время окончания команды';
comment on column infra.command.result is 'Результат выполнения';

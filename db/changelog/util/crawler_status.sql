--liquibase formatted sql

--changeset len2367:create-view-crawler-status runOnChange:true
drop view if exists util.crawler_status;
create view util.crawler_status as
(
select (select count(*) from vagabond.uri where is_locked)       as current_locked,
       (select max(locked_at) from vagabond.uri where is_locked) as last_locked,
       (select min(locked_at) from vagabond.uri where is_locked) as oldest_locked,
       (select count(*) from vagabond.uri where status != 'new') as checked_uris,
       (select count(*) from vagabond.url)                       as already_found_urls,
       (select max(created_at) from vagabond.url)                as last_found_url_datetime );
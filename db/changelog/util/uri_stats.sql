--liquibase formatted sql

--changeset len2367:create-view-crawler-stat runOnChange:true
drop view if exists util.uris_stats;
create view util.uris_stats as
(
select u.uri,
       (select count(*) from vagabond.url as ur where ur.url like concat('%/', u.uri, '-%')) as cnt
from vagabond.uri as u
order by cnt desc
limit 30 );
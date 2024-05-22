--liquibase formatted sql

--changeset len2367:create-function-to-get-info-about-external-url splitStatements:false stripComments:false runOnChange:true
create or replace function util.external_info(_id numeric)
    returns table
            (
                id  bigint,
                url text
            )
as
$$
begin
    return query
        with sources as (select source_url_id,
                                external_url_id
                         from vagabond.url_external_url
                         where external_url_id = _id)
        select u.id  as id,
               u.url as url
        from sources as s
                 left join vagabond.url as u
                           on s.source_url_id = u.id;
end
$$ language plpgsql;

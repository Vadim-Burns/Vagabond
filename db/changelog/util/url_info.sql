--liquibase formatted sql

--changeset len2367:create-function-to-get-info-about-url splitStatements:false stripComments:false runOnChange:true
create or replace function util.url_info(_id numeric)
    returns table
            (
                id   bigint,
                url  text,
                type text
            )
as
$$
begin
    return query
        with outgoing as (select u.id       as id,
                                 u.url      as url,
                                 'outgoing' as type
                          from vagabond.url_url as uu
                                   left join vagabond.url as u
                                             on u.id = uu.target_url_id
                          where uu.source_url_id = _id),
             ingoing as (select u.id      as id,
                                u.url     as url,
                                'ingoing' as type
                         from vagabond.url_url as uu
                                  left join vagabond.url as u
                                            on u.id = uu.source_url_id
                         where uu.target_url_id = _id)
        select *
        from ingoing
        union all
        select *
        from outgoing;
end
$$ language plpgsql;
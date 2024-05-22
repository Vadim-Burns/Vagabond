--liquibase formatted sql

--changeset len2367:create-function-to-get-info-about-file splitStatements:false stripComments:false runOnChange:true
create or replace function util.file_info(_id numeric)
    returns table
            (
                id  bigint,
                url text
            )
as
$$
begin
    return query
        with sources as (select url_id,
                                file_id
                         from vagabond.url_file
                         where file_id = _id)
        select u.id  as id,
               u.url as url
        from sources as s
                 left join vagabond.url as u
                           on s.url_id = u.id;
end
$$ language plpgsql;
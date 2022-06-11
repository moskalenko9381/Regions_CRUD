create table if not exists regions
(
    id
    bigint
    auto_increment
    not
    null,
    name
    varchar
(
    255
) not null ,
    short_name varchar
(
    255
) not null,
    primary key
(
    id
)
    );
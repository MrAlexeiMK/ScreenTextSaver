create table if not exists messages (
    id serial primary key,
    uuid uuid not null,
    text varchar(256) not null
);

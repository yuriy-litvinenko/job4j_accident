create table accident_type
(
    id   serial not null
        constraint accident_type_pkey
            primary key,
    name text
);

create table accident
(
    id            serial not null
        constraint accident_pkey
            primary key,
    name          text,
    text          text,
    address       text,
    accident_type integer
        constraint accident_accident_type_fkey
            references accident_type
);

create table rule
(
    id   serial not null
        constraint rule_pkey
            primary key,
    name text
);

create table accident_rule
(
    accident_id integer
        constraint accident_rule_accident_id_fkey
            references accident,
    rule_id     integer
        constraint accident_rule_rule_id_fkey
            references rule
);

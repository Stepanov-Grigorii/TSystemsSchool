create table admin
(
    id       integer generated by default as identity
        constraint admin_pkey
            primary key,
    email    varchar(255),
    login    varchar(255),
    password varchar(255)
);

create table cargo
(
    id     integer generated by default as identity
        constraint cargo_pkey
            primary key,
    name   varchar(255),
    number varchar(255),
    status varchar(255),
    weigh  numeric(19, 2)
);

create table city
(
    id        integer generated by default as identity
        constraint city_pkey
            primary key,
    latitude  double precision,
    longitude double precision,
    name      varchar(255)
);

create table distance
(
    id        integer generated by default as identity
        constraint distance_pkey
            primary key,
    distance  numeric(19, 2),
    first_id  integer
        constraint first_city_fk
            references city,
    second_id integer
        constraint second_city_fk
            references city
);

create table wagon
(
    id             integer generated by default as identity
        constraint wagon_pkey
            primary key,
    brand          varchar(255),
    capacity       integer,
    drivernumber   integer,
    registrynumber varchar(255),
    status         varchar(255),
    currentcity_id integer
        constraint current_city_fk
            references city
);

create table driver
(
    id                  integer generated by default as identity
        constraint driver_pkey
            primary key,
    email               varchar(255),
    login               varchar(255),
    password            varchar(255),
    hoursincurrentmonth integer,
    identitynumber      varchar(255),
    name                varchar(255),
    status              varchar(255),
    surname             varchar(255),
    currentcity_id      integer
        constraint current_city_fk
            references city,
    wagon_id            integer
        constraint wagon_fk
            references wagon
);

create table orders
(
    id       integer generated by default as identity
        constraint orders_pkey
            primary key,
    number   varchar(255),
    status   varchar(255),
    wagon_id integer
        constraint wagon_fk
            references wagon
);

create table waypoint
(
    id       integer generated by default as identity
        constraint waypoint_pkey
            primary key,
    type     varchar(255),
    cargo_id integer
        constraint cargo_fk
            references cargo,
    city_id  integer
        constraint city_fk
            references city,
    order_id integer
        constraint order_fk
            references orders
);
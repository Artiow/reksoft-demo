create sequence user_role_seq;

create sequence user_seq;

create sequence genre_seq;

create sequence album_seq;

create sequence composition_seq;

create sequence media_seq;

create sequence media_type_seq;

create sequence order_seq;

create sequence order_status_seq;

create table user_role
(
  id          integer default nextval('user_role_seq' :: regclass) not null
    constraint user_role_pkey
    primary key,
  name        varchar(45)                                          not null,
  code        varchar(45)                                          not null,
  description varchar(90)
);

create table genre
(
  id   integer default nextval('genre_seq' :: regclass) not null
    constraint genre_pkey
    primary key,
  name varchar(45)                                      not null,
  code varchar(45)                                      not null
);

create table media_type
(
  id          integer default nextval('media_type_seq' :: regclass) not null
    constraint media_type_pkey
    primary key,
  name        varchar(45)                                           not null,
  code        varchar(45)                                           not null,
  description varchar(90)
);

create table order_status
(
  id          integer default nextval('order_status_seq' :: regclass) not null
    constraint order_status_pkey
    primary key,
  name        varchar(45)                                             not null,
  code        varchar(45)                                             not null,
  description varchar(90)
);

create table "order"
(
  id        integer default nextval('order_seq' :: regclass) not null
    constraint order_pkey
    primary key,
  status_id integer                                          not null
    constraint order_status_id_fkey
    references order_status,
  address   varchar(90)                                      not null,
  ordered   timestamp                                        not null
);

create table picture
(
  id       serial      not null
    constraint picture_pkey
    primary key,
  url      varchar(90) not null,
  name     varchar(45) not null,
  size     integer     not null,
  uploaded timestamp   not null
);

create table "user"
(
  id         integer default nextval('user_seq' :: regclass) not null
    constraint user_pkey
    primary key,
  login      varchar(45)                                     not null,
  password   varchar(90)                                     not null,
  name       varchar(45)                                     not null,
  surname    varchar(45)                                     not null,
  picture_id integer
    constraint user_picture_id_fk
    references picture,
  phone      varchar(16)                                     not null,
  address    varchar(90)                                     not null,
  role_id    integer                                         not null
    constraint user_user_role_id_fk
    references user_role
);

create unique index user_picture_id_uindex
  on "user" (picture_id);

create table album
(
  id         integer default nextval('album_seq' :: regclass) not null
    constraint album_pkey
    primary key,
  label      varchar(45)                                      not null,
  singer     varchar(45)                                      not null,
  name       varchar(45)                                      not null,
  picture_id integer
    constraint album_picture_id_fk
    references picture,
  release    date                                             not null
);

create unique index album_picture_id_uindex
  on album (picture_id);

create table composition
(
  id        integer default nextval('composition_seq' :: regclass) not null
    constraint composition_pkey
    primary key,
  album_id  integer                                                not null
    constraint composition_album_id_fkey
    references album,
  album_pos integer                                                not null,
  name      varchar(45)                                            not null,
  duration  time                                                   not null
);

create table album_genres
(
  album_id integer not null
    constraint album_genres_album_id_fkey
    references album,
  genre_id integer not null
    constraint album_genres_genre_id_fkey
    references genre,
  constraint album_genres_album_id_genre_id_pk
  primary key (album_id, genre_id)
);

create table media
(
  id       integer default nextval('media_seq' :: regclass) not null
    constraint media_pkey
    primary key,
  type_id  integer                                          not null
    constraint media_type_id_fkey
    references media_type,
  album_id integer                                          not null
    constraint media_album_id_fk
    references album,
  price    integer                                          not null
);

create table media_picture
(
  media_id   integer not null
    constraint media_picture_media_id_fk
    references media,
  picture_id integer not null
    constraint media_picture_picture_id_fk
    references picture,
  constraint media_picture_media_id_picture_id_pk
  primary key (media_id, picture_id)
);

create table media_order
(
  media_id    integer           not null
    constraint media_order_media_id_fkey
    references media,
  order_id    integer           not null
    constraint media_order_order_id_fkey
    references "order",
  count       integer default 1 not null,
  total_price integer           not null,
  constraint media_order_media_id_order_id_pk
  primary key (media_id, order_id)
);

create table current_basket
(
  user_id     integer           not null
    constraint current_basket_user_id_fkey
    references "user",
  media_id    integer           not null
    constraint current_basket_media_id_fkey
    references media,
  media_count integer default 1 not null,
  constraint current_basket_user_id_media_id_pk
  primary key (user_id, media_id)
);

create unique index picture_id_uindex
  on picture (id);

create unique index picture_url_uindex
  on picture (url);



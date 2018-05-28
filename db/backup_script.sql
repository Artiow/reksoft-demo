create sequence demo.user_role_seq;

create sequence demo.user_seq;

create sequence demo.genre_seq;

create sequence demo.album_seq;

create sequence demo.composition_seq;

create sequence demo.media_seq;

create sequence demo.media_type_seq;

create sequence demo.order_seq;

create sequence demo.order_status_seq;

create sequence demo.picture_id_seq;

create sequence demo.label_id_seq;

create sequence demo.singer_id_seq;

create sequence demo.picture_id_seq1;

create sequence demo.label_id_seq1;

create sequence demo.singer_id_seq1;

create table demo.user_role
(
  id          integer default nextval('demo.user_role_seq' :: regclass) not null
    constraint user_role_pkey
    primary key,
  name        varchar(45)                                               not null,
  code        varchar(45)                                               not null,
  description varchar(90)
);

create table demo.genre
(
  id   integer default nextval('demo.genre_seq' :: regclass) not null
    constraint genre_pkey
    primary key,
  name varchar(45)                                           not null,
  code varchar(45)                                           not null
);

create table demo.media_type
(
  id          integer default nextval('demo.media_type_seq' :: regclass) not null
    constraint media_type_pkey
    primary key,
  name        varchar(45)                                                not null,
  code        varchar(45)                                                not null,
  description varchar(90)
);

create table demo.order_status
(
  id          integer default nextval('demo.order_status_seq' :: regclass) not null
    constraint order_status_pkey
    primary key,
  name        varchar(45)                                                  not null,
  code        varchar(45)                                                  not null,
  description varchar(90)
);

create table demo."order"
(
  id        integer default nextval('demo.order_seq' :: regclass) not null
    constraint order_pkey
    primary key,
  status_id integer                                               not null
    constraint order_status_id_fkey
    references order_status,
  address   varchar(90)                                           not null,
  ordered   timestamp                                             not null
);

create table demo.picture
(
  id       serial      not null
    constraint picture_pkey
    primary key,
  url      varchar(90) not null,
  name     varchar(45) not null,
  size     integer     not null,
  uploaded timestamp   not null
);

create unique index picture_id_uindex
  on demo.picture (id);

create unique index picture_url_uindex
  on demo.picture (url);

create table demo."user"
(
  id       integer default nextval('demo.user_seq' :: regclass) not null
    constraint user_pkey
    primary key,
  login    varchar(45)                                          not null,
  password varchar(90)                                          not null,
  name     varchar(45)                                          not null,
  surname  varchar(45)                                          not null,
  phone    varchar(16)                                          not null,
  address  varchar(90)                                          not null,
  role_id  integer                                              not null
    constraint user_user_role_id_fk
    references user_role
);

create table demo.label
(
  id   serial      not null
    constraint label_pkey
    primary key,
  name varchar(45) not null
);

create unique index label_id_uindex
  on demo.label (id);

create unique index label_name_uindex
  on demo.label (name);

create table demo.singer
(
  id   serial      not null
    constraint singer_pkey
    primary key,
  name varchar(45) not null
);

create unique index singer_id_uindex
  on demo.singer (id);

create unique index singer_name_uindex
  on demo.singer (name);

create table demo.album
(
  id          integer default nextval('demo.album_seq' :: regclass) not null
    constraint album_pkey
    primary key,
  label_id    integer                                               not null
    constraint album_label_id_fk
    references label,
  singer_id   integer                                               not null
    constraint album_singer_id_fk
    references singer,
  name        varchar(45)                                           not null,
  picture_id  integer
    constraint album_picture_id_fk
    references picture,
  release     date                                                  not null,
  description varchar(255)
);

create unique index album_picture_id_uindex
  on demo.album (picture_id);

create table demo.composition
(
  id        integer default nextval('demo.composition_seq' :: regclass) not null
    constraint composition_pkey
    primary key,
  album_id  integer                                                     not null
    constraint composition_album_id_fkey
    references album,
  album_pos integer                                                     not null,
  name      varchar(45)                                                 not null,
  duration  time                                                        not null
);

create table demo.album_genres
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

create table demo.media
(
  id       integer default nextval('demo.media_seq' :: regclass) not null
    constraint media_pkey
    primary key,
  type_id  integer                                               not null
    constraint media_type_id_fkey
    references media_type,
  album_id integer                                               not null
    constraint media_album_id_fk
    references album,
  price    integer                                               not null
);

create unique index media_type_id_album_id_uindex
  on demo.media (type_id, album_id);

create table demo.media_order
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

create table demo.current_basket
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



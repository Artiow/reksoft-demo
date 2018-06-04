

-- user_role

create table demo.user_role
(
  id          serial      not null
    constraint user_role_pk
    primary key,

  code        varchar(45) not null,
  name        varchar(45) not null,
  description varchar(90)
);

create unique index user_role_code_uindex
  on demo.user_role (code);

create unique index user_role_name_uindex
  on demo.user_role (name);


-- user

create table demo."user"
(
  id          serial      not null
    constraint user_pk
    primary key,

  role_id     integer     not null
    constraint user_user_role_id_fk
    references demo.user_role,

  login       varchar(45) not null,
  password    varchar(90) not null,
  name        varchar(45) not null,
  surname     varchar(45) not null,
  patronymic  varchar(45) not null,
  address     varchar(90) not null,
  phone       varchar(16) not null
);

create unique index user_login_uindex
  on demo."user" (login);


-- order_status

create table demo.order_status
(
  id          serial      not null
    constraint order_status_pk
    primary key,

  code        varchar(45) not null,
  name        varchar(45) not null,
  description varchar(90)
);

create unique index order_status_code_uindex
  on demo.order_status (code);

create unique index order_status_name_uindex
  on demo.order_status (name);


-- order

create table demo."order"
(
  id          serial    not null
    constraint order_pk
    primary key,

  status_id integer     not null
    constraint order_status_id_fk
    references demo.order_status,

  address   varchar(90) not null,
  ordered   timestamp   not null
);


-- picture

create table demo.picture
(
  id       serial       not null
    constraint picture_pk
    primary key,

  url      varchar(255) not null,
  name     varchar(45)  not null,
  width    integer      not null,
  height   integer      not null,
  uploaded timestamp    not null
);

create unique index picture_url_uindex
  on demo.picture (url);

create unique index picture_name_uindex
  on demo.picture (name);


-- label

create table demo.label
(
  id   serial      not null
    constraint label_pk
    primary key,

  name varchar(45) not null
);

create unique index label_name_uindex
  on demo.label (name);


-- singer

create table demo.singer
(
  id   serial      not null
    constraint singer_pk
    primary key,

  name varchar(45) not null
);

create unique index singer_name_uindex
  on demo.singer (name);


-- album

create table demo.album
(
  id            serial      not null
    constraint album_pk
    primary key,

  label_id      integer     not null
    constraint album_label_id_fk
    references demo.label,

  singer_id     integer     not null
    constraint album_singer_id_fk
    references demo.singer,

  picture_id    integer
    constraint album_picture_id_fk
    references demo.picture,

  name          varchar(45) not null,
  description   varchar(255),
  release_year  timestamp   not null
);

create unique index album_name_uindex
  on demo.album (name);


-- genre

create table demo.genre
(
  id   serial       not null
    constraint genre_pk
    primary key,

  code varchar(45)  not null,
  name varchar(45)  not null
);

create unique index genre_code_uindex
  on demo.genre (code);

create unique index genre_name_uindex
  on demo.genre (name);


-- album genres

create table demo.album_genres
(
  album_id integer not null
    constraint album_genres_album_id_fk
    references demo.album,

  genre_id integer not null
    constraint album_genres_genre_id_fk
    references demo.genre,

  constraint album_genres_pk
  primary key (album_id, genre_id)
);


-- composition

create table demo.composition
(
  id        serial      not null
    constraint composition_pk
    primary key,

  album_id  integer     not null
    constraint composition_album_id_fk
    references demo.album,

  album_pos integer     not null,
  name      varchar(45) not null,
  duration  interval    not null
);

create unique index composition_album_id_album_pos_uindex
  on demo.composition (album_id, album_pos);


-- media_type

create table demo.media_type
(
  id          serial      not null
    constraint media_type_pk
    primary key,

  code        varchar(45) not null,
  name        varchar(45) not null,
  description varchar(90)
);

create unique index media_type_code_uindex
  on demo.media_type (code);

create unique index media_type_name_uindex
  on demo.media_type (name);


-- media

create table demo.media
(
  id       serial   not null
    constraint media_pk
    primary key,

  type_id  integer  not null
    constraint media_type_id_fk
    references demo.media_type,

  album_id integer  not null
    constraint media_album_id_fk
    references demo.album,

  price    integer  not null
);

create unique index media_type_id_album_id_uindex
  on demo.media (type_id, album_id);


-- media_order

create table demo.media_order
(
  media_id    integer           not null
    constraint media_order_media_id_fk
    references demo.media,

  order_id    integer           not null
    constraint media_order_order_id_fk
    references demo."order",

  count       integer default 1 not null,
  total_price integer           not null,

  constraint media_order_pk
  primary key (media_id, order_id)
);


-- current_basket

create table demo.current_basket
(
  user_id     integer           not null
    constraint current_basket_user_id_fk
    references demo."user",

  media_id    integer           not null
    constraint current_basket_media_id_fk
    references demo.media,

  media_count integer default 1 not null,

  constraint current_basket_user_id_media_id_pk
  primary key (user_id, media_id)
);

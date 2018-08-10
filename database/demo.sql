------------------------------------------------------------------------------------------------------------------------
-- DICTIONARIES

-- genres

INSERT INTO demo.genre (id, name, code) VALUES
  (1, 'инди-рок', 'indie_rock'),
  (2, 'инди-поп', 'indie_pop'),
  (3, 'дрим-поп', 'dream_pop'),
  (4, 'поп-панк', 'pop_punk'),
  (5, 'пост-панк', 'post_punk'),
  (6, 'альтернативный рок', 'alternative'),
  (7, 'эмо', 'emo'),
  (8, 'мидвест-эмо', 'midwest'),
  (9, 'скримо', 'screamo'),
  (10, 'скрамз', 'skramz'),
  (11, 'художественная декламация', 'spoken_word'),
  (12, 'математический рок', 'math_rock');

ALTER SEQUENCE demo.genre_id_seq RESTART WITH 13;

-- media_type

INSERT INTO demo.media_type (id, name, code, description) VALUES
  (1, 'CD', 'cd', 'компакт-диск'),
  (2, 'Винил', 'vinyl', 'музыкальная пластинка'),
  (3, 'Кассета', 'cassette', 'музыкальная кассета');

ALTER SEQUENCE demo.media_type_id_seq RESTART WITH 4;

-- order_status

INSERT INTO demo.order_status (id, code, name, description) VALUES
  (1, 'expect', 'Ожидает', 'ожидает подтверждения и отправки'),
  (2, 'sent', 'Отправлено', 'подтвержденио и отправлено');

ALTER SEQUENCE demo.order_status_id_seq RESTART WITH 3;

------------------------------------------------------------------------------------------------------------------------
-- DATA

-- label

INSERT INTO demo.label (id, name) VALUES
  (1, 'Big Scary Monsters'),
  (2, 'Flower Girl Records'),
  (3, 'Triple Crown Records');

ALTER SEQUENCE demo.label_id_seq RESTART WITH 4;

-- singer

INSERT INTO demo.singer (id, name) VALUES
  (1, 'Tiny Moving Parts'),
  (2, 'Sorority Noise'),
  (3, 'Old Grey');

ALTER SEQUENCE demo.singer_id_seq RESTART WITH 4;

-- picture

INSERT INTO demo.picture (id, size, uploaded) VALUES
  (1, 15323, '2018-06-07 10:10:00.000000'),
  (2, 213896, '2018-06-07 10:10:00.000000'),
  (3, 377022, '2018-06-07 10:10:00.000000'),
  (4, 23673, '2018-06-07 10:10:00.000000');

ALTER SEQUENCE demo.picture_id_seq RESTART WITH 5;

-- album

INSERT INTO demo.album (id, label_id, singer_id, picture_id, name, description, release_year) VALUES
  (1, 1, 1, 1, 'Swell', null, '2016-01-01 00:00:00'),
  (2, 2, 3, 2, 'Slow Burn', null, '2016-01-01 00:00:00'),
  (3, 2, 2, 3, 'Forgettable', null, '2015-01-01 00:00:00'),
  (4, 3, 2, 4, 'You''re Not As _____ As You Think', null, '2017-01-01 00:00:00');

ALTER SEQUENCE demo.album_id_seq RESTART WITH 5;

-- album_genres

INSERT INTO demo.album_genres (album_id, genre_id) VALUES
  (1, 7),
  (1, 8),
  (1, 12),
  (2, 7),
  (2, 9),
  (2, 10),
  (2, 11),
  (3, 1),
  (3, 4),
  (3, 7),
  (4, 1),
  (4, 4),
  (4, 6),
  (4, 7),
  (4, 8);

-- composition

INSERT INTO demo.composition (id, album_id, album_pos, name, duration) VALUES
  (1, 1, 1, 'Applause', '0001-01-01 00:03:07'),
  (2, 1, 2, 'Smooth It Out', '0001-01-01 00:03:24'),
  (3, 1, 3, 'Feel Alive', '0001-01-01 00:03:21'),
  (4, 2, 1, 'Pulpit', '0001-01-01 00:01:16'),
  (5, 2, 2, 'Communion', '0001-01-01 00:01:04'),
  (6, 2, 3, 'Blunt Trauma', '0001-01-01 00:00:52'),
  (7, 3, 1, 'Rory Shield', '0001-01-01 00:03:13'),
  (8, 3, 2, 'Mediocre at Best', '0001-01-01 00:02:13'),
  (9, 3, 3, 'Dirty Ickes', '0001-01-01 00:02:19'),
  (10, 4, 1, 'No Halo', '0001-01-01 00:02:50'),
  (11, 4, 2, 'A Portrait Of', '0001-01-01 00:03:30'),
  (12, 4, 3, 'First Letter From St. Sean', '0001-01-01 00:03:05');

ALTER SEQUENCE demo.composition_id_seq RESTART WITH 13;

-- media

INSERT INTO demo.media (id, type_id, album_id, price) VALUES
  (1, 1, 1, 1250),
  (2, 1, 2, 1500),
  (3, 1, 3, 1500),
  (4, 1, 4, 600),
  (5, 2, 2, 2300),
  (6, 2, 3, 2250),
  (7, 2, 4, 1250),
  (8, 3, 4, 800);

ALTER SEQUENCE demo.media_id_seq RESTART WITH 9;

------------------------------------------------------------------------------------------------------------------------
-- AUTH DATA

-- user_role

INSERT INTO demo.user_role (id, code, name, description) VALUES
  (1, 'admin', 'Администратор', 'администратор сайта'),
  (2, 'user', 'Пользователь', 'обычный пользователь');

ALTER SEQUENCE demo.user_role_id_seq RESTART WITH 3;

-- user

INSERT INTO demo."user" (id, role_id, login, password, name, surname, patronymic, address, phone) VALUES
  (1, 1, 'root', '$2a$10$6ugKXFk4PvEWwxapdDTY7e3TLIu3pkRVr4Elf6ltTbImptM..EHc2', 'Намеднев', 'Артем', 'Александрович',
   'г. Воронеж', '+7-920-425-12-58'),
  (2, 2, 'user', '$2a$10$O3C/PdAH3s2k15dxQLbrFOBI4I9pWJefgCGke9/wVw.jFDXEYXUpy', 'Намеднев', 'Артем', 'Александрович',
   'г. Воронеж', '+7-920-425-12-58');

ALTER SEQUENCE demo.user_id_seq RESTART WITH 3;

------------------------------------------------------------------------------------------------------------------------
-- USER DATA

-- current_basket

INSERT INTO demo.current_basket (user_id, media_id, count) VALUES
  (1, 1, 1),
  (1, 2, 1),
  (1, 3, 1),
  (1, 4, 1),
  (2, 1, 1),
  (2, 4, 1),
  (2, 6, 1);

-- order

INSERT INTO demo."order" (id, status_id, cost, address, ordered) VALUES
  (1, 2, 3000, 'ул. Адрес, дом 1', '2018-07-12 14:00:00'),
  (2, 2, 3000, 'ул. Адрес, дом 2', '2018-07-12 14:00:00'),
  (3, 1, 1500, 'ул. Адрес, дом 3', '2018-07-12 14:00:00');

ALTER SEQUENCE demo.order_id_seq RESTART WITH 4;

-- ordered_media

INSERT INTO demo.ordered_media (order_id, media_id, count) VALUES
  (1, 2, 1),
  (1, 3, 1),
  (2, 2, 2),
  (3, 2, 1);
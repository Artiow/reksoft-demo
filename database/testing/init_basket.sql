------------------------------------------------------------------------------------------------------------------------
-- DICTIONARIES

-- media_type

INSERT INTO demo.media_type (id, name, code, description) VALUES
  (1, 'TEST_TYPE_1', 'TEST_TYPE_1', 'TEST_TYPE_1'),
  (2, 'TEST_TYPE_2', 'TEST_TYPE_2', 'TEST_TYPE_2');

ALTER SEQUENCE demo.media_type_id_seq RESTART WITH 3;

------------------------------------------------------------------------------------------------------------------------
-- DATA

-- label

INSERT INTO demo.label (id, name) VALUES
  (1, 'LABEL_1'),
  (2, 'LABEL_2'),
  (3, 'LABEL_3');

ALTER SEQUENCE demo.label_id_seq RESTART WITH 4;

-- singer

INSERT INTO demo.singer (id, name) VALUES
  (1, 'SINGER_1'),
  (2, 'SINGER_2'),
  (3, 'SINGER_3');

ALTER SEQUENCE demo.singer_id_seq RESTART WITH 4;

-- album

INSERT INTO demo.album (id, label_id, singer_id, picture_id, name, description, release_year) VALUES
  (1, 1, 1, null, 'ALBUM_1', null, '2000-01-01 00:00:00'),
  (2, 2, 3, null, 'ALBUM_2', null, '2000-01-01 00:00:00'),
  (3, 2, 2, null, 'ALBUM_3', null, '2000-01-01 00:00:00');

ALTER SEQUENCE demo.album_id_seq RESTART WITH 4;

-- media

INSERT INTO demo.media (id, type_id, album_id, price) VALUES
  (1, 1, 1, 1500),
  (2, 1, 2, 1500),
  (3, 1, 3, 1500),
  (4, 2, 1, 2000),
  (5, 2, 2, 2000),
  (6, 2, 3, 2000);

ALTER SEQUENCE demo.media_id_seq RESTART WITH 7;

------------------------------------------------------------------------------------------------------------------------
-- AUTH DATA

-- user_role

INSERT INTO demo.user_role (id, code, name, description) VALUES
  (1, 'user', 'TEST_ROLE', 'TEST_ROLE');

ALTER SEQUENCE demo.user_role_id_seq RESTART WITH 2;

-- user

INSERT INTO demo."user" (id, role_id, login, password, name, surname, patronymic, address, phone) VALUES
  (1, 1, 'user', '$2a$10$O3C/PdAH3s2k15dxQLbrFOBI4I9pWJefgCGke9/wVw.jFDXEYXUpy',
   'TEST_USER', 'TEST_USER', 'TEST_USER', 'TEST_USER', '+7-999-999-99-99');

ALTER SEQUENCE demo.user_id_seq RESTART WITH 2;

------------------------------------------------------------------------------------------------------------------------
-- USER DATA

-- current_basket

INSERT INTO demo.current_basket (user_id, media_id, count) VALUES
  (1, 1, 3),
  (1, 2, 2),
  (1, 3, 1);


-- label

INSERT INTO demo.label (id, name) VALUES
(1, 'Big Scary Monsters'  ),
(2, 'Flower Girl Records' ),
(3, 'Triple Crown Records');

ALTER SEQUENCE demo.label_id_seq RESTART WITH 4;


-- singer

INSERT INTO demo.singer (id, name) VALUES
(1, 'Old Grey'         ),
(2, 'Sorority Noise'   ),
(3, 'Tiny Moving Parts');

ALTER SEQUENCE demo.singer_id_seq RESTART WITH 4;


-- album

INSERT INTO demo.album (id, label_id, singer_id, picture_id, name, description, release_year) VALUES
(1, 2, 2, null, 'Forgettable',                        null, '2015-01-01 00:00:00.000000'),
(2, 2, 1, null, 'Slow Burn',                          null, '2016-01-01 00:00:00.000000'),
(3, 1, 3, null, 'Swell',                              null, '2016-01-01 00:00:00.000000'),
(4, 3, 2, null, 'You''re Not As _____ As You Think',  null, '2017-01-01 00:00:00.000000');

ALTER SEQUENCE demo.album_id_seq RESTART WITH 5;


-- album_genres

INSERT INTO demo.album_genres (album_id, genre_id) VALUES
(1, 1 ),
(1, 4 ),
(1, 7 ),
(2, 7 ),
(2, 9 ),
(2, 10),
(2, 11),
(3, 7 ),
(3, 8 ),
(3, 12),
(4, 1 ),
(4, 4 ),
(4, 6 ),
(4, 7 ),
(4, 8 );


-- composition

INSERT INTO demo.composition (id, album_id, album_pos, name, duration) VALUES
(1, 1, 1, 'Rory Shield',      '00:03:13'),
(2, 1, 2, 'Mediocre at Best', '00:02:13'),
(3, 1, 3, 'Dirty Ickes',      '00:02:19'),
(4, 2, 1, 'Pulpit',           '00:01:16'),
(5, 2, 2, 'Communion',        '00:01:04'),
(6, 2, 3, 'Blunt Trauma',     '00:00:52'),
(7, 3, 1, 'Applause',         '00:03:07'),
(8, 3, 2, 'Smooth It Out',    '00:03:24'),
(9, 3, 3, 'Feel Alive',       '00:03:21');

ALTER SEQUENCE demo.composition_id_seq RESTART WITH 10;


-- media

INSERT INTO demo.media (id, type_id, album_id, price) VALUES
(1, 1, 1, 1500),
(2, 1, 2, 1500),
(3, 1, 3, 1250),
(4, 1, 4, 600 ),
(5, 2, 1, 2250),
(6, 2, 2, 2300),
(7, 2, 4, 1250),
(8, 3, 4, 800 );

ALTER SEQUENCE demo.media_id_seq RESTART WITH 9;

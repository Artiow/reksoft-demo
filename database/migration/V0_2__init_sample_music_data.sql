

-- label

INSERT INTO demo.label (name) VALUES
('Big Scary Monsters'  ),
('Flower Girl Records' ),
('Triple Crown Records');


-- singer

INSERT INTO demo.singer (name) VALUES
('Old Grey'         ),
('Sorority Noise'   ),
('Tiny Moving Parts');


-- album

INSERT INTO demo.album (label_id, singer_id, picture_id, name, description, release_year) VALUES
(2, 2, null, 'Forgettable',                        null, '2015-01-01 00:00:00'),
(2, 1, null, 'Slow Burn',                          null, '2016-01-01 00:00:00'),
(1, 3, null, 'Swell',                              null, '2016-01-01 00:00:00'),
(3, 2, null, 'You''re Not As _____ As You Think',  null, '2017-01-01 00:00:00');


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

INSERT INTO demo.composition (album_id, album_pos, name, duration) VALUES
(1, 1, 'Rory Shield',                '0001-01-01 00:03:13'),
(1, 2, 'Mediocre at Best',           '0001-01-01 00:02:13'),
(1, 3, 'Dirty Ickes',                '0001-01-01 00:02:19'),
(2, 1, 'Pulpit',                     '0001-01-01 00:01:16'),
(2, 2, 'Communion',                  '0001-01-01 00:01:04'),
(2, 3, 'Blunt Trauma',               '0001-01-01 00:00:52'),
(3, 1, 'Applause',                   '0001-01-01 00:03:07'),
(3, 2, 'Smooth It Out',              '0001-01-01 00:03:24'),
(3, 3, 'Feel Alive',                 '0001-01-01 00:03:21'),
(4, 1, 'No Halo',                    '0001-01-01 00:02:50'),
(4, 2, 'A Portrait Of',              '0001-01-01 00:03:30'),
(4, 3, 'First Letter From St. Sean', '0001-01-01 00:03:05');


-- media

INSERT INTO demo.media (type_id, album_id, price) VALUES
(1, 1, 1500),
(1, 2, 1500),
(1, 3, 1250),
(1, 4, 600 ),
(2, 1, 2250),
(2, 2, 2300),
(2, 4, 1250),
(3, 4, 800 );


INSERT INTO demo.picture (name, size, uploaded) VALUES
('forgettable.jpg',                15323,  '2018-06-07 10:10:00.000000'),
('slow_burn.jpg',                  213896, '2018-06-07 10:10:00.000000'),
('swell.jpg',                      377022, '2018-06-07 10:10:00.000000'),
('you_re_not_as_as_you_think.jpg', 23673,  '2018-06-07 10:10:00.000000');

UPDATE demo.album SET picture_id = 1 WHERE id = 1;
UPDATE demo.album SET picture_id = 2 WHERE id = 2;
UPDATE demo.album SET picture_id = 3 WHERE id = 3;
UPDATE demo.album SET picture_id = 4 WHERE id = 4;

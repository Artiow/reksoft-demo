
INSERT INTO demo.picture (name, width, height, uploaded) VALUES
('forgettable.jpg',                355,  355,  '2018-06-07 10:10:00.000000'),
('slow_burn.jpg',                  1200, 1200, '2018-06-07 10:10:00.000000'),
('swell.jpg',                      1200, 1200, '2018-06-07 10:10:00.000000'),
('you_re_not_as_as_you_think.jpg', 320,  320,  '2018-06-07 10:10:00.000000');

UPDATE demo.album SET picture_id = 1 WHERE id = 1;
UPDATE demo.album SET picture_id = 2 WHERE id = 2;
UPDATE demo.album SET picture_id = 3 WHERE id = 3;
UPDATE demo.album SET picture_id = 4 WHERE id = 4;

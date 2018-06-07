
INSERT INTO demo.picture (url, name, width, height, uploaded) VALUES
('/pic/forgettable.jpg',                'Forgettable',                       355,  355,  '2018-06-07 10:10:00.000000'),
('/pic/slow_burn.jpg',                  'Slow Burn',                         1200, 1200, '2018-06-07 10:10:00.000000'),
('/pic/swell.jpg',                      'Swell',                             1200, 1200, '2018-06-07 10:10:00.000000'),
('/pic/you_re_not_as_as_you_think.jpg', 'You''re Not As _____ As You Think', 320,  320,  '2018-06-07 10:10:00.000000');

UPDATE demo.album SET picture_id = 1 WHERE id = 1;
UPDATE demo.album SET picture_id = 2 WHERE id = 2;
UPDATE demo.album SET picture_id = 3 WHERE id = 3;
UPDATE demo.album SET picture_id = 4 WHERE id = 4;

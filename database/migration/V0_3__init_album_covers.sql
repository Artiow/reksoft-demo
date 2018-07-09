INSERT INTO demo.picture (size, uploaded) VALUES
  (15323, '2018-06-07 10:10:00.000000'),
  (213896, '2018-06-07 10:10:00.000000'),
  (377022, '2018-06-07 10:10:00.000000'),
  (23673, '2018-06-07 10:10:00.000000');

UPDATE demo.album
SET picture_id = 1
WHERE id = 1;

UPDATE demo.album
SET picture_id = 2
WHERE id = 2;

UPDATE demo.album
SET picture_id = 3
WHERE id = 3;

UPDATE demo.album
SET picture_id = 4
WHERE id = 4;

-- media_type

INSERT INTO demo.media_type (id, name, code, description) VALUES
  (1, 'CD', 'cd', 'компакт-диск'),
  (2, 'Винил', 'vinyl', 'музыкальная пластинка'),
  (3, 'Кассета', 'cassette', 'музыкальная кассета');

ALTER SEQUENCE demo.media_type_id_seq RESTART WITH 4;

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

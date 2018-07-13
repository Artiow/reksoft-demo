insert into demo.order_status (id, code, name, description) values
  (1, 'expect', 'Ожидает', 'ожидает подтверждения и отправки'),
  (2, 'sent', 'Отправлено', 'подтвержденио и отправлено');

alter sequence demo.order_status_id_seq restart with 3;

insert into demo."order" (status_id, cost, address, ordered) values
  (2, 0, 'ул. Адрес, дом 1', '2018-07-12 14:00:00'),
  (2, 0, 'ул. Адрес, дом 2', '2018-07-12 14:00:00'),
  (1, 0, 'ул. Адрес, дом 3', '2018-07-12 14:00:00'),
  (1, 0, 'ул. Адрес, дом 4', '2018-07-12 14:00:00');

insert into demo.media_order (media_id, order_id, count) values
  (1, 1, 1),
  (2, 1, 2),
  (2, 2, 3);
insert into demo.user_role (id, code, name, description) values
  (1, 'admin', 'Администратор', 'администратор сайта'),
  (2, 'user', 'Пользователь', 'обычный пользователь');

alter sequence demo.user_role_id_seq restart with 3;

insert into demo."user" (role_id, login, password, name, surname, patronymic, address, phone) values
  (1, 'root', '$2a$10$6ugKXFk4PvEWwxapdDTY7e3TLIu3pkRVr4Elf6ltTbImptM..EHc2', 'Name', 'Surname', 'Patronymic', 'Address', 'Phone');
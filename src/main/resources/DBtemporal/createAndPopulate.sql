DROP TABLE IF EXISTS app_user;

create table app_user
(
    user_id         SERIAL PRIMARY KEY,
    first_name varchar(20) NOT NULL,
    last_name  varchar(20) NOT NULL,
    user_info  varchar(200),
    user_email varchar(30)
);


INSERT INTO app_user(first_name, last_name, user_info, user_email)
VALUES ('Ivan', 'Ivanov', 'Moscow, 23 y.o', 'ivanov@gmail.com'),
       ('Alex', 'Alexandrov', 'Saint-Petersburg, 10 y.o', 'alexandrov@mail.ru'),
       ('Maxim', 'Kizilov', 'Kirov, 27 y.o', 'maxkizi@yandex.ru');


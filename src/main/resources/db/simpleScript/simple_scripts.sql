drop table user_vacations;
drop table users;
drop table databasechangelog;
drop table databasechangeloglock;


SELECT * FROM users u LEFT JOIN user_vacations uv ON u.id = uv.user_id ORDER BY u.id;
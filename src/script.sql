CREATE DATABASE IF NOT EXISTS bd2_2023_grupo8;
CREATE USER 'grupo8'@'localhost' IDENTIFIED BY 'grupoocho';
ALTER USER 'grupo8'@'localhost' IDENTIFIED WITH mysql_native_password BY 'grupoocho';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP ON bd2_2023_grupo8.* TO 'grupo8'@'localhost';
FLUSH PRIVILEGES;
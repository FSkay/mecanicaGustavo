create database dbmecanicagustavo;
-- a linha abaixo escolhe o banco de dados a ser utilizado
use dbmecanicagustavo;
-- cria tabela
create table tbclientes(
idclitbclientestbclientes int primary key auto_increment,
nomecli varchar(40) not null,
cpfcli varchar(14) not null unique,
enderecocli varchar(50) not null,
emailcli varchar(50) not null,
senhacli varchar(15) not null
);
describe tbclientes;


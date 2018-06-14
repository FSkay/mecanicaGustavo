create database dbmecanicagustavo;
-- a linha abaixo escolhe o banco de dados a ser utilizado
use dbmecanicagustavo;
-- cria tabela
create table tbeusuario(
iduser int primary key not null auto_increment,
nomeuser varchar(40) not null,
emailuser varchar(40) not null,
login varchar(30) not null unique,
senha varchar(30) not null
);

create table tbclientes(
idcli int primary key not null auto_increment,
nomecli varchar(40) not null,
cpfcli varchar(14) not null,
enderecocli varchar(50) not null,
emailcli varchar(50) not null,
senhacli varchar(15) not null,
placaveicli varchar(10),
veiculocli varchar(14),
kmveicli varchar(9999)  
);

insert into tbeusuario(nomeuser, emailuser, login, senha)
values ('Admin', 'admin@admin.com', 'admin', 'admin');
describe tbclientes;
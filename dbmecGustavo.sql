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
cpfcli varchar(14) not null unique,
enderecocli varchar(50) not null,
cidadecli varchar(30) not null,
fonecli varchar(14) not null,
emailcli varchar(50) not null
);

insert into tbeusuario(nomeuser, emailuser, login, senha)
values ('Admin', 'admin@admin.com', 'admin', 'admin');
describe tbos;
select * from tbclientes;
-- a linha abaixo adiciona um campo na tabela
alter table tbeusuario add column perfil varchar(20) not null;
-- a linha abaixo remove um campo da tabela 
alter table tbeusuario drop column perfil;
update tbeusuario set perfil='admin' where iduser=1;
select * from tbeusuario where iduser=1;

alter table tbeusuario 
add logincli varchar(30) not null unique;

create table tbos(
os int(11) not null primary key auto_increment,
data_os timestamp not null,
veiculo varchar(100) not null,
placa varchar(100) not null,
km varchar(10000) not null, 
problema varchar(300),
servico_executado varchar(100),
mecanico varchar(50),
fk_idcli int not null,
foreign key (fk_idcli) references tbclientes (idcli) 
);
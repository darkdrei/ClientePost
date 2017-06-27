select  p.id, p.nombre,p.descripcion,t.nombre,p.precio from  plato as p inner join tipo as t on (p.tipo_id=t.id and p.estado=1 and t.estado=1) ;
select * from plato

SELECT * from categoria;
insert into categoria (nombre,descripcion) values ('Bebidas','Liquidos y mas');
insert into categoria (nombre,descripcion) values ('Jugos','Liquidos y mas');
insert into categoria (nombre,descripcion) values ('Entradas','Liquidos y mas');
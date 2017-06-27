select * from trabajador;
select  count(nombre)   from trabajador where cedula like '12121212'
select t.cedula,t.nombre,t.apellidos,t.telefono,u.nom from trabajador as t inner join usuario as u on (t.user_id=u.id)  where u.estado=1
select * from tipo

select * from categoria
select id from new_factura from mesa_id=1 and estado=1 order by id desc limit 1;
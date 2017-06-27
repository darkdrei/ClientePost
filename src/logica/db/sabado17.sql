select * from conten_facnew as c inner join plato as p on (c.plato_id=p.id and c.estado=1 and p.estado=1);
update conten_facnew set estado = 1 where factura_id=1 and id in (12,13) 
update new_factura set estado = 1 where id=1


select sum(p.precio*c.cantidad) as total from conten_facnew as c  inner join plato as p on (c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id=2)
 
 select count(id) from new_factura where estado=1 and mesa_id=0 order by id desc limit 1
 select c.id,p.nombre,p.precio,c.cantidad,p.precio*c.cantidad as total from conten_facnew as c  inner join plato as p on (c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id=0)
 select * from plato
 select  case when opcion=1 then 1.1 *(select sum(p.precio*c.cantidad) as total from conten_facnew as c  inner join plato as p on (c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id= f. mesa_id) )   else add_p+(select sum(p.precio*c.cantidad) as total from conten_facnew as c 
 inner join plato as p on (c.plato_id=p.id and c.estado=1 and p.estado=1 and c.factura_id= f. mesa_id)) end as total from new_factura as f where f. id=1 and estado=1 order by id desc
 
 select *   from new_factura estado=1 and mesa_id=0
 
 update new_factura set opcion=1  where id=1;
  select * from new_factura
 select id from new_factura where mesa_id=1 and estado=1 order by id desc limit 1;
  select  sum(cantidad*) from conten_facnew 
 
 select * from new_factura
 select opcion,add_p from new_factura  where id=1
 select * from conten_facnew 
  delete from new_factura
 delete from conten_facnew
 
 
 
 CREATE TABLE "new_factura" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "dia" INTEGER NOT NULL,
    "mes" INTEGER NOT NULL,
    "anio" INTEGER NOT NULL,
    "opcion" INTEGER NOT NULL DEFAULT (1),
    "estado" INTEGER NOT NULL DEFAULT (1),
    "add_p" REAL NOT NULL DEFAULT (0),
    "mesa_id" INTEGER
)
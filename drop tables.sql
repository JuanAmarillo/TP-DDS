alter table basedebatos.condiciones drop foreign key condiciones_ibfk_1;
alter table basedebatos.condiciones_x_metodologias drop foreign key condiciones_x_metodologias_ibfk_1;
alter table basedebatos.condiciones_x_metodologias drop foreign key condiciones_x_metodologias_ibfk_2;
alter table basedebatos.cuentas drop foreign key cuentas_ibfk_1;

drop table basedebatos.condiciones;
drop table basedebatos.condiciones_x_metodologias;
drop table basedebatos.cuentas;
drop table basedebatos.empresas;
drop table basedebatos.indicadores;
drop table basedebatos.metodologias;
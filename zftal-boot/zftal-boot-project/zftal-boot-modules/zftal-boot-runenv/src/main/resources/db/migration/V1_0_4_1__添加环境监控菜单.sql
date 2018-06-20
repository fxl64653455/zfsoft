/**************添加二级菜单**************/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N0104';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N0104','服务器监控','N01','','2');

/**************添加三级菜单**************/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N010401';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N010401','JVM环境监控','N0104','/monitor/jvm/index','3');

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N010401';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010401_#','N010401','#','monitor-watch:cx',''); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010401_xg','N010401','xg','monitor-watch:xg','1'); 

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N010401%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010401_#'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010401_xg');
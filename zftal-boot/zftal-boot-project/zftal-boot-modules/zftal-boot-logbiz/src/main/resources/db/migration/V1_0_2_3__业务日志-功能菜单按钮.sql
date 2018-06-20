
/**************【系统日志模块】功能菜单**************/

/*日志管理*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N0103';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N0103','日志管理','N01','','1'); 

/*业务日志*/

delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N010301';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N010301','业务日志','N0103','/xtgl/log/biz','1');

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N010301';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010301_#','N010301','#','log-biz:cx',''); 

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N010301%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010301_#'); 

commit;


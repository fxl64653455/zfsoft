
/**************【系统管理模块】功能菜单**************/
 
/*系统管理*/

delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N01';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N01','系统管理','N','','1'); 

/*系统设置*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N0101';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N0101','系统设置','N01','','1'); 

/*参数设置*/

delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N010101';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N010101','参数设置','N0101','/xtgl/xtsz/cxCssz','1');

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N010101';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010101_bc','N010101','bc','cssz:bc','1'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010101_#','N010101','#','cssz:cx',''); 

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N010101%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010101_#'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010101_bc'); 

/*缓存管理*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N010199';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N010199','缓存管理','N0101','/system/cache/cacheManagement','99'); 

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N010199';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010199_#','N010199','#','',''); 

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N010199%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010199_#'); 

/*权限管理*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N0102';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N0102','权限管理','N01','','2');

/*角色管理*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N010201';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N010201','角色管理','N0102','/authz/role/index','1');

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N010201';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010201_zj','N010201','zj','role:zj','1'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010201_xg','N010201','xg','role:xg','2'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010201_sc','N010201','sc','role:sc','3'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010201_ck','N010201','ck','role:ck','4'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010201_gnsq','N010201','gnsq','role:gnsq','5'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010201_sjsq','N010201','sjsq','role:sjsq','6'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010201_fpyh','N010201','fpyh','role:fpyh','7'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010201_#','N010201','#','role:cx',''); 

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N010201%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010201_#'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010201_ck'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010201_fpyh'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010201_gnsq'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010201_sc'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010201_sjsq'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010201_xg'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010201_zj'); 

/*用户管理*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N010202';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N010202','用户管理','N0102','/authz/user/cxYhxx','2'); 

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N010202';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010202_zj','N010202','zj','yhgl:zj','1'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010202_xg','N010202','xg','yhgl:xg','2'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010202_sc','N010202','sc','yhgl:sc','3'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010202_ck','N010202','ck','yhgl:ck','4'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010202_mmcsh','N010202','mmcsh','yhgl:mmcsh','5'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010202_qy','N010202','qy','yhgl:qy','6'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010202_ty','N010202','ty','yhgl:ty','7'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010202_#','N010202','#','yhgl:cx','');

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N010202%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010202_#'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010202_ck'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010202_mmcsh'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010202_qy'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010202_sc'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010202_ty'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010202_xg'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010202_zj');

/*基础数据*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N0105';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N0105','基础数据','N01','','5');

/*基础数据维护*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N010501';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N010501','基础数据维护','N0105','/xtgl/jcsj/cxJcsj','1'); 

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N010501';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010501_#','N010501','#','jcsj:cx',''); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010501_zj','N010501','zj','jcsj:zj','1'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010501_xg','N010501','xg','jcsj:xg','2'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N010501_sc','N010501','sc','jcsj:sc','3'); 

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N010501%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010501_#'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010501_sc'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010501_xg'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N010501_zj'); 

commit;


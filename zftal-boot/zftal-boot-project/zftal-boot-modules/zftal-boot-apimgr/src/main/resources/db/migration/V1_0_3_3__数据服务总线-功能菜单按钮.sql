
/**************【数据服务总线】功能菜单（业务系统功能菜单请从N05之后开始）**************/

/*接口申请、注册、审批*/

delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N03';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N03','接口管理','N','','2'); 

/*接口注册与发布*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N0301';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N0301','接口注册与发布','N03','','1'); 

/*接口数据来源*/

delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N030101';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N030101','我的数据源','N0301','/manager/api/database/index','1');

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N030101';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030101_#','N030101','#','data-api:cx',''); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030101_zj','N030101','zj','data-api:zj','1'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030101_xg','N030101','xg','data-api:xg','2');
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030101_sc','N030101','sc','data-api:sc','3');

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N030101%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030101_#'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030101_zj');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030101_xg');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030101_sc');


/*数据源接口*/

delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N030103';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N030103','数据接口发布','N0301','/manager/api/info/data/index','1');

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N030103';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030103_#','N030103','#','data-api:cx',''); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030103_zj','N030103','zj','data-api:zj','1'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030103_xg','N030103','xg','data-api:xg','2');
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030103_sc','N030103','sc','data-api:sc','3');
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030103_fb','N030103','fb','data-api:fb','4');
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030103_qxfb','N030103','qxfb','data-api:qxfb','4');

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N030103%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030103_#'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030103_zj');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030103_xg');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030103_sc');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030103_fb');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030103_qxfb'); 

/*业务系统接口*/

delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N030105';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N030105','业务接口代理','N0301','/manager/api/info/biz/index','2'); 

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N030105';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030105_#','N030105','#','biz-api:cx',''); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030105_zj','N030105','zj','biz-api:zj','1'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030105_xg','N030105','xg','biz-api:xg','2');
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030105_sc','N030105','sc','biz-api:sc','3');
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030105_fb','N030105','fb','biz-api:fb','4');
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030105_qxfb','N030105','qxfb','biz-api:qxfb','4');

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N030105%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030105_#'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030105_zj');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030105_xg');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030105_sc');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030105_fb');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030105_qxfb'); 

/*接口申请与审批*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N0305';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N0305','接口申请与审批','N03','','2'); 

/*我的应用*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N030501';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N030501','我的应用','N0305','/manager/api/app/index','1');

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N030501';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030501_zj','N030501','zj','app:zj','1'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030501_xg','N030501','xg','app:xg','2'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030501_sc','N030501','sc','app:sc','3'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030501_ck','N030501','ck','app:ck','4'); 
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030501_#','N030501','#','app:cx',''); 

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N030501%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030501_#'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030501_zj'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030501_xg'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030501_sc'); 
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030501_ck'); 

/*接口申请列表 */
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N030505';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N030505','我的接口申请','N0305','/manager/api/apply/index','1'); 

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N030505';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030505_#','N030505','#','apply-api:cx',''); 

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N030505%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030505_#'); 

/*接口审批列表*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N030510';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N030510','接口审批列表','N0305','/manager/api/audit/index','2'); 

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N030510';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N030510_#','N030510','#','audit-api:cx',''); 

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N030510%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N030510_#'); 

/*接口访问统计分析*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N0310';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N0310','接口访问统计分析','N03','','3'); 
delete from zftal_xtgl_gnmkczb where gnmkdm = 'N0310';
delete from zftal_xtgl_jsgnmkczb where gnczid like 'N0310%' and jsdm='admin';

/*访问监控分析 */
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N031005';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N031005','访问监控分析','N0310','/manager/api/analysis/index','1'); 

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N031005';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N031005_#','N031005','#','api-metric:cx',''); 

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N031005%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N031005_#'); 

/*访问记录查询*/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N031010';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N031010','访问记录查询','N0310','/manager/api/metric/index','2'); 

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N031010';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N031010_#','N031010','#','api-stat:cx',''); 

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N031010%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N031010_#'); 

commit;


/*操作代码

insert into zftal_xtgl_czdmb(CZDM, CZMC, XSSX, ANYS, YWMC) values ('#', '查询', '0', 'glyphicon glyphicon-search', 'Query');

commit;*/


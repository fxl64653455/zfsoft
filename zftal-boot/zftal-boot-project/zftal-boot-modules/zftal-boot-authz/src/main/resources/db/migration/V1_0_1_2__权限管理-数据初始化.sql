
/* 初始角色 */

delete from ZFTAL_XTGL_JSXXB where JSDM in('xs','admin','js');

insert into ZFTAL_XTGL_JSXXB (JSDM, JSMC, JSSM, JSLX, JSZT, YHM)
values ('xs', '学生', '学生角色', '1', '1', 'admin');
insert into ZFTAL_XTGL_JSXXB (JSDM, JSMC, JSSM, JSLX, JSZT, YHM)
values ('admin', '超级管理员', '超级管理员', '1', '1',  'admin');
insert into ZFTAL_XTGL_JSXXB (JSDM, JSMC, JSSM, JSLX, JSZT, YHM)
values ('js', '教师', '任课教师角色', '1', '1', 'admin');
commit;

/* 初始用户 */

delete from ZFTAL_XTGL_YHB where yhm = 'admin';
insert into ZFTAL_XTGL_YHB (YHM, XM, MM, SJHM, DZYX, YHZT) values ('admin', '超级管理员', '{MD5}wYOkMucG7a9A/0G1sqVI2Q==', '', '', '1');

/* 初始用户角色 */

delete from ZFTAL_XTGL_YHJSB where yhm = 'admin';
insert into ZFTAL_XTGL_YHJSB(yhm,jsdm) values ('admin', 'admin');

commit;





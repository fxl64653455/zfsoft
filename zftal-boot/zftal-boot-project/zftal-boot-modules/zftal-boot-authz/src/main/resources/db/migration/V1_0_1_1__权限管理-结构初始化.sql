
/* 菜单核心表：功能模块代码表、操作代码表、功能模块操作表*/

-- 功能模块代码表
-- Create table
create table ZFTAL_XTGL_GNMKDMB
(
  GNMKDM   VARCHAR2(10) not null,
  GNMKMC   VARCHAR2(50) not null,
  GNMKYWMC VARCHAR2(200),
  GNMKJC   VARCHAR2(50),
  GNMKYWJC VARCHAR2(50),
  FJGNDM   VARCHAR2(10) not null,
  DYYM     VARCHAR2(100),
  XSSX     VARCHAR2(2),
  GNSYDX   VARCHAR2(2),
  SFXS     VARCHAR2(1) default 1,
  SFZDYMK  VARCHAR2(1) default 0,
  TBLJ     VARCHAR2(200),
  primary key (GNMKDM)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_GNMKDMB  is '功能模块代码表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_GNMKDMB.GNMKDM  is '功能模块代码';
comment on column ZFTAL_XTGL_GNMKDMB.GNMKMC  is '功能模块名称';
comment on column ZFTAL_XTGL_GNMKDMB.GNMKYWMC  is '功能模块英文名称';
comment on column ZFTAL_XTGL_GNMKDMB.GNMKJC  is '功能模块简称，在最近使用或者我的应用中会用到';
comment on column ZFTAL_XTGL_GNMKDMB.GNMKYWJC  is '功能模块英文简称，在最近使用或者我的应用中会用到';
comment on column ZFTAL_XTGL_GNMKDMB.FJGNDM  is '父级功能代码';
comment on column ZFTAL_XTGL_GNMKDMB.DYYM  is '对应页面';
comment on column ZFTAL_XTGL_GNMKDMB.XSSX  is '显示顺序';
comment on column ZFTAL_XTGL_GNMKDMB.GNSYDX  is '功能使用对象(学生:xs;教师:js;管理:gl;空:面向所有用户)';
comment on column ZFTAL_XTGL_GNMKDMB.SFXS  is '是否显示(1:显示0:不显示)';
comment on column ZFTAL_XTGL_GNMKDMB.SFZDYMK  is '是否自定义模块：默认否；(1：是；0：否)';
comment on column ZFTAL_XTGL_GNMKDMB.TBLJ  is '菜单样式或菜单图标路径，在最近使用或者我的应用中会用到'; 

-- 操作代码表
-- Create table
create table ZFTAL_XTGL_CZDMB (
   CZDM VARCHAR2(10) not null,
   CZMC VARCHAR2(30) not null,
   ANYS VARCHAR2(60),
   YWMC VARCHAR2(200),
   primary key (CZDM)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_CZDMB  is '操作代码表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_CZDMB.CZDM  is '操作代码';
comment on column ZFTAL_XTGL_CZDMB.CZMC  is '操作名称';
comment on column ZFTAL_XTGL_CZDMB.ANYS  is '按钮样式';
comment on column ZFTAL_XTGL_CZDMB.YWMC  is '英文名称'; 

-- 功能模块操作表
-- Create table
create table ZFTAL_XTGL_GNMKCZB
(
  GNMKDM  	VARCHAR2(10) not null,
  CZDM    	VARCHAR2(10) not null,
  SFXS    	VARCHAR2(1) default 1,
  SFZDYCZ 	VARCHAR2(1) default 0,
  GNCZID 	VARCHAR2(20),
  QXDM 		VARCHAR2(20),
  CZMC    	VARCHAR2(30),
  YWMC    	VARCHAR2(200),
  XSSX 		VARCHAR2(2) default 1,
  primary key (GNMKDM, CZDM)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_GNMKCZB  is '功能模块操作表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_GNMKCZB.GNMKDM  is '功能模块代码';
comment on column ZFTAL_XTGL_GNMKCZB.CZDM  is '操作代码';
comment on column ZFTAL_XTGL_GNMKCZB.SFXS  is '是否显示(1:显示0:不显示)';
comment on column ZFTAL_XTGL_GNMKCZB.SFZDYCZ  is '是否自定义操作代码：默认否；(1：是；0：否)';
comment on column ZFTAL_XTGL_GNMKCZB.GNCZID is '功能操作ID';
comment on column ZFTAL_XTGL_GNMKCZB.QXDM is '权限代码';
comment on column ZFTAL_XTGL_GNMKCZB.CZMC  is '操作名称;如果该值为空则取对应zftal_xtgl_czdmb的操作名称';
comment on column ZFTAL_XTGL_GNMKCZB.YWMC  is '英文名称'; 
comment on column ZFTAL_XTGL_GNMKCZB.XSSX is '显示顺序';


/* 
 * 权限核心表：
 * 角色信息表、角色功能模块操作表（角色-菜单-按钮）、角色用户组关系表
 */

-- 角色信息表
-- Create table
create table ZFTAL_XTGL_JSXXB (
  JSDM   		VARCHAR2(32) default sys_guid() not null,
  JSMC   		VARCHAR2(50) not null,
  JSSM  		VARCHAR2(1000),
  JSLX   		VARCHAR2(2) default 1,
  JSZT			VARCHAR2(2) default 1,
  YHM    		VARCHAR2(32),
  primary key (JSDM)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_JSXXB  is '角色信息表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_JSXXB.JSDM  is '角色代码';
comment on column ZFTAL_XTGL_JSXXB.JSMC  is '角色名称';
comment on column ZFTAL_XTGL_JSXXB.JSSM  is '角色说明';
comment on column ZFTAL_XTGL_JSXXB.JSLX  is '角色类型代码(1:原生|2:继承|3:复制)';
comment on column ZFTAL_XTGL_JSXXB.JSZT  is '角色状态(0:不可用|1:正常|2:锁定)';
comment on column ZFTAL_XTGL_JSXXB.YHM  is '角色创建人ID'; 

-- 角色功能模块操作表（角色-菜单-按钮）
-- Create table
create table ZFTAL_XTGL_JSGNMKCZB (
  JSGNCZID VARCHAR2(32) default sys_guid() not null,
  GNCZID   VARCHAR2(20),
  JSDM     VARCHAR2(32) not null,
  primary key (JSGNCZID)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_JSGNMKCZB is '角色功能模块操作表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_JSGNMKCZB.JSGNCZID is '角色功能操作ID';
comment on column ZFTAL_XTGL_JSGNMKCZB.GNCZID is '功能操作ID';
comment on column ZFTAL_XTGL_JSGNMKCZB.JSDM is '角色代码'; 

-- 角色用户组关系表
-- Create table
create table ZFTAL_XTGL_JSYHZGXB (
  JSDM   			VARCHAR2(32) not null,
  YHZ   			VARCHAR2(32) not null,
  primary key (JSDM, YHZ)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_JSYHZGXB  is '角色用户组关系表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_JSYHZGXB.JSDM  is '角色代码';
comment on column ZFTAL_XTGL_JSYHZGXB.YHZ  is '用户组';

/* 
 * 权限核心表：
 * 用户信息表、用户详情表（实际应用过程中应该是一个虚拟表，如：视图）、用户角色表、用户组信息表、用户组用户关系表、用户组功能模块操作表（用户组-菜单-按钮）
 */

-- 用户信息表
-- Create table
create table ZFTAL_XTGL_YHB
(
  YHM    VARCHAR2(32) not null,
  XM     VARCHAR2(50) not null,
  MM     VARCHAR2(70),
  SJHM   VARCHAR2(20),
  DZYX   VARCHAR2(50),
  YHZT   VARCHAR2(1) default 1,
  primary key (YHM)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_YHB  is '用户表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_YHB.YHM  is '用户名';
comment on column ZFTAL_XTGL_YHB.XM  is '姓名';
comment on column ZFTAL_XTGL_YHB.MM  is '密码';
comment on column ZFTAL_XTGL_YHB.SJHM  is '手机号码';
comment on column ZFTAL_XTGL_YHB.DZYX  is '电子邮箱';
comment on column ZFTAL_XTGL_YHB.YHZT  is '用户状态(0:不可用|1:正常|2:锁定)';

-- 用户角色表
-- Create table
create table ZFTAL_XTGL_YHJSB (
  YHM 	VARCHAR2(32) not null,
  JSDM  VARCHAR2(32) not null,
  primary key (YHM, JSDM)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_YHJSB  is '用户角色表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_YHJSB.YHM  is '用户名'; 
comment on column ZFTAL_XTGL_YHJSB.JSDM  is '角色代码';

-- 用户组信息表
-- Create table
create table ZFTAL_XTGL_YHZB(
  YHZ   			VARCHAR2(32) default sys_guid() not null,
  YHZMC				VARCHAR2(50),
  CJSJ				VARCHAR2(50) default to_char(sysdate ,'yyyy-mm-dd hh24:mi:ss'),
  primary key (YHZ)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_YHZB  is '用户组信息表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_YHZB.YHZ  is '用户组信息ID';
comment on column ZFTAL_XTGL_YHZB.YHZMC  is '用户组名称';
comment on column ZFTAL_XTGL_YHZB.CJSJ  is '用户组创建时间';

-- 用户组用户关系表
-- Create table
create table ZFTAL_XTGL_YHZYHGXB (
  YHZ   			VARCHAR2(32) not null,
  YHM   			VARCHAR2(32) not null,
  primary key (YHM, YHZ)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_YHZYHGXB  is '用户用户组关系表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_YHZYHGXB.YHZ  is '用户组';
comment on column ZFTAL_XTGL_YHZYHGXB.YHM  is '用户名';

-- 用户组功能模块操作表（用户组-菜单-按钮）
-- Create table
create table ZFTAL_XTGL_YHZGNMKCZB (
  YHZ     VARCHAR2(32) not null,
  GNCZID   VARCHAR2(20) not null,
  primary key (GNCZID, YHZ)
) ;
-- Add comments to the table 
comment on table ZFTAL_XTGL_YHZGNMKCZB  is '用户组功能模块操作表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_YHZGNMKCZB.YHZ is '用户组代码'; 
comment on column ZFTAL_XTGL_YHZGNMKCZB.GNCZID is '功能操作ID';

/* 权限附属表1：部门代码表、部门信息详情表、职务代码表、用户-部门关系表、部门-职务关系表（岗位）、用户-岗位关系表*/


-- Create table
create table ZFTAL_XTGL_BMDMB (
  BMID   		VARCHAR2(32) default sys_guid(),
  BMDM      	VARCHAR2(20) not null,
  BMMC      	VARCHAR2(60),
  BMJC      	VARCHAR2(20),
  SSBM		   	VARCHAR2(32),
  primary key (BMID)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_BMDMB  is '部门代码表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_BMDMB.BMID  is '部门ID';
comment on column ZFTAL_XTGL_BMDMB.BMDM  is '部门代码';
comment on column ZFTAL_XTGL_BMDMB.BMMC  is '部门名称';
comment on column ZFTAL_XTGL_BMDMB.BMJC  is '部门简称';
comment on column ZFTAL_XTGL_BMDMB.SSBM  is '上级部门';

-- Create table
create table ZFTAL_XTGL_BMJCXXB (
  BMID   		VARCHAR2(32) not null,
  BMLX      	VARCHAR2(20),
  XZFZRJGH  	VARCHAR2(20),
  DZZFZRJGH 	VARCHAR2(20),
  BMSX      	VARCHAR2(100),
  BMDH      	VARCHAR2(50),
  BMYX      	VARCHAR2(80),
  BMFZR     	VARCHAR2(50),
  SFZF      	VARCHAR2(20),
  ZMSS      	VARCHAR2(50),
  BMDZ      	VARCHAR2(2000),
  DZXYDM    	VARCHAR2(20),
  YB        	VARCHAR2(20),
  CZ        	VARCHAR2(20),
  QH        	VARCHAR2(10),
  primary key (BMID)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_BMJCXXB  is '部门信息详情表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_BMJCXXB.BMID  is '部门ID';
comment on column ZFTAL_XTGL_BMJCXXB.BMLX  is '部门类型';
comment on column ZFTAL_XTGL_BMJCXXB.XZFZRJGH  is '行政负责人教工号';
comment on column ZFTAL_XTGL_BMJCXXB.DZZFZRJGH  is '党部门负责人教工号';
comment on column ZFTAL_XTGL_BMJCXXB.BMSX  is '部门顺序';
comment on column ZFTAL_XTGL_BMJCXXB.BMDH  is '部门电话';
comment on column ZFTAL_XTGL_BMJCXXB.BMYX  is '部门邮箱';
comment on column ZFTAL_XTGL_BMJCXXB.BMFZR  is '部门负责人';
comment on column ZFTAL_XTGL_BMJCXXB.SFZF  is '是否作废';
comment on column ZFTAL_XTGL_BMJCXXB.ZMSS  is '字母搜索（保存每个汉字大写的首字母）';
comment on column ZFTAL_XTGL_BMJCXXB.BMDZ  is '部门地址';
comment on column ZFTAL_XTGL_BMJCXXB.DZXYDM  is '对照学院代码';
comment on column ZFTAL_XTGL_BMJCXXB.YB  is '邮编';
comment on column ZFTAL_XTGL_BMJCXXB.CZ  is '传真';
comment on column ZFTAL_XTGL_BMJCXXB.QH  is '区号';

-- Create table
create table ZFTAL_XTGL_ZWDMB (
  ZWID   	VARCHAR2(32) default sys_guid(),
  ZWDM   	VARCHAR2(50) not null,
  ZWMC   	VARCHAR2(50) not null,
  ZWSM  	VARCHAR2(1000),
  primary key (ZWID)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_ZWDMB  is '职务代码表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_ZWDMB.ZWID  is '职务ID';
comment on column ZFTAL_XTGL_ZWDMB.ZWDM  is '职务编码';
comment on column ZFTAL_XTGL_ZWDMB.ZWMC  is '职务名称';
comment on column ZFTAL_XTGL_ZWDMB.ZWSM  is '职务说明';

-- Create table
create table ZFTAL_XTGL_GWXXB (
  GWID   	VARCHAR2(32) default sys_guid(),
  BMID   	VARCHAR2(32) not null,
  ZWID   	VARCHAR2(32) not null,
  GWSM   	VARCHAR2(50) not null,
  YHM       VARCHAR2(32) not null,
  primary key (GWID)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_GWXXB  is '岗位信息表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_GWXXB.GWID  is '岗位信息ID';
comment on column ZFTAL_XTGL_GWXXB.BMID  is '部门ID';
comment on column ZFTAL_XTGL_GWXXB.ZWID  is '职务ID';
comment on column ZFTAL_XTGL_GWXXB.GWSM  is '岗位说明';
comment on column ZFTAL_XTGL_YHZYHGXB.YHM  is '用户名';

-- ----------------------------
-- Table structure for ZFTAL_XTGL_SJZYGZ
-- ----------------------------
CREATE TABLE "ZFTAL_XTGL_SJZYGZ" (
"GZID" VARCHAR2(20 BYTE) NOT NULL ,
"ZYID" VARCHAR2(20 BYTE) NULL ,
"GZTGZ" VARCHAR2(20 BYTE) NULL ,
"GZMC" VARCHAR2(30 BYTE) NULL ,
"GZSM" VARCHAR2(100 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "ZFTAL_XTGL_SJZYGZ" IS '数据资源规则';
COMMENT ON COLUMN "ZFTAL_XTGL_SJZYGZ"."GZID" IS '规则ID';
COMMENT ON COLUMN "ZFTAL_XTGL_SJZYGZ"."ZYID" IS '资源ID';
COMMENT ON COLUMN "ZFTAL_XTGL_SJZYGZ"."GZTGZ" IS '规则提供者';
COMMENT ON COLUMN "ZFTAL_XTGL_SJZYGZ"."GZMC" IS '规则名称';
COMMENT ON COLUMN "ZFTAL_XTGL_SJZYGZ"."GZSM" IS '规则说明';

-- ----------------------------
-- Records of ZFTAL_XTGL_SJZYGZ
-- ----------------------------
INSERT INTO "ZFTAL_XTGL_SJZYGZ" VALUES ('STUDENT_SZXY', 'student', 'yhglService', '用户所在学院', '查询用户所在学院的学生');
INSERT INTO "ZFTAL_XTGL_SJZYGZ" VALUES ('TEACHER_SZBM', 'teacher', 'yhglService', '用户所在部门', '查询用户所在部门的教职工');
INSERT INTO "ZFTAL_XTGL_SJZYGZ" VALUES ('STUDENT_YHXY', 'student', 'bmdmService', '用户所管理的学院', '查询用户管理学院的学生');
INSERT INTO "ZFTAL_XTGL_SJZYGZ" VALUES ('STUDENT_YHZY', 'student', 'zydmService', '用户所管理的专业', '查询用户管理专业的学生');

-- ----------------------------
-- Table structure for ZFTAL_XTGL_SJZYB
-- ----------------------------
CREATE TABLE "ZFTAL_XTGL_SJZYB" (
"ZYID" VARCHAR2(20 BYTE) NOT NULL ,
"ZYMC" VARCHAR2(30 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "ZFTAL_XTGL_SJZYB" IS '数据资源表';
COMMENT ON COLUMN "ZFTAL_XTGL_SJZYB"."ZYID" IS '资源ID';
COMMENT ON COLUMN "ZFTAL_XTGL_SJZYB"."ZYMC" IS '资源名称';

-- ----------------------------
-- Records of ZFTAL_XTGL_SJZYB
-- ----------------------------
INSERT INTO "ZFTAL_XTGL_SJZYB" VALUES ('student', '学生');
INSERT INTO "ZFTAL_XTGL_SJZYB" VALUES ('teacher', '教职工');

-- ----------------------------
-- Indexes structure for table ZFTAL_XTGL_SJZYGZ
-- ----------------------------

-- ----------------------------
-- Checks structure for table ZFTAL_XTGL_SJZYGZ
-- ----------------------------
ALTER TABLE "ZFTAL_XTGL_SJZYGZ" ADD CHECK ("GZID" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_SJZYGZ" ADD CHECK ("GZID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table ZFTAL_XTGL_SJZYGZ
-- ----------------------------
ALTER TABLE "ZFTAL_XTGL_SJZYGZ" ADD PRIMARY KEY ("GZID");

-- ----------------------------
-- Indexes structure for table ZFTAL_XTGL_SJZYB
-- ----------------------------

-- ----------------------------
-- Checks structure for table ZFTAL_XTGL_SJZYB
-- ----------------------------
ALTER TABLE "ZFTAL_XTGL_SJZYB" ADD CHECK ("ZYID" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_SJZYB" ADD CHECK ("ZYID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table ZFTAL_XTGL_SJZYB
-- ----------------------------
ALTER TABLE "ZFTAL_XTGL_SJZYB" ADD PRIMARY KEY ("ZYID");

-- ----------------------------
-- Table structure for ZFTAL_XTGL_JSSJQXB
-- ----------------------------
CREATE TABLE "ZFTAL_XTGL_JSSJQXB" (
"JSDM" VARCHAR2(32 BYTE) NOT NULL ,
"GZID" VARCHAR2(20 BYTE) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "ZFTAL_XTGL_JSSJQXB" IS '角色数据权限';
COMMENT ON COLUMN "ZFTAL_XTGL_JSSJQXB"."JSDM" IS '角色代码';
COMMENT ON COLUMN "ZFTAL_XTGL_JSSJQXB"."GZID" IS '规则ID';

-- ----------------------------
-- Records of ZFTAL_XTGL_JSSJQXB
-- ----------------------------
INSERT INTO "ZFTAL_XTGL_JSSJQXB" VALUES ('80feacbf55b9ea80e1f094fd5edd7625', 'TEACHER_SZBM');
INSERT INTO "ZFTAL_XTGL_JSSJQXB" VALUES ('8236f6250194193865c91615d656d39d', 'TEACHER_SZBM');
INSERT INTO "ZFTAL_XTGL_JSSJQXB" VALUES ('9ab3b245f520fc7abe0ef5b77ca76d4e', 'TEACHER_SZBM');
INSERT INTO "ZFTAL_XTGL_JSSJQXB" VALUES ('admin', 'STUDENT_YHXY');

-- ----------------------------
-- Indexes structure for table ZFTAL_XTGL_JSSJQXB
-- ----------------------------

-- ----------------------------
-- Checks structure for table ZFTAL_XTGL_JSSJQXB
-- ----------------------------
ALTER TABLE "ZFTAL_XTGL_JSSJQXB" ADD CHECK ("JSDM" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JSSJQXB" ADD CHECK ("GZID" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JSSJQXB" ADD CHECK ("JSDM" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JSSJQXB" ADD CHECK ("GZID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table ZFTAL_XTGL_JSSJQXB
-- ----------------------------
ALTER TABLE "ZFTAL_XTGL_JSSJQXB" ADD PRIMARY KEY ("JSDM", "GZID");

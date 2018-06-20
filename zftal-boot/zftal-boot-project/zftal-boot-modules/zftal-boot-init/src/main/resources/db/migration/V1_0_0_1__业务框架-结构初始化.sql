
declare

  tb_name VARCHAR2(50);
  cursor table_cursor is
    select t.table_name
      from all_tables t
     where upper(t.table_name) in ('ZFTAL_XTGL_CSSZB','ZFTAL_XTGL_XTSZB','ZFTAL_XTGL_JCSJLXB','ZFTAL_XTGL_JCSJB')
       and upper(t.owner) = upper((select username from user_users));

begin

  open table_cursor;
  LOOP
    exit when table_cursor%notfound;
    tb_name := null;
    FETCH table_cursor into tb_name;
    if tb_name is null then
      dbms_output.put_line('Basic tables have been cleared. ');
      exit;
    end if;
    dbms_output.put_line('drop table ' || tb_name);
    Execute Immediate ('drop table ' || tb_name);
  
  end LOOP;
  close table_cursor;

end;
/

commit;

 -- Create table
create table ZFTAL_XTGL_XTSZB
(
  XXDM     VARCHAR2(10) not null,
  XXMC     VARCHAR2(100) not null,
  DQND     VARCHAR2(4),
  DQXN     VARCHAR2(9),
  DQXQ     VARCHAR2(10),
  AUTH_SHA VARCHAR2(1000)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_XTSZB  is '系统设置表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_XTSZB.XXDM  is '学校代码';
comment on column ZFTAL_XTGL_XTSZB.XXMC  is '学校名称';
comment on column ZFTAL_XTGL_XTSZB.DQND  is '当前年度';
comment on column ZFTAL_XTGL_XTSZB.DQXN  is '当前学年';
comment on column ZFTAL_XTGL_XTSZB.DQXQ  is '当前学期';
comment on column ZFTAL_XTGL_XTSZB.AUTH_SHA is '授权使用';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_XTGL_XTSZB add constraint PK_XG_XTGL_XTSZB primary key (XXDM);

-- Create table
create table ZFTAL_XTGL_CSSZB
(
  ID  VARCHAR2(32) default sys_guid() not null,
  ZDM      VARCHAR2(30) not null,
  ZDZ      VARCHAR2(50),
  SSMK     VARCHAR2(300),
  ZS       VARCHAR2(300),
  BZ       VARCHAR2(240),
  SSGNMKDM VARCHAR2(30),
  ZDZYQ    VARCHAR2(255),
  ZDLX     NUMBER default 2 not null,
  ZDLY     VARCHAR2(1000),
   primary key (ZDM)
);
-- Add comments to the table 
comment on table ZFTAL_XTGL_CSSZB  is '参数设置表';
-- Add comments to the columns 
comment on column ZFTAL_XTGL_CSSZB.ID  is '参数设置表ID';
comment on column ZFTAL_XTGL_CSSZB.ZDM  is '字段名';
comment on column ZFTAL_XTGL_CSSZB.ZDZ  is '字段值';
comment on column ZFTAL_XTGL_CSSZB.SSMK  is '所属模块';
comment on column ZFTAL_XTGL_CSSZB.ZS  is '注释';
comment on column ZFTAL_XTGL_CSSZB.BZ  is '备注';
comment on column ZFTAL_XTGL_CSSZB.SSGNMKDM  is '所属功能模块代码';
comment on column ZFTAL_XTGL_CSSZB.ZDZYQ  is '字段值要求;  例如 required:true;stringMaxLength:30;range:[0,100] (多个用;隔开)';
comment on column ZFTAL_XTGL_CSSZB.ZDLX  is '字段类型： 1 ：表示 下拉框，2：表示输入框，3：表示单选框，4：表示多选框';
comment on column ZFTAL_XTGL_CSSZB.ZDLY  is '字段来源：
     数据库        ：格式如 database:查询SQL 例如 database:select jg_id as key,jg_mc as value from zftal_xtgl_jgdmb
     xml数据       ：格式如 basedata:(baseData.xml)文件中的id 例如 basedata:isValid
     固定值        ：格式如 fixed:固定值1,固定值2,...(多个用,隔开) 例如  fixed:aaa,bbb,ccc
     空            ：'; 
 
-- ----------------------------
-- Table structure for ZFTAL_XTGL_JCSJLXB
-- ----------------------------
CREATE TABLE "ZFTAL_XTGL_JCSJLXB" (
"LXDM" VARCHAR2(20 BYTE) NOT NULL ,
"LXMC" VARCHAR2(60 BYTE) NOT NULL ,
"XTJB" VARCHAR2(20 BYTE) NULL ,
"YZ" VARCHAR2(200 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "ZFTAL_XTGL_JCSJLXB" IS '基础数据类型表';
COMMENT ON COLUMN "ZFTAL_XTGL_JCSJLXB"."LXDM" IS '类型代码';
COMMENT ON COLUMN "ZFTAL_XTGL_JCSJLXB"."LXMC" IS '类型名称';
COMMENT ON COLUMN "ZFTAL_XTGL_JCSJLXB"."YZ" IS '验证,格式:【表名：字段名】';

-- ----------------------------
-- Table structure for ZFTAL_XTGL_JCSJB
-- ----------------------------
CREATE TABLE "ZFTAL_XTGL_JCSJB" (
"LX" VARCHAR2(20 BYTE) NOT NULL ,
"DM" VARCHAR2(20 BYTE) NOT NULL ,
"MC" VARCHAR2(200 BYTE) NOT NULL ,
"XTJB" VARCHAR2(20 BYTE) DEFAULT 'xt'  NOT NULL ,
"ZT" VARCHAR2(1 BYTE) DEFAULT '1'  NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;
COMMENT ON TABLE "ZFTAL_XTGL_JCSJB" IS '基础数据表';
COMMENT ON COLUMN "ZFTAL_XTGL_JCSJB"."LX" IS '类型';
COMMENT ON COLUMN "ZFTAL_XTGL_JCSJB"."DM" IS '代码';
COMMENT ON COLUMN "ZFTAL_XTGL_JCSJB"."MC" IS '名称';
COMMENT ON COLUMN "ZFTAL_XTGL_JCSJB"."ZT" IS '启用状态';

-- ----------------------------
-- Checks structure for table ZFTAL_XTGL_JCSJLXB
-- ----------------------------
ALTER TABLE "ZFTAL_XTGL_JCSJLXB" ADD CHECK ("LXDM" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJLXB" ADD CHECK ("LXMC" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJLXB" ADD CHECK ("LXDM" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJLXB" ADD CHECK ("LXMC" IS NOT NULL);

-- ----------------------------
-- Checks structure for table ZFTAL_XTGL_JCSJB
-- ----------------------------
ALTER TABLE "ZFTAL_XTGL_JCSJB" ADD CHECK ("LX" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJB" ADD CHECK ("DM" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJB" ADD CHECK ("MC" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJB" ADD CHECK ("XTJB" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJB" ADD CHECK ("ZT" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJB" ADD CHECK ("LX" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJB" ADD CHECK ("DM" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJB" ADD CHECK ("MC" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJB" ADD CHECK ("XTJB" IS NOT NULL);
ALTER TABLE "ZFTAL_XTGL_JCSJB" ADD CHECK ("ZT" IS NOT NULL);

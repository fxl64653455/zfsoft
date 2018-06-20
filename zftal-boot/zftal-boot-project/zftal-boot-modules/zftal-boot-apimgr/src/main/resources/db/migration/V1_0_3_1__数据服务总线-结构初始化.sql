
declare

  tb_name VARCHAR2(50);
  cursor table_cursor is
    select t.table_name
      from all_tables t
     where (upper(t.table_name) like 'ZFTAL_API%' or upper(t.table_name) like 'ZFTAL_MQ%' or
           upper(t.table_name) in ('ZFTAL_API_APP','ZFTAL_API_DATA','ZFTAL_API_INFO','ZFTAL_API_DEPLOY','ZFTAL_API_APPLY','ZFTAL_API_AUDIT', 'ZFTAL_METRIC_DATA'))
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

/*1、ZFTAL_API_APP 我的应用信息表：*/

-- Create table
create table ZFTAL_API_APP (
  APP_ID      	VARCHAR2(32) default sys_guid() not null,
  APP_NAME    	VARCHAR2(100),
  APP_DESC    	CLOB null,
  APP_KEY    	VARCHAR2(32),
  APP_SECRET    VARCHAR2(1024),
  APP_OWNER     VARCHAR2(32)
);
-- Add comments to the table 
comment on table ZFTAL_API_APP  is '我的应用信息表';
-- Add comments to the columns 
comment on column ZFTAL_API_APP.APP_ID  is '应用ID编号';
comment on column ZFTAL_API_APP.APP_NAME  is '应用名称';
comment on column ZFTAL_API_APP.APP_DESC  is '应用描述';
comment on column ZFTAL_API_APP.APP_KEY  is '应用Key';
comment on column ZFTAL_API_APP.APP_SECRET  is '应用Secret';
comment on column ZFTAL_API_APP.APP_OWNER  is '应用所属人ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_API_APP add constraint PK_API_APP primary key (APP_ID);

/*2、ZFTAL_API_DATABASE 接口数据库信息表：*/

-- Create table
create table ZFTAL_API_DATABASE (
  DB_ID      	VARCHAR2(32) default sys_guid() not null,
  DB_NAME    	VARCHAR2(50),
  DB_NAME_CN    VARCHAR2(100),
  DB_TYPE    	VARCHAR2(50),
  DB_URL    	VARCHAR2(200),
  DB_USERNAME   VARCHAR2(50),
  DB_PASSWORD   VARCHAR2(50),
  DB_DESC    	CLOB null,
  DB_OWNER     	VARCHAR2(32)
);
-- Add comments to the table 
comment on table ZFTAL_API_DATABASE  is '接口数据库信息表（存储接口数据来源的数据库连接信息）';
-- Add comments to the columns 
comment on column ZFTAL_API_DATABASE.DB_ID  is '数据库ID编号';
comment on column ZFTAL_API_DATABASE.DB_NAME  is '数据库名称';
comment on column ZFTAL_API_DATABASE.DB_NAME_CN  is '数据库中文名称';
comment on column ZFTAL_API_DATABASE.DB_TYPE  is '数据库类型';
comment on column ZFTAL_API_DATABASE.DB_URL  is '数据库连接地址';
comment on column ZFTAL_API_DATABASE.DB_USERNAME  is '数据库账号（已加密）';
comment on column ZFTAL_API_DATABASE.DB_PASSWORD  is '数据库密码（已加密）';
comment on column ZFTAL_API_DATABASE.DB_DESC  is '数据库描述：该数据库相关简述';
comment on column ZFTAL_API_DATABASE.DB_OWNER  is '接口数据库信息创建人ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_API_DATABASE add constraint PK_API_DATABASE primary key (DB_ID);


/*3、ZFTAL_API_DATA 接口数据来源信息表：*/

-- Create table
create table ZFTAL_API_DATA (
  DATA_ID      VARCHAR2(32) default sys_guid() not null,
  DATA_NAME    VARCHAR2(100),
  DATA_TIME    VARCHAR2(30) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
  DATA_INFO    VARCHAR2(200),
  DATA_FOR	   VARCHAR2(32),
  DATA_FROM    VARCHAR2(2),
  DATA_DESC    CLOB null,
  DATA_OWNER   VARCHAR2(32)
);
-- Add comments to the table 
comment on table ZFTAL_API_DATA  is '接口数据来源信息表（存储接口的源数据来源描述信息）';
-- Add comments to the columns 
comment on column ZFTAL_API_DATA.DATA_ID  is '接口数据ID编号';
comment on column ZFTAL_API_DATA.DATA_NAME  is '接口数据名称';
comment on column ZFTAL_API_DATA.DATA_TIME  is '接口数据创建时间';
comment on column ZFTAL_API_DATA.DATA_FOR  is '接口数据来源数据库ID';
comment on column ZFTAL_API_DATA.DATA_INFO  is '接口数据详细描述';
comment on column ZFTAL_API_DATA.DATA_FROM  is '接口数据来源：0: 数据查询表 、1： 数据查询SQL';
comment on column ZFTAL_API_DATA.DATA_DESC  is '接口数据描述：JSON格式的对象，用于存储不同接口来源的描述信息';
comment on column ZFTAL_API_DATA.DATA_OWNER  is '接口数据创建人ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_API_DATA add constraint PK_API_DATA primary key (DATA_ID);

/*4、ZFTAL_API_INFO 接口信息描述表：*/

-- Create table
create table ZFTAL_API_INFO (
  API_ID      VARCHAR2(32) default sys_guid() not null,
  API_NAME    VARCHAR2(100),
  API_TYPE    VARCHAR2(20),
  API_FOR	  VARCHAR2(32),
  API_INFO    VARCHAR2(1000),
  API_DETAIL  CLOB,
  API_STATUS  VARCHAR2(2),
  API_DESC    CLOB null,
  API_OWNER   VARCHAR2(32),
  API_TIME    VARCHAR2(30) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')
);
-- Add comments to the table 
comment on table ZFTAL_API_INFO  is '接口信息描述表';
-- Add comments to the columns 
comment on column ZFTAL_API_INFO.API_ID  is '接口ID编号';
comment on column ZFTAL_API_INFO.API_NAME  is '接口名称';
comment on column ZFTAL_API_INFO.API_TYPE  is '接口类型：biz、data；用于区别业务接口和数据源接口，不显示';
comment on column ZFTAL_API_INFO.API_FOR  is '接口数据来源：当API_TYPE为data时该值为ZFTAL_API_DATA表的DATA_ID';
comment on column ZFTAL_API_INFO.API_INFO  is '接口用途描述';
comment on column ZFTAL_API_INFO.API_DETAIL  is '接口详细说明';
comment on column ZFTAL_API_INFO.API_STATUS  is '接口状态：0:不可用、1：可用';
comment on column ZFTAL_API_INFO.API_DESC  is '接口描述信息：JSON格式的对象，用于存储不同接口的描述信息';
comment on column ZFTAL_API_INFO.API_OWNER  is '接口录入人ID';
comment on column ZFTAL_API_INFO.API_TIME  is '接口录入时间：数据库时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_API_INFO add constraint PK_API_INFO primary key (API_ID);

/*5、ZFTAL_API_DEPLOY 接口发布信息表：*/

-- Create table
create table ZFTAL_API_DEPLOY (
  DEPLOY_ID      VARCHAR2(32) default sys_guid() not null,
  DEPLOY_FOR     VARCHAR2(32),
  DEPLOY_ICON    BLOB null,
  DEPLOY_STATUS  VARCHAR2(2),
  DEPLOY_TYPE    VARCHAR2(20),
  DEPLOY_METHOD  VARCHAR2(20),
  DEPLOY_ADDR    VARCHAR2(200),
  DEPLOY_VER     VARCHAR2(20),
  DEPLOY_DESC    CLOB null,
  DEPLOY_OWNER   VARCHAR2(32),
  DEPLOY_TIME    VARCHAR2(30) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')
);
-- Add comments to the table 
comment on table ZFTAL_API_DEPLOY  is '接口发布信息表';
-- Add comments to the columns 
comment on column ZFTAL_API_DEPLOY.DEPLOY_ID  is '接口发布信息表ID编号';
comment on column ZFTAL_API_DEPLOY.DEPLOY_FOR  is '发布的接口信息ID';
comment on column ZFTAL_API_DEPLOY.DEPLOY_ICON  is '接口图标';
comment on column ZFTAL_API_DEPLOY.DEPLOY_STATUS  is '接口状态：0:不可用、1：可用';
comment on column ZFTAL_API_DEPLOY.DEPLOY_TYPE  is '接口发布类型：Rest、WS（Axis）、WS（Axiss）、WS（CXF）';
comment on column ZFTAL_API_DEPLOY.DEPLOY_METHOD  is '接口请求方式：GET、POST';
comment on column ZFTAL_API_DEPLOY.DEPLOY_ADDR  is '接口发布Addr:指对外暴露的访问地址';
comment on column ZFTAL_API_DEPLOY.DEPLOY_VER  is '接口发布版本：自动生成';
comment on column ZFTAL_API_DEPLOY.DEPLOY_DESC  is '接口发布信息：JSON格式的对象，用于存储接口输入输出信息';
comment on column ZFTAL_API_DEPLOY.DEPLOY_OWNER  is '接口发布人ID';
comment on column ZFTAL_API_DEPLOY.DEPLOY_TIME  is '接口发布时间：数据库时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_API_DEPLOY add constraint PK_API_DEPLOY primary key (DEPLOY_ID);


/*6、ZFTAL_API_APPLAY 接口申请信息表：*/

-- Create table
create table ZFTAL_API_APPLY (
  APPLY_ID      VARCHAR2(32) default sys_guid() not null,
  APPLY_FOR     VARCHAR2(32),
  APPLY_TO      VARCHAR2(500),
  APPLY_USER    VARCHAR2(32),
  APPLY_STATUS  VARCHAR2(2),
  APPLY_TIME    VARCHAR2(30) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')
);
-- Add comments to the table 
comment on table ZFTAL_API_APPLY  is '接口申请信息表';
-- Add comments to the columns 
comment on column ZFTAL_API_APPLY.APPLY_ID  is '接口申请ID编号';
comment on column ZFTAL_API_APPLY.APPLY_FOR  is '申请的接口ID';
comment on column ZFTAL_API_APPLY.APPLY_TO  is '接口应用场景';
comment on column ZFTAL_API_APPLY.APPLY_USER  is '申请人ID';
comment on column ZFTAL_API_APPLY.APPLY_STATUS  is '申请状态：0:待审核、1：审核通过、2：审核不通过';
comment on column ZFTAL_API_APPLY.APPLY_TIME  is '接口申请时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_API_APPLY add constraint PK_API_APPLY primary key (APPLY_ID);
  
/*7、ZFTAL_API_AUDIT 接口申请审批信息表：*/

-- Create table
create table ZFTAL_API_AUDIT (
  AUDIT_ID      VARCHAR2(32) default sys_guid() not null,
  AUDIT_TIME    VARCHAR2(30) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
  AUDIT_FOR     VARCHAR2(32),
  AUDIT_USER    VARCHAR2(32),
  AUDIT_OPINION  VARCHAR2(200)
);
-- Add comments to the table 
comment on table ZFTAL_API_AUDIT  is '接口申请审批信息表';
-- Add comments to the columns 
comment on column ZFTAL_API_AUDIT.AUDIT_ID  is '接口申请审批ID编号';
comment on column ZFTAL_API_AUDIT.AUDIT_TIME  is '审批时间';
comment on column ZFTAL_API_AUDIT.AUDIT_FOR  is '审批的接口申请ID';
comment on column ZFTAL_API_AUDIT.AUDIT_USER  is '审批人ID';
comment on column ZFTAL_API_AUDIT.AUDIT_OPINION  is '审批意见';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_API_AUDIT add constraint PK_API_AUDIT primary key (AUDIT_ID);

/*8、ZFTAL_API_USECASE 接口使用示例表：*/

-- Create table
create table ZFTAL_API_USECASE (
  USECASE_ID     VARCHAR2(32) default sys_guid() not null,
  USECASE_FOR    VARCHAR2(32),
  USECASE_LANG   VARCHAR2(30)
);
-- Add comments to the table 
comment on table ZFTAL_API_USECASE  is '接口使用示例表(用于关联发布的接口与代码示例模板关系)';
-- Add comments to the columns 
comment on column ZFTAL_API_USECASE.USECASE_ID  is '接口使用示例表ID编号';
comment on column ZFTAL_API_USECASE.USECASE_FOR  is '接口发布信息表ID编号';
comment on column ZFTAL_API_USECASE.USECASE_LANG  is '语言版本：Java、JavaScript、PHP、Python...等';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_API_USECASE add constraint PK_API_USECASE primary key (USECASE_ID);

/*8、ZFTAL_API_METRICS 接口调用统计分析表：

-- Create table
create table ZFTAL_API_METRICS (
  METRIC_ID     VARCHAR2(32) default sys_guid() not null,
  METRIC_COUNT    VARCHAR2(32),
  METRIC_MEAN_RATE   VARCHAR2(30),
  METRIC_1_MINUTE_RATE VARCHAR2(30),
  METRIC_5_MINUTE_RATE VARCHAR2(30),
  METRIC_10_MINUTE_RATE VARCHAR2(30),
  METRIC_TIME    VARCHAR2(30) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')
);
-- Add comments to the table 
comment on table ZFTAL_API_USECASE  is '接口调用统计分析表';
-- Add comments to the columns 
comment on column ZFTAL_API_USECASE.USECASE_ID  is '接口使用示例表ID编号';
comment on column ZFTAL_API_USECASE.USECASE_FOR  is '接口发布信息表ID编号';
comment on column ZFTAL_API_USECASE.USECASE_LANG  is '语言版本：Java、JavaScript、PHP、Python...等';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_API_USECASE add constraint PK_API_USECASE primary key (USECASE_ID);
*/

/*1、ZFTAL_METRIC_DATA 服务接口客户端请求记录：*/

-- Create table
create table ZFTAL_METRIC_DATA (
  ID      			VARCHAR2(32) default sys_guid() not null,
  BIZ_ID   			VARCHAR2(32),
  BIZ_NAME			VARCHAR2(100),
  APP_KEY			VARCHAR2(32) not null,
  ADDR    			VARCHAR2(50),
  URI    			VARCHAR2(500),
  DEVICE_NAME   	VARCHAR2(50) default 'Unknown',
  OS_NAME    		VARCHAR2(50) default 'Unknown',
  OS_VER			VARCHAR2(30) default 'Unknown',
  OS_MFR			VARCHAR2(30) default 'Unknown',
  BROWSER_NAME	   	VARCHAR2(50) default 'Unknown',
  BROWSER_VER	   	VARCHAR2(30) default 'Unknown',
  BROWSER_TYPE    	VARCHAR2(30) default 'Unknown',
  BROWSER_KERNEL	VARCHAR2(30) default 'Unknown',
  USER_AGENT    	VARCHAR2(300),
  TIME24    		VARCHAR2(30) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')
);
-- Add comments to the table 
comment on table ZFTAL_METRIC_DATA  is '服务接口客户端请求记录';
-- Add comments to the columns 
comment on column ZFTAL_METRIC_DATA.ID  is 'ID编号';
comment on column ZFTAL_METRIC_DATA.BIZ_ID  is '业务主键ID;用于作为附加参数提取该业务相关的请求记录';
comment on column ZFTAL_METRIC_DATA.BIZ_NAME  is '业务名称';
comment on column ZFTAL_METRIC_DATA.APP_KEY  is '应用Key;记录该次记录产生源应用';
comment on column ZFTAL_METRIC_DATA.ADDR  is '客户端IP地址';
comment on column ZFTAL_METRIC_DATA.URI  is '接口路径';
comment on column ZFTAL_METRIC_DATA.DEVICE_NAME  is '客户端设备名称';
comment on column ZFTAL_METRIC_DATA.OS_NAME  is '客户端操作系统名称';
comment on column ZFTAL_METRIC_DATA.OS_VER  is '客户端操作系统版本';
comment on column ZFTAL_METRIC_DATA.OS_MFR  is '客户端操作系统制造商';
comment on column ZFTAL_METRIC_DATA.BROWSER_NAME  is '客户端浏览器名称';
comment on column ZFTAL_METRIC_DATA.BROWSER_VER  is '客户端浏览器版本';
comment on column ZFTAL_METRIC_DATA.BROWSER_TYPE  is '客户端浏览器类型';
comment on column ZFTAL_METRIC_DATA.BROWSER_KERNEL  is '客户端浏览器内核';
comment on column ZFTAL_METRIC_DATA.USER_AGENT  is '客户端User-Agent请求头原始信息';
comment on column ZFTAL_METRIC_DATA.TIME24  is '客户端请求发送时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ZFTAL_METRIC_DATA add constraint PK_METRIC_DATA primary key (ID);
 
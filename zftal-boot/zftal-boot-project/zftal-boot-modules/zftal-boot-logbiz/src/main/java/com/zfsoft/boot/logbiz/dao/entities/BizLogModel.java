/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.logbiz.dao.entities;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.basemodel.BaseModel;

/**
 * 
 * @className	： BizLogModel
 * @description	： 业务日志信息表Model
 * @author 		：万大龙（743）
 * @date		： 2017年11月13日 下午1:57:40
 * @version 	V1.0
 */
@SuppressWarnings("serial")
public class BizLogModel extends BaseModel {

	/**
	 * 业务日志ID编号
	 */
	private String logId;
	/**
	 * 业务日志记录器名称
	 */
	private String logLogger;
	/**
	 * 产生该业务日志的线程名
	 */
	private String logThread;
	/**
	 * 产生该业务日志的函数名称
	 */
	private String logFunc;
	/**
	 * 产生该业务日志的行号
	 */
	private String logLine;
	/**
	 * 业务日志级别
	 */
	private String logLivel;
	/**
	 * 业务日志内容
	 */
	private String logMessage;
	/**
	 * 业务日志记录时间
	 */
	private String logTimestamp;

	private JSONObject logMsg;
	
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogLogger() {
		return logLogger;
	}

	public void setLogLogger(String logLogger) {
		this.logLogger = logLogger;
	}

	public String getLogThread() {
		return logThread;
	}

	public void setLogThread(String logThread) {
		this.logThread = logThread;
	}

	public String getLogFunc() {
		return logFunc;
	}

	public void setLogFunc(String logFunc) {
		this.logFunc = logFunc;
	}

	public String getLogLine() {
		return logLine;
	}

	public void setLogLine(String logLine) {
		this.logLine = logLine;
	}

	public String getLogLivel() {
		return logLivel;
	}

	public void setLogLivel(String logLivel) {
		this.logLivel = logLivel;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMsg = JSONObject.parseObject(logMessage);
		this.logMessage = logMessage;
	}

	public String getLogTimestamp() {
		return logTimestamp;
	}

	public void setLogTimestamp(String logTimestamp) {
		this.logTimestamp = logTimestamp;
	}

	public JSONObject getLogMsg() {
		return logMsg;
	}

	public void setLogMsg(JSONObject logMsg) {
		this.logMsg = logMsg;
	}

}

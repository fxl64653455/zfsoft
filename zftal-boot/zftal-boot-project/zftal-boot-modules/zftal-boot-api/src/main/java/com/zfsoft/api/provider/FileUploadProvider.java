package com.zfsoft.api.provider;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletResponse;
/**
 * 
 *@类名称	: FileUploadProvider.java
 *@类描述	：为了解决教务框架不直接依赖ftp,samba组件问题
 *@创建人	：wandalong
 *@创建时间	：2017年8月17日 下午7:24:48
 *@修改人	：
 *@修改时间	：
 *@版本号	:v1.0
 */
public interface FileUploadProvider {

	/**
	 * 
	 *@描述		：批量文件上传
	 *@创建人		: wandalong
	 *@创建时间	: Jun 7, 201610:59:11 AM
	 *@param rootDir
	 *@param files
	 *@param fileNames
	 *@param fileContentTypes
	 *@throws Exception
	 *@修改人		: 
	 *@修改时间	: 
	 *@修改描述	:
	 */
	public void uploadFile(String rootDir, List<File> files, List<String> fileNames) throws Exception;
	
	/**
	 * 
	 *@描述		：单个文件上传
	 *@创建人		: wandalong
	 *@创建时间	: Jun 7, 201610:59:04 AM
	 *@param rootDir
	 *@param file
	 *@param fileName
	 *@param fileContentType
	 *@throws Exception
	 *@修改人		: 
	 *@修改时间	: 
	 *@修改描述	:
	 */
	public void uploadFile(File file, String rootDir, String fileName) throws Exception;
	public void uploadFile(byte[] bytes, String rootDir,String fileName) throws Exception;
	public void uploadFile(InputStream input, String rootDir,String fileName) throws Exception;
	
	/**
	 * 
	 *@描述		：批量文件下载
	 *@创建人		: wandalong
	 *@创建时间	: Jun 7, 201611:10:17 AM
	 *@param rootDir
	 *@param fileNames
	 *@param destDir
	 *@throws Exception
	 *@修改人		: 
	 *@修改时间	: 
	 *@修改描述	:
	 */
	public void downloadFile(String rootDir, List<String> fileNames, File destDir) throws Exception;
	public void downloadFile(String rootDir, List<String> fileNames, OutputStream output) throws Exception;
	public void downloadFile(String rootDir, List<String> fileNames, ServletResponse response) throws Exception;
	
	/**
	 * 
	 *@描述		：单个文件下载
	 *@创建人		: wandalong
	 *@创建时间	: Jun 7, 201611:09:03 AM
	 *@param rootDir
	 *@param fileName
	 *@param destFile
	 *@throws Exception
	 *@修改人		: 
	 *@修改时间	: 
	 *@修改描述	:
	 */
	public void downloadFile(String rootDir, String fileName, File destFile) throws Exception;
	public void downloadFile(String rootDir, String fileName, OutputStream output) throws Exception;
	public void downloadFile(String rootDir, String fileName, ServletResponse response) throws Exception;
	
}

/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.fastdfs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.luhuiguo.fastdfs.proto.storage.DownloadCallback;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
public class FastdfsController {

	@Autowired
	private FastFileStorageClient storageClient;

	@ApiOperation(value = "上传文件测试1", notes = "测试Fastdfs文件上传")
	@PostMapping(value = "upload1", consumes = { "multipart/*" })
	@ResponseBody
	public Object upload1(@RequestParam("content") @ApiParam(name = "content", value = "文件") MultipartFile content)
			throws Exception {
		return storageClient.uploadFile(content.getBytes(), FilenameUtils.getExtension(content.getOriginalFilename()));
	}

	@ApiOperation(value = "上传文件测试2", notes = "测试Fastdfs文件上传")
	@PostMapping(value = "upload2", consumes = { "multipart/*" })
	@ResponseBody
	public Object upload2(@RequestParam("groupName") String groupName,
			@RequestParam("content") @ApiParam(name = "content", value = "文件") MultipartFile content) throws Exception {
		return storageClient.uploadFile(groupName, content.getBytes(),
				FilenameUtils.getExtension(content.getOriginalFilename()));
	}

	@ApiOperation(value = "上传文件测试3", notes = "测试Fastdfs文件上传")
	@PostMapping(value = "upload3", consumes = { "multipart/*" })
	@ResponseBody
	public Object upload3(@RequestParam("groupName") String groupName,
			@RequestParam("content") @ApiParam(name = "content", value = "文件") MultipartFile content) throws Exception {

		return storageClient.uploadFile(groupName, content.getInputStream(), content.getSize(),
				FilenameUtils.getExtension(content.getOriginalFilename()));
	}

	@ApiOperation(value = "删除文件测试1", notes = "测试Fastdfs文件删除")
	@PostMapping(value = "deleteFile1")
	@ResponseBody
	public Object deleteFile1(@RequestParam("filePath") String filePath) throws Exception {
		storageClient.deleteFile(filePath);
		return filePath;
	}

	@ApiOperation(value = "删除文件测试2", notes = "测试Fastdfs文件删除")
	@PostMapping(value = "deleteFile2")
	@ResponseBody
	public Object deleteFile2(@RequestParam("groupName") String groupName, @RequestParam("filePath") String filePath)
			throws Exception {
		storageClient.deleteFile(groupName, filePath);
		return filePath;
	}

	@ApiOperation(value = "下载文件测试1", notes = "测试Fastdfs文件下载")
	@PostMapping(value = "downloadFile1")
	@ResponseBody
	public ResponseEntity<byte[]> downloadFile1(@RequestParam("groupName") String groupName,
			@RequestParam("filePath") String filePath) throws Exception {

		byte[] bytes = storageClient.downloadFile(groupName, filePath);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
	}

	@ApiOperation(value = "下载文件测试2", notes = "测试Fastdfs文件下载")
	@PostMapping(value = "downloadFile2")
	@ResponseBody
	public Object downloadFile2(@RequestParam("groupName") String groupName, @RequestParam("filePath") String filePath)
			throws Exception {

		InputStream input = null;
		ByteArrayOutputStream output = null;
		try {

			input = storageClient.downloadFile(groupName, filePath, new DownloadCallback<InputStream>() {

				@Override
				public InputStream recv(InputStream ins) throws IOException {

					return ins;
				}

			});

			output = new ByteArrayOutputStream();
			IOUtils.copy(input, output);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<byte[]>(output.toByteArray(), headers, HttpStatus.OK);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
	}

}

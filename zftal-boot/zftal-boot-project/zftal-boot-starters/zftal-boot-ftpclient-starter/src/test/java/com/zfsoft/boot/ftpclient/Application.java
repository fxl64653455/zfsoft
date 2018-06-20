/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.ftpclient;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zfsoft.ftpclient.client.IFTPClient;

@EnableFTPClient
@SpringBootApplication
public class Application {
	
	@Autowired
	IFTPClient ftpclient;
	
	@PostConstruct
	public void test() throws Exception {
		
		ftpclient.sendCommand("test");
		
	}
	
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}

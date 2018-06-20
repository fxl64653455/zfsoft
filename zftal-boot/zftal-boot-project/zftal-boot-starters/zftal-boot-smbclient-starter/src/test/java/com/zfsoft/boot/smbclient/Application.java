/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.smbclient;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zfsoft.smbclient.client.ISMBClient;

@EnableSMBClient
@SpringBootApplication
public class Application {
	
	@Autowired
	ISMBClient smbclient;
	
	@PostConstruct
	public void test() throws Exception {
		
		smbclient.getSMBClient().connect();
		 	
		
	}
	
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}

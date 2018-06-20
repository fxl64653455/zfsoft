package com.zfsoft.boot.mybatis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zfsoft.boot.mybatis.service.svcinterface.IUuidService;

  
@RunWith(SpringRunner.class)  
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MybatisApplicationTests {  
  
	@Autowired
	protected IUuidService uuidService;
	
	@Test  
    public void uuid() {  
        System.out.println( "uuid: " + uuidService.get());;
    }  
    
  
}  

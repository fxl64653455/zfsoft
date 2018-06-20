/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.rocketmq.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.zfsoft.boot.rocketmq.setup.LogProducer;

@RestController
public class RocketMQController {
	
    @Autowired  
    private LogProducer logProducer;  
    
    @GetMapping("/rocketmq/send/{msg}")  
    public String activemq(HttpServletRequest request,@PathVariable("msg") String msg) {  
        msg = StringUtils.isEmpty(msg) ? "This is Empty Msg." : msg;  
      
        try {  
            logProducer.send(msg);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "Rocketmq has sent OK.";  
    }  
    
    
}

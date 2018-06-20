/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.demo.web.controller;


import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketP2PController {

    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void handlerChat(Principal principal, String msg) {
        if (principal.getName().equals("wyf")) {
            messagingTemplate.convertAndSendToUser("wisely", "/queue/notifications",
                    principal.getName() + "<br/>&emsp;&emsp;&emsp;" + msg);
        } else {
            messagingTemplate.convertAndSendToUser("wyf", "/queue/notifications",
                    principal.getName() + "<br/>&emsp;&emsp;&emsp;" + msg);
        }
    }

}
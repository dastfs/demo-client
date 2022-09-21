package kz.tdcis.poe.democlient.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/demo/socket")
public class SocketController {

    @MessageMapping("/handshake")
    @SendTo("/exchange/handshake")
    public String doHandshake(String message) throws Exception {
        return "Handshake Succeed!";
    }
}

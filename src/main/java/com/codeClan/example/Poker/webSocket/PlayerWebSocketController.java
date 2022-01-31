package com.codeClan.example.Poker.webSocket;

import com.codeClan.example.Poker.game.models.Player;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@EnableScheduling
@Controller
public class PlayerWebSocketController {

    // test route
    @MessageMapping("/hello")
    @SendTo("/client/greetings")
    public Player player(Player userInputPlayer) throws Exception {
//        System.out.println(userInputPlayer.getName());
//        System.out.println(userInputPlayer.getUsername());
//        System.out.println("heeloooo");
        return new Player("Tom", 100.00, "Tom76", "123");
    }

//    @Scheduled(fixedRate = 5000)
//    public Player continuousData() throws Exception {
//        Thread.sleep(1000);
//        return new Player("Tom", 100.0, "Tom123", "12345");
//    }


}

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }

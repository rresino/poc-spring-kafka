package rresino.poc.spring.kafka.web;

import org.springframework.web.bind.annotation.*;
import rresino.poc.spring.kafka.mq.PingPongKafkaService;

@RestController
public class PingPongController {

  PingPongKafkaService pingPongKafkaService;

  public PingPongController(PingPongKafkaService pingPongKafkaService) {
    this.pingPongKafkaService = pingPongKafkaService;
  }

  @PostMapping("/ping")
  public String doPing() {
    pingPongKafkaService.sendPingPong("ping");
    return "ping";
  }
}

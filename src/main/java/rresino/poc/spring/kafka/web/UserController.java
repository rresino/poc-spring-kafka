package rresino.poc.spring.kafka.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rresino.poc.spring.kafka.domain.User;
import rresino.poc.spring.kafka.mq.PingPongKafkaService;
import rresino.poc.spring.kafka.mq.UserKafkaService;

@RestController
public class UserController {

  UserKafkaService userKafkaService;

  public UserController(UserKafkaService userKafkaService) {
    this.userKafkaService = userKafkaService;
  }

  @PostMapping("/user")
  public User doUser(@RequestBody User user) {
    userKafkaService.sendUser(user);
    return user;
  }
}

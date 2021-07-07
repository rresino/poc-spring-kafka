package rresino.poc.spring.kafka.mq;

import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.ParseStringDeserializer;
import org.springframework.kafka.support.serializer.ToStringSerializer;
import org.springframework.stereotype.Service;
import rresino.poc.spring.kafka.domain.User;

@Service
public class UserKafkaService {

  private final Logger logger = LoggerFactory.getLogger(UserKafkaService.class);

  private final KafkaTemplate<String, User> kafkaUserTemplate;

  public UserKafkaService(KafkaTemplate<String, User> kafkaUserTemplate) {
    this.kafkaUserTemplate = kafkaUserTemplate;
  }

  @KafkaListener(id = "user_kafka_service_listener", topics = "user")
  public void processUser(User user) {
    logger.info("Get user: [{}]", user);

  }

  public void sendUser(User user) {
    logger.info("Send user: [{}]", user);
    kafkaUserTemplate.send("user", user);
  }

}

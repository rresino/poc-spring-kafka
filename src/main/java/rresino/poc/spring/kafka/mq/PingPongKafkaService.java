package rresino.poc.spring.kafka.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PingPongKafkaService {

  private final KafkaTemplate<String, String> kafkaPingPongTemplate;

  private final Logger logger = LoggerFactory.getLogger(PingPongKafkaService.class);

  public PingPongKafkaService(KafkaTemplate<String, String> kafkaPingPongTemplate) {
    this.kafkaPingPongTemplate = kafkaPingPongTemplate;
  }

  @KafkaListener(id = "pingpong_kafka_service", topics = "ping_pong")
  public void processPingPong(String msg) {
    logger.info("Get msg: [{}]", msg);
    if (msg.equalsIgnoreCase("ping")) {
      sendPingPong("pong");
    }
  }

  public void sendPingPong(String msg) {
    logger.info("Send msg: [{}]", msg);
    kafkaPingPongTemplate.send("ping_pong", msg);
  }

}

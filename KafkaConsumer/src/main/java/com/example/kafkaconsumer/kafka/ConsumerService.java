package com.example.kafkaconsumer.kafka;

import com.example.kafkaconsumer.entity.Passenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

    @Service
    public class ConsumerService {

        private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

        @KafkaListener(topics = "users", groupId = "group_id")
        public void consume(Passenger passenger) throws IOException {
            logger.info(String.format("#### -> Consumed message -> %s", passenger));
        }
}

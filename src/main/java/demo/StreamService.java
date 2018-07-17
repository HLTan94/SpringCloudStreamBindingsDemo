package demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
@EnableBinding({StreamChannel.class})
public class StreamService {
    @Autowired
    StreamChannel streamChannel;

    @StreamListener(StreamChannel.TEST_INPUT)
    public void dummyListener(
            @Payload Message<String> message,
            @Header(value = "spoiler", required = false) String spoiler
    ) {

        log.info("Received message: {}", message.getPayload());
        if (spoiler != null) {
            log.info("Spoiler Header found! {}", spoiler);
        }
    }

    public void dummyPublisher() {
        try {
            streamChannel.testOutput().send(MessageBuilder.withPayload("My message: " + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()))
                    .setHeader("spoiler", "EveryoneTurnedIntoAshes")
                    .build());
            log.info("Message sent to {}", StreamChannel.TEST_OUTPUT);
        } catch (Exception ex) {
            log.error("Unable to send message to {}.", StreamChannel.TEST_OUTPUT, ex);
        }
    }
}

package demo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("squid:S1214")
public interface StreamChannel {
    String TEST_INPUT = "TestInput";
    String TEST_INPUT2 = "TestInput2";
    String TEST_OUTPUT = "TestOutput";

    @Input(StreamChannel.TEST_INPUT)
    SubscribableChannel testInput();
    @Input(StreamChannel.TEST_INPUT2)
    SubscribableChannel testInput2();
    @Output(StreamChannel.TEST_OUTPUT)
    SubscribableChannel testOutput();


}

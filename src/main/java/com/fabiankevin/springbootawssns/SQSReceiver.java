package com.fabiankevin.springbootawssns;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SQSReceiver  {

    @SqsListener(value = "sqs-local-queue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void sqsReceiver(String message){
        log.info("Receive: {}", message);
    }
}

package com.fabiankevin.springbootawssns;

import com.amazonaws.Protocol;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class SNSSampleController {
    private final AmazonSNSClient amazonSNSClient;

    private String SNS_TOPIC_ARN = "arn:aws:sns:us-west-2:000000000000:test-sns";
    private String SQS_URL = "http://awslocal:4566/000000000000/sqs-local-queue";


    @GetMapping("/subscription")
    public String subscribeToSqs(){
        SubscribeRequest request = new SubscribeRequest(SNS_TOPIC_ARN, "sqs", SQS_URL);
        amazonSNSClient.subscribe(request);
        return "You've been successfully subscribed.";
    }

    @GetMapping("/message/{message}")
    public String sendMessage(@PathVariable String message) {
        PublishRequest publishRequest = new PublishRequest(SNS_TOPIC_ARN, message, "Sample Demo");
        amazonSNSClient.publish(publishRequest);
        return "Message sent: "+message;
    }




//
//    private
}

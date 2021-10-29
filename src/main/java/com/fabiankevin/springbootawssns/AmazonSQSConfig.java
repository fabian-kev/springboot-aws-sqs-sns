//package com.fabiankevin.springbootawssns;
//
//import com.amazonaws.auth.AWSCredentialsProvider;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.client.builder.AwsClientBuilder;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.sqs.AmazonSQSAsync;
//import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
//import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
//import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
//import org.springframework.cloud.aws.messaging.listener.support.AcknowledgmentHandlerMethodArgumentResolver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.messaging.converter.MappingJackson2MessageConverter;
//import org.springframework.messaging.converter.MessageConverter;
//import org.springframework.messaging.handler.annotation.support.PayloadArgumentResolver;
//
//import java.util.Arrays;
//
//@Slf4j
//@Configuration
//public class AmazonSQSConfig {
//  @Value("${cloud.aws.credentials.access-key}")
//  private String accessKey;
//  @Value("${cloud.aws.credentials.secret-key}")
//  private String secretKey;
//  @Bean
//  public AWSCredentialsProvider awsCredentialsProvider(){
//    return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));
//  }
//
//  @Bean
//  public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(
//      AmazonSQSAsync amazonSQSAsync,
//      @Value("${aws.sqs.maxNumberOfMessages:10}") int maxNumberOfMessages,
//      @Value("${aws.sqs.waitTimeOut:20}") int waitTimeOut,
//      @Value("${aws.sqs.visibilityTimeOut:300}") int visibilityTimeOut) {
//    SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
//    factory.setAmazonSqs(amazonSQSAsync);
//    factory.setMaxNumberOfMessages(maxNumberOfMessages);
//    factory.setWaitTimeOut(waitTimeOut);
//    factory.setVisibilityTimeout(visibilityTimeOut);
//    factory.setAutoStartup(true);
//    return factory;
//  }
//
//  @Bean
//  @Primary
//  public AmazonSQSAsync amazonSqsAsync(AWSCredentialsProvider awsCredentialsProvider) {
//    AmazonSQSAsyncClientBuilder amazonSQSAsyncClientBuilder = AmazonSQSAsyncClientBuilder.standard();
//    amazonSQSAsyncClientBuilder.withCredentials(awsCredentialsProvider);
//      amazonSQSAsyncClientBuilder.withEndpointConfiguration(
//          new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-west-2"));
//
//    return amazonSQSAsyncClientBuilder.build();
//  }
//
//  @Bean
//  public QueueMessageHandlerFactory queueMessageHandlerFactory(MessageConverter messageConverter,
//                                                               AmazonSQSAsync amazonSQSAsync) {
//    QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
//    factory.setAmazonSqs(amazonSQSAsync);
//    AcknowledgmentHandlerMethodArgumentResolver acknowledgmentResolver =
//        new AcknowledgmentHandlerMethodArgumentResolver("Acknowledgment");
//    PayloadArgumentResolver payloadArgumentResolver = new PayloadArgumentResolver(messageConverter);
//    factory.setArgumentResolvers(Arrays.asList(acknowledgmentResolver, payloadArgumentResolver));
//    return factory;
//  }
//
//  @Bean
//  public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
//    return new QueueMessagingTemplate(amazonSQSAsync);
//  }
//
//  @Bean
//  protected MessageConverter messageConverter(ObjectMapper objectMapper) {
//    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//    converter.setObjectMapper(objectMapper);
//    converter.setStrictContentTypeMatch(false);
//    converter.setSerializedPayloadClass(String.class);
//    return converter;
//  }
//}

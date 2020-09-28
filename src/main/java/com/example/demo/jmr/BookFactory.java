package com.example.demo.jmr;

import com.example.demo.objects.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.web.client.RestTemplate;

import javax.jms.ConnectionFactory;
import java.math.BigInteger;

@SpringBootApplication
@EnableJms
public class BookFactory {

    @Autowired
    private static RestTemplate restTemplate2 = new RestTemplate();


    @Bean
    public JmsListenerContainerFactory<?> aFactory(ConnectionFactory connectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookFactory.class, args);
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        System.out.println("Sending book");
        Book[] books = getBooks();

        for(Book book : books) {
            jmsTemplate.convertAndSend("bookshelf", book);
        }

    }

    public static Book[] getBooks() {
        return restTemplate2.getForObject("http://localhost:8080/all", Book[].class);
    }

}

package code.mailsenderservice.config;

import code.mailsenderservice.utils.EmailRequest;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;


    @Bean
    public ConsumerFactory<String, EmailRequest> consumerFactory() {

        JsonDeserializer<EmailRequest> deserializer = new JsonDeserializer<>(EmailRequest.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> props = new HashMap<>(){{
            put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            put(KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
            put(VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        }};


        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);

    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, EmailRequest>>
    kafkaListenerContainerFactory(ConsumerFactory<String, EmailRequest> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, EmailRequest> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}

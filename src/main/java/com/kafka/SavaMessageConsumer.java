package com.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by haocheng on 2018/3/20.
 */
public class SavaMessageConsumer {

    public static final String SERVER ="192.168.145.128:9095";
    public static final String TOPIC ="helloKafka1";
    public static void main(String[] agrs){
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,SERVER);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"group-1");
        Consumer<String,String> consummer = new KafkaConsumer<>(props);
        consummer.subscribe(Arrays.asList(TOPIC));
        boolean flag = true;
        while (flag){
            ConsumerRecords<String,String> allRecoders =  consummer.poll(200);
            for(ConsumerRecord<String,String> record:allRecoders){
                System.out.println("key: "+record.key()+" value: "+record.value());
            }
        }
        consummer.close();
    };
}

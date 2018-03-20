package com.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
/**
 * Created by haocheng on 2018/3/20.
 */
public class SendMessageProducer {

    public static final String SERVERS ="192.168.145.128:9095";
    public static final String TOPIC ="helloKafka1";
    public static void main(String[] args){
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,SERVERS);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        Producer<String,String> producer = new KafkaProducer<>(props);
        long start = System.currentTimeMillis();
        for(int i=0;i<1000;i++){
            producer.send(new ProducerRecord<String, String>(TOPIC,"x",i+""));
        }
        long end = System.currentTimeMillis();
        System.out.println("kafka takes total :"+ (end - start));
        producer.close();

    };
}

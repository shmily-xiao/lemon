package com.lemon.rocketmq;

import com.lemon.pojo.mq.MQ;
import com.lemon.utils.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

/**
 * Created by wangzaijun on 2017/7/14.
 */
@Component
@Singleton
public class Producer {


    /**
     * 发送消息到 rocketmq 中
     * @param nameServer     "send_task_email"
     * @param producerGroupName
     * @param topic          "SendEmailTopic"
     * @param tags           "dreamEmail""birthdayEmail"
     * @param message         传送给消息队列的内容 json 字符串 {"email":"xxx@ss.com","theme":"ssss"}
     * @return
     * @throws MQClientException
     */
    public Boolean sendMessage(String nameServer, String producerGroupName, String topic,String tags, String message) throws MQClientException {

        if (StringUtils.isEmpty(message)){
            return Boolean.FALSE;
        }
        DefaultMQProducer producer = new DefaultMQProducer(producerGroupName+ MQ.PRODUCER_POSTFIX);
        producer.setProducerGroup(nameServer);
        producer.start();
        try {
            Message msg = new Message(topic, tags, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            return SendStatus.SEND_OK.equals(sendResult.getSendStatus());
        }catch (Exception e){
            e.printStackTrace();
            return Boolean.FALSE;
        }finally {
            producer.shutdown();
        }
    }

    private String  test(){
        try{
            System.out.println("normal");
            return "normal test";
        }catch (Exception e){
            System.out.println("Exception");
            return "Exception test";
        }finally {
            System.out.println("finally");
//            return "finally test";
        }
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        String string = producer.test();
        System.out.println(string);
    }
}

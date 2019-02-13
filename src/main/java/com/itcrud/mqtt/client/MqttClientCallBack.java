package com.itcrud.mqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @Author: Joker
 * @Desc:
 * @Date: 2019/1/10 10:18
 * @Modified By:
 * @Project_name: iot-mqtt
 * @Version 1.0
 */
public class MqttClientCallBack implements MqttCallback {

    @Override
    public void connectionLost(Throwable throwable) {
        //连接丢失输出内容
        System.out.println("client连接断开，可以做重连");
    }

    /**
     * 当一个主题发送了消息后，这个主题消息会保存到mosquito上，只要监听这个主题的服务启动就会看到此主题最新的一次主题消息
     * 由于多个机器都会接收这个主题的消息，会导致消息的重复消费，需要考虑重复数据问题（集群的时候，考虑加上分布式锁）
     *
     * @param topic
     * @param message
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //收到消息处理逻辑
        System.out.println("client接收消息主题 : " + topic);
        System.out.println("client接收消息Qos : " + message.getQos());
        System.out.println("client接收消息内容 : " + new String(message.getPayload()));
    }

    /**
     * 接收回调结果(此结果是发送到mosquitto返回的状态,并不是消费者接收成功返回的)
     *
     * @param token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        //对于QoS1、QoS2回调的处理
        System.out.println("client发出的消息是否已经被接收：" + token.isComplete());
    }
}

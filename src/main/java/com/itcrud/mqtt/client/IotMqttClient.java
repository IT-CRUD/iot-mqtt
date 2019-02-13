package com.itcrud.mqtt.client;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @Author: Joker
 * @Desc: mqtt的客户端，用于接收消息，但是实际上客户端也是服务端，也可以订阅接收其他端发布的消息
 * @Date: 2019/1/10 10:17
 * @Modified By:
 * @Project_name: iot-mqtt
 * @Version 1.0
 */
public class IotMqttClient {

    private static final String host = "tcp://ip:1883";//ip是对应mosquitto安装机器的ip地址，1883是默认端口号
    private static final String clientId = "iot-mqtt";
    private static final String userName = "itcrud";//非必填
    private static final String pwd = "123";//非必填

    private MqttClient client;

    public IotMqttClient() {
        try {
            client = new MqttClient(host, clientId, new MemoryPersistence());
        } catch (MqttException e) {
            e.printStackTrace();
        }
        connect();
    }


    //连接
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(pwd.toCharArray());
        options.setConnectionTimeout(10);//设置超时时间10S，单位：秒
        options.setKeepAliveInterval(20);//设置会话心跳时间20，单位：秒

        //当连接断开后，mosquito会自动发送此消息，监听close主题的服务会接收到信息
        //options.setWill("close","失去连接".getBytes(),1,true);

        client.setCallback(new MqttClientCallBack());//设置回调类
        try {
            client.connect(options);//建立连接
            int[] Qos = {1};//三种消息可靠性
            String[] topic1 = {"joker"};//监听的主题名称，可监听多个主题，用逗号隔开
            client.subscribe(topic1, Qos);//接收
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发布消息
     *
     * @param message
     */
    public void publish(MqttMessage message, String topic) {
        try {
            MqttTopic mqttTopic = client.getTopic(topic);//根据指定的topic发送消息
            mqttTopic.publish(message); //发布消息
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

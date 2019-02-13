package com.itcrud.mqtt.controller;

import com.itcrud.mqtt.client.IotMqttClient;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Joker
 * @Desc:
 * @Date: 2019/1/10 11:06
 * @Modified By:
 * @Project_name: iot-mqtt
 * @Version 1.0
 */
@Controller
@RequestMapping("/")
@Slf4j
public class IotMqttController {

    private IotMqttClient mqttClient = new IotMqttClient();

    /**
     * 由client发布消息
     *
     * @return
     */
    @RequestMapping("/client")
    @ResponseBody
    public String client() {
        MqttMessage message = new MqttMessage();
        message.setQos(1);//保证消息能到达一次
        message.setRetained(true);
        message.setPayload("client推送的内容".getBytes());
        mqttClient.publish(message, "joker");
        return "SUCCESS";
    }
}

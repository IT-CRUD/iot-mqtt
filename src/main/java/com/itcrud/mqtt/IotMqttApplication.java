package com.itcrud.mqtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: Joker
 * @Desc:
 * @Date: 2019/1/10 10:12
 * @Modified By:
 * @Project_name: iot-mqtt
 * @Version 1.0
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class IotMqttApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotMqttApplication.class);
    }
}

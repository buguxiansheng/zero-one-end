package com.lcfc.demo.modules.mq;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 直连模式
@Configuration
public class DirectRabbitConfig {



    // 扇形的队列
    @Bean
    public Queue TestFanoutQueue1() {
        return new Queue("TestFanoutQueue1",true);
    }
    @Bean
    public Queue TestFanoutQueue2(){
        return new Queue("TestFanoutQueue2",true);
    }

    // 扇形交换机
    @Bean
    FanoutExchange TestFanoutExchange(){
        return  new FanoutExchange("TestFanoutExchange",true,false);
    }
    // 扇形绑定  一对多
    @Bean
    Binding bindingFanout1() {
        return BindingBuilder.bind(TestFanoutQueue1()).to(TestFanoutExchange());
    }
    @Bean
    Binding bindingFanout2() {
        return BindingBuilder.bind(TestFanoutQueue2()).to(TestFanoutExchange());
    }





    @Bean
    public Queue TestDirectQueue() {
        return new Queue("TestDirectQueue",true);
    }



    // 直连交换机
    @Bean
    DirectExchange TestDirectExchange() {
        //  return new DirectExchange("TestDirectExchange",true,true);
        return new DirectExchange("TestDirectExchange",true,false);
    }
    // 直连绑定
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }

}

package ben.study.spring;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.peer.SystemTrayPeer;

@Configuration
public class HelloConfig {
    @Bean
    public String greeting(){
        return "World";
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HelloConfig.class);
        String text = (String)context.getBean("greeting");

        System.out.println(text);
    }
}

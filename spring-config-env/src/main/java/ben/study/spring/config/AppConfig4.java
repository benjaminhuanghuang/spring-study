package ben.study.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;


/**
    Set Profile by using env variable spring.profiles.active
    @Profile("dev")
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class AppConfig4 {

    /*
        Load value from property file
     */
    @Value("${greeting.text}")
    private String greetingText;

    @Value("${greeting.preamble}")
    private String greetingPreamble;

    @Value("#{new Boolean(environment['spring.profiles.active']=='dev')}")
    private boolean isDev;

    public class Worker{
        private String preamble;
        private String text;

        public Worker(String preamble, String text){
            this.preamble = preamble;
            this.text = text;
        }

        public void execute(){
            System.out.println(preamble + " " + text + " is dev: " + isDev);
        }
    }

    @Bean
    @Profile("dev")
    Worker workerForDev(){
        return new Worker("DEV", greetingText);
    }

    @Bean
    @Profile("prod")
    Worker workerForProd(){
        return new Worker("PROD", greetingText);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig4.class);

        Worker worker = context.getBean(Worker.class);
        worker.execute();
    }
}


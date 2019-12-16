package ben.study.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;


/**
    Using spring expression language
    1. @PropertySource("classpath:/application=${spring.profiles.active}.properties")

    2. set env variable spring.profiles.active to dev or prod

    3. @Value("#{new Boolean(environment['spring.profiles.active']=='dev')}")
 */
@Configuration
@PropertySource("classpath:/application-${spring.profiles.active}.properties")
public class AppConfig5 {

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
    Worker worker(){
        return new Worker(greetingPreamble, greetingText);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig5.class);

        Worker worker = context.getBean(Worker.class);
        worker.execute();
    }
}


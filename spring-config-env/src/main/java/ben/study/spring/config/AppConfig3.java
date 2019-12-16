package ben.study.spring.config;

import ben.study.spring.data.repository.CustomerRepository;
import ben.study.spring.data.repository.InventoryItemRepository;
import ben.study.spring.data.repository.SalesOrderRepository;
import ben.study.spring.service.InventoryService;
import ben.study.spring.service.OrderService;
import ben.study.spring.service.impl.InventoryServiceImpl;
import ben.study.spring.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;


/**
    Load properties from application.properties
    Or Environment variable greeting.text=WOOOORLD

    Environment variable have higher priority than property file.

 */
@Configuration
@PropertySource("classpath:/application.properties")
public class AppConfig3 {

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
            System.out.println(preamble + " " + text + "is dev: " + isDev);
        }
    }

    @Bean Worker worker(){
        return new Worker(greetingPreamble, greetingText);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig3.class);

        Worker worker = context.getBean(Worker.class);
        worker.execute();
    }
}


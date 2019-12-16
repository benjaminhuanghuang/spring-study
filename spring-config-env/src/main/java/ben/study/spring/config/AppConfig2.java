package ben.study.spring.config;

import ben.study.spring.data.repository.CustomerRepository;
import ben.study.spring.data.repository.InventoryItemRepository;
import ben.study.spring.data.repository.SalesOrderRepository;
import ben.study.spring.service.InventoryService;
import ben.study.spring.service.OrderService;
import ben.study.spring.service.impl.InventoryServiceImpl;
import ben.study.spring.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
    @Import data (repository ) config from other file
    Then @Autowired the instance
 */
@Configuration
@Import(DataConfig.class)
public class AppConfig2 {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Bean
    public OrderService orderService(InventoryService inventoryService,
                                     CustomerRepository customerRepository,
                                     SalesOrderRepository salesOrderRepository) {
        return new OrderServiceImpl(inventoryService, customerRepository, salesOrderRepository);
    }

    @Bean
    public InventoryService inventoryService(InventoryItemRepository inventoryItemRepository) {
        return new InventoryServiceImpl(inventoryItemRepository);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig2.class);
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println(orderService == null ? "NULL" : "A OK");
    }
}


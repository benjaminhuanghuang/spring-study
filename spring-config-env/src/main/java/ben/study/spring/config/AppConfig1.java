package ben.study.spring.config;

import ben.study.spring.data.repository.CustomerRepository;
import ben.study.spring.data.repository.InventoryItemRepository;
import ben.study.spring.data.repository.SalesOrderRepository;
import ben.study.spring.service.InventoryService;
import ben.study.spring.service.OrderService;
import ben.study.spring.service.impl.InventoryServiceImpl;
import ben.study.spring.service.impl.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
public class AppConfig1 {
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

    @Bean
    public InventoryItemRepository inventoryItemRepository() {
        return new InventoryItemRepository();
    }

    @Bean
    public CustomerRepository customerRepository(){
        return new CustomerRepository();
    }

    @Bean
    public SalesOrderRepository salesOrderRepository(){
        return new SalesOrderRepository();
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig1.class);
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println(orderService == null ? "NULL" : "A OK");
    }
}


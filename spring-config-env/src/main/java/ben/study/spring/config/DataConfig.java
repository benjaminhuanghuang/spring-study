package ben.study.spring.config;

import ben.study.spring.data.repository.CustomerRepository;
import ben.study.spring.data.repository.InventoryItemRepository;
import ben.study.spring.data.repository.SalesOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

    @Bean
    public InventoryItemRepository inventoryItemRepository(){
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
}

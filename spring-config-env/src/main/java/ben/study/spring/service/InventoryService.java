package ben.study.spring.service;

import ben.study.spring.domain.Product;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    Map<Product, Long> getTotalInventoryOnHand();
    long getQuantityOnHand(String itemId);
    void adjustInventory(String itemId, long quantity);
}

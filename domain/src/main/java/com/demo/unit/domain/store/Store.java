package com.demo.unit.domain.store;

import lombok.Getter;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;
    private String name;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "store_inventory", joinColumns = @JoinColumn(name = "store_id"))
    private Map<Long, Integer> inventory;

    protected Store() {

    }

    public Store(String name) {
        this.name = name;
        this.inventory = new HashMap<>();
    }

    public void addInventory(long productId, int quantity) {
        Integer cur = inventory.getOrDefault(productId, 0);
        inventory.put(productId, cur + quantity);
    }

    public int getInventory(long productId) {
        return inventory.getOrDefault(productId, 0);
    }

    public void removeInventory(long productId, int quantity) {
        if (!hasEnoughInventory(productId, quantity)) {
            throw new RuntimeException("Not enough inventory");
        }
        inventory.put(productId, inventory.get(productId) - quantity);
    }

    public boolean hasEnoughInventory(long productId, int quantity) {
        return inventory.getOrDefault(productId, 0) >= quantity;
    }
}

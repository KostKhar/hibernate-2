package service;

import dao.InventoryDAO;
import entity.Film;
import entity.Inventory;
import entity.Store;

public class InventoryService {
    private InventoryDAO inventoryDAO ;

    public InventoryService(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public Inventory createInventory(Film film, Store store) {
        Inventory inventory =  Inventory.builder()
                .film(film)
                .store(store)
                .build();
        inventoryDAO.create(inventory);
        return inventory;
    }
}

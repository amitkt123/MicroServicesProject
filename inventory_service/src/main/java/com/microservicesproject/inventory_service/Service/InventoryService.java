package com.microservicesproject.inventory_service.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microservicesproject.inventory_service.DTO.InventoryResponseDTO;
import com.microservicesproject.inventory_service.Model.Inventory;
import com.microservicesproject.inventory_service.Repository.InventoryRepository;

import inventoryExceptionHandling.InventoryNotFoundException;
 

@Service
public class InventoryService implements InventoryServiceAPIS {

     final InventoryRepository repo;

    InventoryService(InventoryRepository repo) {
        this.repo = repo;
    }


    /**
     *
     * @param inventory
     * @return returns the InventoryResponseDTO when Inventory is given as input
     *
     */
    private InventoryResponseDTO mapInventoryToResponseDTO(Inventory inventory) {
        InventoryResponseDTO result = new InventoryResponseDTO();
        result.setItemCode(inventory.getItemCode());
        result.setQuantity(inventory.getQuantity());
        return result;
    }

    /**
     * Checks if an item is in stock.
     *
     * @param itemCode The item code of the inventory item to check.
     * @return true if the item is in stock, false otherwise.
     */
    @Transactional(readOnly = true)
    public boolean isInStock(String itemCode) {
        return repo.findByItemCode(itemCode).isPresent();
    }

    /**
     * Retrieves inventory details by item code.
     *
     * @param item_code The item code of the inventory item.
     * @return InventoryResponseDTO containing the inventory details, or null if not found.
     */
    @Override
    public InventoryResponseDTO getInventoryByItemCode(String item_code) {
        Optional<Inventory> inventory = repo.findByItemCode(item_code);
        if (inventory.isPresent()) {
            return mapInventoryToResponseDTO(inventory.get());
        }
        return null;
    }



    /**
     * Adds stock to the inventory for a specific item.
     *
     * @param item_code The item code of the inventory item.
     * @param quantity The quantity to add to the inventory.
     * @throws InventoryNotFoundException if the item is not found.
     */
    @Override
    public void addStock(String item_code, int quantity) {
        Optional<Inventory> inventory = repo.findByItemCode(item_code);
        if (inventory.isPresent()) {
            inventory.get().setQuantity(inventory.get().getQuantity() + quantity);
            repo.saveAndFlush(inventory.get());
        } else {
            throw new InventoryNotFoundException("Item not found");
        }
    }



    /**
     * Reduces stock from the inventory for a specific item.
     *
     * @param item_code The item code of the inventory item.
     * @param quantity The quantity to reduce from the inventory.
     * @throws InventoryNotFoundException if the item is not found.
     * @throws RuntimeException if there is not enough stock.
     */
    @Override
    public void reduceStock(String item_code, int quantity) {
        Optional<Inventory> inventoryOpt = repo.findByItemCode(item_code);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            if (inventory.getQuantity() >= quantity) {
                inventory.setQuantity(inventory.getQuantity() - quantity);
                repo.save(inventory);
            } else {
                throw new RuntimeException("Not enough stock");
            }
        } else {
            throw new InventoryNotFoundException("Product not found");
        }
    }





    /**
     * Reserves stock for a specific item by reducing it from the inventory.
     *
     * @param item_code The item code of the inventory item.
     * @param quantity The quantity to reserve from the inventory.
     * @throws InventoryNotFoundException if the item is not found.
     * @throws RuntimeException if there is not enough stock.
     */
    @Override
    public void reserveStock(String item_code, int quantity) {
        reduceStock(item_code.toString(), quantity);
    }




    /**
     * Releases reserved stock for a specific item by adding it back to the inventory.
     *
     * @param item_code The item code of the inventory item.
     * @param quantity The quantity to release back to the inventory.
     * @throws InventoryNotFoundException if the item is not found.
     */
    @Override
    public void releaseStock(String item_code, int quantity) {
        addStock(item_code, quantity);
    }






    /**
     * Adjusts the stock quantity for a specific item to a specified value.
     *
     * @param item_code The item code of the inventory item.
     * @param quantity The new quantity to set for the inventory item.
     * @throws InventoryNotFoundException if the item is not found.
     */
    @Override
    public void adjustStock(String item_code, int quantity) {
        Optional<Inventory> inventoryOpt = repo.findByItemCode(item_code);
        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            inventory.setQuantity(quantity);
            repo.save(inventory);
        } else {
            throw new InventoryNotFoundException("Product not found");
        }
    }
}

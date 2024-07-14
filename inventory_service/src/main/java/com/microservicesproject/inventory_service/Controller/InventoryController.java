package com.microservicesproject.inventory_service.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservicesproject.inventory_service.DTO.InventoryResponseDTO;
import com.microservicesproject.inventory_service.Service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	private final InventoryService service;
	InventoryController(InventoryService service){
		this.service = service;
	}

	//get the inventory status
	@GetMapping("/inStock/{item_code}")
	@ResponseStatus(HttpStatus.OK)
	public boolean isInStock(@PathVariable("item_code")String itemCode) {
		return service.isInStock(itemCode);
	}

	@GetMapping("/{item_code}")
    public InventoryResponseDTO getInventory(@PathVariable("item_code") String item_code) {
        return service.getInventoryByItemCode(item_code );
    }

    @PostMapping("/{item_code}/add")
    public void addStock(@PathVariable Long item_code, @RequestParam int quantity) {
    	service.addStock(item_code.toString(), quantity);
    }

    @PostMapping("/{item_code}/reduce")
    public void reduceStock(@PathVariable Long item_code, @RequestParam int quantity) {
    	service.reduceStock(item_code.toString(), quantity);
    }

    @PostMapping("/{item_code}/reserve")
    public void reserveStock(@PathVariable Long item_code, @RequestParam int quantity) {
    	service.reserveStock(item_code.toString(), quantity);
    }

    @PostMapping("/{item_code}/release")
    public void releaseStock(@PathVariable Long item_code, @RequestParam int quantity) {
    	service.releaseStock(item_code.toString(), quantity);
    }

    @PutMapping("/{item_code}/adjust")
    public void adjustStock(@PathVariable Long item_code, @RequestParam int quantity) {
    	service.adjustStock(item_code.toString(), quantity);
    }




}

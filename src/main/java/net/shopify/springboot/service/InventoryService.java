package net.shopify.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.shopify.springboot.model.Inventory;

public interface InventoryService {
	List<Inventory> getAllInventory();
	void saveInventory(Inventory inventory);
	Inventory getInventoryById(long id);
	void deleteInventoryById(long id);
	Page<Inventory> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

package net.shopify.springboot.service;

import java.util.List;
import java.util.Optional;

import net.shopify.springboot.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.shopify.springboot.repository.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public List<Inventory> getAllInventory() {
		return inventoryRepository.findAll();
	}

	@Override
	public void saveInventory(Inventory inventory) {
		this.inventoryRepository.save(inventory);
	}

	@Override
	public Inventory getInventoryById(long id) {
		Optional<Inventory> optional = inventoryRepository.findById(id);
		Inventory inventory = null;
		if (optional.isPresent()) {
			inventory = optional.get();
		} else {
			throw new RuntimeException(" Inventory not found for id :: " + id);
		}
		return inventory;
	}

	@Override
	public void deleteInventoryById(long id) {
		this.inventoryRepository.deleteById(id);
	}

	@Override
	public Page<Inventory> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.inventoryRepository.findAll(pageable);
	}
}

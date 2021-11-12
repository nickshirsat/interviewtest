package com.interviewtest.interviewtest.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.interviewtest.interviewtest.model.ProductModel;
import com.interviewtest.interviewtest.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	ProductRepo productRepo;
	
	public Object getProducts() {
		
		try {
			List<ProductModel> products = productRepo.findAll();
			return products;
		} catch (Exception e) {
			// TODO: handle exception
			return e.toString();
		}
		
	}

	public Object getProduct(String id) {
		// TODO Auto-generated method stub
		try {
			//return all products
			if(id.trim().isEmpty() || id.trim().equals("0")) {
				List<ProductModel> products = productRepo.findAll();
				return products;
			} else {
				//return product if ID is specified
				ProductModel product = productRepo.findById(UUID.fromString(id)).get();					
				return product;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return e.toString();
		}
	}
	
	public Object addProduct(ProductModel product) {
		try {
			UUID id = productRepo.save(product).getId();
			return id;
		} catch (Exception e) {
			// TODO: handle exception
			return e.toString();
		}
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<?> deleteProduct(UUID id) {
		ResponseEntity<?> response = null;
		JSONObject jsonResponse = new JSONObject();
		try {
			ProductModel product = productRepo.getById(id);
			if(product != null) {
				
				productRepo.delete(product);
				
				jsonResponse.put("message", "Product with id " + product.getId() + " deleted Successfully.");
				jsonResponse.put("result", 1);
				response = new ResponseEntity<>(jsonResponse,HttpStatus.OK);
			} else {
				response = new ResponseEntity<>("Product not found!", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response = new ResponseEntity<>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public ResponseEntity<?> updateProduct(ProductModel product) {
		ResponseEntity<?> response = null;
		JSONObject jsonResponse = new JSONObject();
		try {
			UUID id = productRepo.save(product).getId();
			jsonResponse.put("message", "Product with id " + id + " updated successfully.");
			response = new ResponseEntity<>(jsonResponse,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response = new ResponseEntity<>(e.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}

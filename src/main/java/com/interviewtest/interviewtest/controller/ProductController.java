package com.interviewtest.interviewtest.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.interviewtest.interviewtest.model.ProductModel;
import com.interviewtest.interviewtest.service.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@ApiOperation("Get all products from Database")
	@GetMapping("/products")
	public ResponseEntity<?> getProducts(){
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response = new ResponseEntity<>("There was an error, Please try again.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@ApiOperation("Get product by product id")
	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") String id){
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response = new ResponseEntity<>("There was an error, Please try again.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@ApiOperation("Save a product")
	@PostMapping("/product")
	public ResponseEntity<?> saveProduct(@RequestBody ProductModel product){
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response = new ResponseEntity<>("There was an error, Please try again.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@ApiOperation("Delete a product")
	@PatchMapping("/delete-product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable UUID id){
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response = new ResponseEntity<>("There was an error, Please try again.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@ApiOperation("Update product")
	@PatchMapping("/update-product")
	public ResponseEntity<?> updateProduct(@RequestBody ProductModel product){
		ResponseEntity<?> response = null;
		try {
			response = new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response = new ResponseEntity<>("There was an error, Please try again.",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
}

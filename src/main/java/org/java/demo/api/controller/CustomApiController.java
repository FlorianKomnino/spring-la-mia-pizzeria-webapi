package org.java.demo.api.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Ingredient;
import org.java.demo.pojo.Pizza;
import org.java.demo.pojo.SpecialOffer;
import org.java.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class CustomApiController {

	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/pizzas") 
	public ResponseEntity<List<Pizza>> pizzasList() {
		List<Pizza> pizzas = pizzaService.findAll();
		
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	@GetMapping("pizzas/search")
	public ResponseEntity<List<Pizza>> pizzaSearch(String name) {
				
		List<Pizza> foundPizzas = pizzaService.findByName(name);
		
		return new ResponseEntity<>(foundPizzas, HttpStatus.OK);
	}
	
	@GetMapping("/pizzas/{id}")
	public ResponseEntity<Pizza> findPizzaById(
			@PathVariable("id") Integer id
			) {
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PostMapping("/pizzas/create")
	public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {
		
		pizzaService.save(pizza);
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PostMapping("/pizzas/edit/{id}")
	public ResponseEntity<Pizza> createPizza(@PathVariable("id") Integer id, @RequestBody Pizza pizza) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizzaFound = pizzaOpt.get();
		pizzaFound.setName(pizza.getName());
		pizzaFound.setDescription(pizza.getDescription());
		pizzaFound.setImgUrl(pizza.getImgUrl());
		pizzaFound.setPriceInCents(pizza.getPriceInCents());
		pizzaService.save(pizzaFound);
		
		return new ResponseEntity<>(pizzaFound, HttpStatus.OK);
	}
	
}

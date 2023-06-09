package org.java.demo.api.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Pizza;
import org.java.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class CustomRestApiController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/hello")
	public ResponseEntity<String> helloUniverse() {
		
		return new ResponseEntity<>("Hello Universe!", HttpStatus.OK);
	}
	
	@GetMapping("/ourPizzas") 
	public ResponseEntity<List<Pizza>> pizzasList() {

		List<Pizza> pizzas = pizzaService.findAll();
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}
	
	@PostMapping("/ourPizzas") 
	public ResponseEntity<List<Pizza>> pizzasSearchedList(@RequestBody(required = false) String userSearch) {

		if(userSearch == null || userSearch == "") {

			List<Pizza> pizzas = pizzaService.findAll();
			return new ResponseEntity<>(pizzas, HttpStatus.OK);
		} else {
			
			System.out.println(userSearch);
			String stringToSearch = userSearch.substring(0, userSearch.length()-1);
			System.out.println(stringToSearch);
			List<Pizza> pizzas = pizzaService.findByName(stringToSearch);
			return new ResponseEntity<>(pizzas, HttpStatus.OK);
		}
	}
	
	
	@PostMapping("/ourPizzas/create")
	public ResponseEntity<Pizza> createByVue(@RequestBody Pizza pizza) {
		
		pizzaService.save(pizza);
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@DeleteMapping("/ourPizzas/delete/{id}")
	public String deletePizza(@PathVariable("id") Integer id) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		
		pizza.getIngredients().clear();
		pizzaService.deletePizza(pizza);
	
		return "Pizza eliminata con successo!";
	}
}

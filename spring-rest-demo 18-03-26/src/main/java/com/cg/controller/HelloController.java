package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//or we can write ("abc/{n} ) but we have to write @PathVariable("n") at sayHello method)
//@Controller

@RestController
public class HelloController {
	@GetMapping("abc/{name}") 	
	public String sayHello(@PathVariable String name) {
		return "Hello World "+name;
	}
}

package com.br.vendas.webservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.vendas.model.Cliente;
import com.google.gson.Gson;

@RestController
@RequestMapping("/cliente")//liink de uso : http://localhost:8080/SpringMVCSpringDataJPA/cliente
@CrossOrigin(origins="*")
public class ClienteRestService {

	@Autowired
	private IClienteRepository service;
	
	@GetMapping(produces="application/json")
	@ResponseBody
	public String listaCliente(){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>passou");
		System.out.println("dentro do metodo");
		Iterable<Cliente> listaEventos = service.findAll();
		
		String json = new Gson().toJson(listaEventos);
		return json;
	}
}

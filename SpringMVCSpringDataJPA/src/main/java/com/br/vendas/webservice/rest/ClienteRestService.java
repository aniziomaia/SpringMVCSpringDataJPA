package com.br.vendas.webservice.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.vendas.exception.ClienteNullPointerRestException;
import com.br.vendas.model.Cliente;

@RestController
@RequestMapping("/cliente")//liink de uso : http://localhost:8080/SpringMVCSpringDataJPA/cliente
@CrossOrigin(origins="*")
public class ClienteRestService {

	@Autowired
	private IClienteRepository service;
	
	//exemplo com json manual
//	@GetMapping(produces="application/json")
//	//@ResponseBody
//	public String listaCliente(){
//		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>passou");
//		System.out.println("dentro do metodo");
//		Iterable<Cliente> listaEventos = service.findAll();
//		
//		String json = new Gson().toJson(listaEventos);
//		return json;
//	}
	
	//@GetMapping(produces="application/json")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Iterable<Cliente> listaCliente(){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>llistar");
		Iterable<Cliente> listaEventos = service.findAll();
		
		return listaEventos;
	}
	
	
    //@PostMapping(produces="application/json")
	//@RequestMapping(value = "/save", produces = "application/json", method = RequestMethod.POST)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Cliente inserirCliente(@Valid @RequestBody Cliente cliente) {
		
		System.out.println(">>>>>>>>>>>>>>>dentro do save: " + cliente);
		//teste de expecifico exception
		//if(true)throw new ClienteNullPointerRestException("EXISTE DADOS EM BRANCO");
		
		return service.save(cliente);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String excluirCliente(@RequestBody Cliente cliente) {
		
		System.out.println(">>>>>>>>>>>>>>>dentro do excluirCliente<<<<<<<<<<<<<<<< ");
		service.delete(cliente);
		return "Sucesso";
		
	}
	
	//exemplo de URL para @PathVariable:  http://localhost:8080/SpringMVCSpringDataJPA/cliente/id/1
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Cliente pesquisaPorId(@PathVariable(value = "id") Long id) {
		System.out.println(">>>>>>>>>>>>>>>  dentro do pesquisaPorId<<<<<<<<<<<<<<<< ");
		Cliente cliente = service.findById(id);
		if(cliente == null) {
			throw new ClienteNullPointerRestException("DADOS NÃO ENCONTRADOS");
		}
		
		return cliente;
	}
	
	//Exemplo de URL para @RequestParam: http://localhost:8080/SpringMVCSpringDataJPA/cliente?name=Mardency
	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> pesquisaPorNome(@RequestParam(value = "name") String name) {
		System.out.println(">>>>>>>>>>>>>>>  dentro do pesquisaPorNome<<<<<<<<<<<<<<<< ");
		List<Cliente> clientes = service.findByName(name);
		if(clientes == null || clientes.isEmpty()) {
			ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(clientes);
	}
	
	//Exemplo de URL para @PathVariable: http://localhost:8080/SpringMVCSpringDataJPA/cliente/email/gfdgsgs@gfsdhhd
	//teve problema de ambiguidade com o metodo de busca por id, po isso ficou assim
	//se fizer a busca por um email no formato correto, nao entra no metodo, devido o .com
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Cliente pesquisaPorEmail(@PathVariable(value = "email") String email) {
		System.out.println(">>>>>>>>>>>>>>>  dentro do pesquisaPorEmail<<<<<<<<<<<<<<<< " + email);
		Cliente cliente = service.findByEmail(email);
		if(cliente == null) {
			throw new ClienteNullPointerRestException("DADOS NÃO ENCONTRADOS");
		}
		
		return cliente;
	}
}

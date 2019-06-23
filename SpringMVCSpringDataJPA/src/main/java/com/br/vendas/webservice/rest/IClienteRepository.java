package com.br.vendas.webservice.rest;

import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.br.vendas.model.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, String>{

	Cliente findById(Long id);
	List<Cliente> findByName(String name);
	Cliente findByEmail(String email);
}

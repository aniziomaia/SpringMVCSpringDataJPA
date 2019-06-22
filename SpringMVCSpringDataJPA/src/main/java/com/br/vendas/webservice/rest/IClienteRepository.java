package com.br.vendas.webservice.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.br.vendas.model.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, String>{

	//Cliente findByCodigo(long id);
}

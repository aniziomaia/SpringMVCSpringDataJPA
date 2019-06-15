package com.br.vendas.repoisitory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.vendas.model.Cliente;

/**
 * Note que esta classe � anotada com a anota��o @Transactional, 
 * ent�o todos os seus m�todos ser�o interceptados pelo Spring Data JPA 
 * para gerenciamento de transa��es. E uma inst�ncia da interface CustomerRepository 
 * ser� injetada nessa classe:
 * 
 * Isso � como m�gica, j� que n�o escrevemos nenhum c�digo DAO, mas o Spring Data JPA 
 * gerar� uma implementa��o automaticamente em tempo de execu��o.
   E como voc� pode ver, todos os m�todos nesta classe s�o para opera��es CRUD. 
   Ele simplesmente delega toda a chamada para um objeto CustomerRepository . 
   Essa classe parece ser redundante, mas � necess�ria para dissociar a camada de 
   neg�cios / servi�o da camada do reposit�rio / DAO.

 * @author aniziomaia
 *
 */
@Service
@Transactional
public class CustomerService {
	
    @Autowired 
    ICustomerRepository repo;
    
    public void save(Cliente customer) {
        repo.save(customer);
    }
     
    public List<Cliente> listAll() {
        return (List<Cliente>) repo.findAll();
    }
     
    public Cliente get(Long id) {
        return repo.findById(id).get();
    }
     
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
    public List<Cliente> search(String keyword) {
        return repo.search(keyword); 
    }
     
}
package com.br.vendas.repoisitory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.vendas.model.Cliente;

/**
 * Note que esta classe é anotada com a anotação @Transactional, 
 * então todos os seus métodos serão interceptados pelo Spring Data JPA 
 * para gerenciamento de transações. E uma instância da interface CustomerRepository 
 * será injetada nessa classe:
 * 
 * Isso é como mágica, já que não escrevemos nenhum código DAO, mas o Spring Data JPA 
 * gerará uma implementação automaticamente em tempo de execução.
   E como você pode ver, todos os métodos nesta classe são para operações CRUD. 
   Ele simplesmente delega toda a chamada para um objeto CustomerRepository . 
   Essa classe parece ser redundante, mas é necessária para dissociar a camada de 
   negócios / serviço da camada do repositório / DAO.

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
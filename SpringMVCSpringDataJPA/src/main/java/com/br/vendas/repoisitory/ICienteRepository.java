package com.br.vendas.repoisitory;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.vendas.model.Cliente;

/**
 * Você vê, este é quase o código que precisamos para a camada de acesso a dados. 
 * Mortalmente simples, certo? Assim como no Spring Data JPA, você não precisa escrever 
 * nenhum código do DAO. Apenas declare uma interface que estenda a interface CrudRepository , 
 * que define métodos CRUD como save () , findAll () , findById () , deleteById () , 
 * etc. No tempo de execução, o Spring Data JPA gera automaticamente o código de implementação.
   Note-se que temos de especificar o tipo da classe modelo e tipo do campo de chave primária ao 
   estender o CrudRepository de interface: CrudRepository <Customer, Long>
 * @author aniziomaia
 *
 */

public interface ICienteRepository extends CrudRepository<Cliente, Long> {
     
	@Query(value = "SELECT c FROM Cliente c WHERE c.name LIKE '%' || :keyword || '%'"
            + " OR c.email LIKE '%' || :keyword || '%'"
            + " OR c.address LIKE '%' || :keyword || '%'")
    public List<Cliente> search(@Param("keyword") String keyword); 
}

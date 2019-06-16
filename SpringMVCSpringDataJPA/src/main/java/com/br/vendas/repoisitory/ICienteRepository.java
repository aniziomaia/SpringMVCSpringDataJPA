package com.br.vendas.repoisitory;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.vendas.model.Cliente;

/**
 * Voc� v�, este � quase o c�digo que precisamos para a camada de acesso a dados. 
 * Mortalmente simples, certo? Assim como no Spring Data JPA, voc� n�o precisa escrever 
 * nenhum c�digo do DAO. Apenas declare uma interface que estenda a interface CrudRepository , 
 * que define m�todos CRUD como save () , findAll () , findById () , deleteById () , 
 * etc. No tempo de execu��o, o Spring Data JPA gera automaticamente o c�digo de implementa��o.
   Note-se que temos de especificar o tipo da classe modelo e tipo do campo de chave prim�ria ao 
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

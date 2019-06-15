package com.br.vendas.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Para ativar o Spring Data JPA, precisamos criar dois beans: 
 * EntityManagerFactory e JpaTransactionManager .
 *  Então crie outra classe de configuração chamada JpaConfig com o seguinte código:
 *  
 *  - @EnableJpaRepositories : isso informa ao Spring Data JPA para procurar por classes 
 *  de repositório no pacote especificado (diretorio raiz) para injetar código relevante em tempo de execução.
 *  - @EnableTransactionManagement : isso informa ao Spring Data JPA para gerar código para 
 *  o gerenciamento de transações no tempo de execução.
 *  
 *  Nesta classe, o primeiro método cria uma instância de EntityManagerFactory para gerenciar a 
 *  unidade de persistência vendaUP (esse nome é especificado no arquivo persistence.xml).
    E o último método cria uma instância do JpaTransactionManager para o EntityManagerFactory 
    criado pelo primeiro método.Essa é a configuração mínima necessária para usar o Spring Data JPA.
 * @author aniziomaia
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.br.vendas"})
@EnableTransactionManagement
public class JpaConfig {
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("vendaUP");
          
        return factoryBean;
    }
      
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
          
        return transactionManager;
    } 
}

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
 *  Ent�o crie outra classe de configura��o chamada JpaConfig com o seguinte c�digo:
 *  
 *  - @EnableJpaRepositories : isso informa ao Spring Data JPA para procurar por classes 
 *  de reposit�rio no pacote especificado (diretorio raiz) para injetar c�digo relevante em tempo de execu��o.
 *  - @EnableTransactionManagement : isso informa ao Spring Data JPA para gerar c�digo para 
 *  o gerenciamento de transa��es no tempo de execu��o.
 *  
 *  Nesta classe, o primeiro m�todo cria uma inst�ncia de EntityManagerFactory para gerenciar a 
 *  unidade de persist�ncia vendaUP (esse nome � especificado no arquivo persistence.xml).
    E o �ltimo m�todo cria uma inst�ncia do JpaTransactionManager para o EntityManagerFactory 
    criado pelo primeiro m�todo.Essa � a configura��o m�nima necess�ria para usar o Spring Data JPA.
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

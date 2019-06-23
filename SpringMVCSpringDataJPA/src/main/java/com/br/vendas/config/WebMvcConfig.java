package com.br.vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Esta classe � anotada com a anota��o @Configuratio n para informar ao 
 * framework Spring que esta � uma classe de configura��o. 
 * A anota��o @ComponentScan informa ao Spring para verificar 
 * as classes de configura��o no pacote model onde estao as entidades.
   Nesta classe, simplesmente criamos um bean de resolu��o de vis�o que especifica o
    prefixo e o sufixo dos arquivos de vis�o. 
    Portanto, crie as visualiza��es de diret�rio sob o 
    diret�rio WebContent / WEB-INF para armazenar arquivos detala.
 * @author aniziomaia
 *
 */
@Configuration
@ComponentScan("com.br.vendas")
@EnableWebMvc
public class WebMvcConfig {
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}

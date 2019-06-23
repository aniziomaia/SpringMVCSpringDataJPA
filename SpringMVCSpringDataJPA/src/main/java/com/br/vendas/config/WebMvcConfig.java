package com.br.vendas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Esta classe é anotada com a anotação @Configuratio n para informar ao 
 * framework Spring que esta é uma classe de configuração. 
 * A anotação @ComponentScan informa ao Spring para verificar 
 * as classes de configuração no pacote model onde estao as entidades.
   Nesta classe, simplesmente criamos um bean de resolução de visão que especifica o
    prefixo e o sufixo dos arquivos de visão. 
    Portanto, crie as visualizações de diretório sob o 
    diretório WebContent / WEB-INF para armazenar arquivos detala.
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

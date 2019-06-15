package com.br.vendas.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
 
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Para usar o Spring MVC para nosso aplicativo da Web em Java,
 *  precisamos registrar o servlet do Spring Dispatcher na inicializa��o 
 *  do aplicativo codificando a seguinte classe:
 * @author aniziomaia
 *
 */
public class WebAppInitializer implements WebApplicationInitializer {
    //@Override
	/**
	 * O m�todo onStartup () dessa classe ser� invocado automaticamente pelo cont�iner 
	 * do servlet quando o aplicativo estiver sendo carregado. 
	 * O Spring Dispatcher Servlet manipula todas as solicita��es por meio do mapeamento 
	 * de URL "/" e procura a configura��o na classe WebMvcConfig , descrita abaixo.
	 */
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(WebMvcConfig.class);
          
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher", new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
          
    }
}

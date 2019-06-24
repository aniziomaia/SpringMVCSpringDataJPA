package com.br.vendas.schedule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.br.vendas.model.Cliente;
import com.br.vendas.webservice.rest.IClienteRepository;

@Configuration
@EnableScheduling
public class SystemSchedule {

	@Autowired
	private IClienteRepository service;
	
	public static final String DELAY = "FIXED_DELEY";
	public static final String RATE  = "FIXED_RATE";
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static String validate(Cliente cliente, String TIPO_CHAMADA) {
		String resultado = "";
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(cliente.getEmail());
        if(!matcher.find()) {
        	System.out.println("CHAMADA DO TIPO: " + TIPO_CHAMADA);
        	resultado = "O SCHEDULE INFORMA QUE O CLIENTE " + cliente.getName() + " POSSUI E-MAIL FORA DO PADR�O: " + cliente.getEmail();
        }else {
        	resultado="";
        }
        return resultado;
	}
	
	/**
	 * A cada 5 minutos
	 * Nesse caso, a dura��o entre o final da �ltima execu��o e o in�cio da pr�xima execu��o 
	 * � fixa. A tarefa sempre espera at� que a anterior seja conclu�da.Essa op��o deve ser usada quando 
	 * for obrigat�rio que a execu��o anterior seja conclu�da antes de ser executada novamente.
	 */
	@Scheduled(fixedDelay = 300000)
	public void scheduleVerifyEmailDalay() {
	    Iterable<Cliente> listaClientes = service.findAll();
	    listaClientes.forEach(c -> System.out.println(validate(c, DELAY)));
	}
	
	/**
	 * A cada 10 minutos
	 * N�o vamos executar uma tarefa em um intervalo fixo de tempo
	 * Observe que o in�cio da execu��o da tarefa n�o aguarda a conclus�o da execu��o anterior.
	 * Esta op��o deve ser usada quando cada execu��o da tarefa for independente.
	 */
	@Scheduled(fixedRate = 600000)
	public void scheduleVerifyEmailRate() {
	    Iterable<Cliente> listaClientes = service.findAll();
	    listaClientes.forEach(c -> System.out.println(validate(c, RATE)));
	}
}

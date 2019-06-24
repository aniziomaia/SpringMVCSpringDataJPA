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
        	resultado = "O SCHEDULE INFORMA QUE O CLIENTE " + cliente.getName() + " POSSUI E-MAIL FORA DO PADRÃO: " + cliente.getEmail();
        }else {
        	resultado="";
        }
        return resultado;
	}
	
	/**
	 * A cada 5 minutos
	 * Nesse caso, a duração entre o final da última execução e o início da próxima execução 
	 * é fixa. A tarefa sempre espera até que a anterior seja concluída.Essa opção deve ser usada quando 
	 * for obrigatório que a execução anterior seja concluída antes de ser executada novamente.
	 */
	@Scheduled(fixedDelay = 300000)
	public void scheduleVerifyEmailDalay() {
	    Iterable<Cliente> listaClientes = service.findAll();
	    listaClientes.forEach(c -> System.out.println(validate(c, DELAY)));
	}
	
	/**
	 * A cada 10 minutos
	 * Não vamos executar uma tarefa em um intervalo fixo de tempo
	 * Observe que o início da execução da tarefa não aguarda a conclusão da execução anterior.
	 * Esta opção deve ser usada quando cada execução da tarefa for independente.
	 */
	@Scheduled(fixedRate = 600000)
	public void scheduleVerifyEmailRate() {
	    Iterable<Cliente> listaClientes = service.findAll();
	    listaClientes.forEach(c -> System.out.println(validate(c, RATE)));
	}
}

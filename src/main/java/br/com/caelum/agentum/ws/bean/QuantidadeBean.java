package br.com.caelum.agentum.ws.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.caelum.agentum.ws.modelo.Quantidade;

@Configuration
public class QuantidadeBean {

	@Bean
	public Quantidade getQuantidadeMeses() {
		return Quantidade.TRES;
	}
	
}

package br.com.caelum.agentum.ws.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import br.com.caelum.agentum.ws.modelo.Data;
import br.com.caelum.agentum.ws.modelo.GeradorNegociacoes;
import br.com.caelum.agentum.ws.modelo.Quantidade;

@Configuration
public class GeradorNegociacoesBean {

	@Bean @Scope("request")
	public GeradorNegociacoes getGeradorNegociacoes() {

		Quantidade dois = Quantidade.DOIS;
		Data hoje = new Data();
		Data passado = hoje.mesesAtras(dois);

		return new GeradorNegociacoes(passado, hoje);
	}
}

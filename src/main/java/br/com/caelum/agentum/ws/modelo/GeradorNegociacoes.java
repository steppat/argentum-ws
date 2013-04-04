package br.com.caelum.agentum.ws.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class GeradorNegociacoes {

	private final List<Negociacao> negociacoes;
	private final Data dataInicio;
	private final Data dataFinal;
    
	public GeradorNegociacoes(Data dataInicio, Data dataFinal) {
    		this.dataInicio = dataInicio;
		this.dataFinal = dataFinal;
		this.negociacoes = new ArrayList<Negociacao>();
	}

	public List<Negociacao> getNegociacoes() {
		
		geraNegociacoesEntreDatas();
		
		return negociacoes;
	}


	private void geraNegociacoesEntreDatas() {
		
		Data dataCorrente = this.dataInicio;
		
		while (dataCorrente.ehAntes(dataFinal)) {

			Random random = geraRandomComSeed(dataCorrente.millis());
			int totalDeNegociosNesseDia = geraTotalDeNegociacoesBaseadoNo(random);

			for (int i = 0; i < totalDeNegociosNesseDia; i++) {
				Negociacao n = geraRandomNegociacaoPara(dataCorrente.toCalendar(), random);
				negociacoes.add(n);
			}

			dataCorrente = dataCorrente.adicionDias(Quantidade.UM);
		}
	}

	private int geraTotalDeNegociacoesBaseadoNo(Random random) {
		return (int) (random.nextDouble() +1 * 4);
	}

	private Random geraRandomComSeed(Long seed) {
		return new Random(seed);
	}

	private Negociacao geraRandomNegociacaoPara(Calendar data, Random random) {
		int quantidade = (int) ((random.nextDouble() + 1) * 13 );
		double preco= round2((random.nextDouble() + quantidade) * 17);
		return new Negociacao(preco, quantidade, data);
	}
	
	private double round2(double value) {
		double result = value * 100;
		result = Math.round(result);
		result = result / 100;
		return result;
	}
}

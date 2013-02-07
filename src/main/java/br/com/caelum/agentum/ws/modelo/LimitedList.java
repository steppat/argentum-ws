package br.com.caelum.agentum.ws.modelo;

import java.util.LinkedList;
import java.util.List;



public class LimitedList {

	private List<Negocio> lista = new LinkedList<Negocio>();
	private Quantidade meses;

	public LimitedList(Quantidade meses) {
		this.meses = meses;
	}

	public boolean add(Negocio elemento) {

		Data dataLimite = new Data().mesesAtras(meses);

		while (primeiroNegocioEhVelhoDemais(dataLimite)) {
			lista.remove(0);
		}

		return lista.add(elemento);
	}

	private boolean primeiroNegocioEhVelhoDemais(Data limite) {

		if (this.lista.isEmpty())
			return false;

		Data dataDoPrimeiroNegocio = new Data(this.lista.get(0).getData());
		
		return dataDoPrimeiroNegocio.ehAntes(limite);
	}

	public List<Negocio> toList() {
		return this.lista;
	}

	public boolean isEmtpy() {
		return this.lista.isEmpty();
	}

}

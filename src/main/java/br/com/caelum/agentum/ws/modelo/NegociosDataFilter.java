package br.com.caelum.agentum.ws.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NegociosDataFilter {

	private final List<Negocio> lista;

	public NegociosDataFilter(List<Negocio> lista) {
		this.lista = lista;
	}

	public List<Negocio> pelaData(Calendar data) {
		List<Negocio> negocios = new ArrayList<Negocio>();

		for (Negocio negocio : lista) {
			if (negocio.getData().after(data)) {
				negocios.add(negocio);
			}
		}
		return negocios;
	}

}

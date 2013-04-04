package br.com.caelum.argentum.ws.xml;

import java.io.OutputStream;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import br.com.caelum.agentum.ws.modelo.Negociacao;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class NegociacaoXmlizer {

	private final XStream stream;
	private final List<Negociacao> negociacoes;

	public NegociacaoXmlizer(List<Negociacao> negociacoes) {
		this.negociacoes = negociacoes;
		this.stream = new XStream(new DomDriver());
		this.stream.alias("negociacao", Negociacao.class);
		this.stream.alias("list", LinkedList.class);
	}

	@SuppressWarnings("unchecked")
	public List<Negociacao> fromXML(Reader reader) {
		return (List<Negociacao>) this.stream.fromXML(reader);
	}

	public void toXML(OutputStream os) {
		this.stream.toXML(negociacoes, os);
	}

	public String toXML() {
		return this.stream.toXML(negociacoes);		
	}

}

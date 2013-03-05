package br.com.caelum.argentum.ws.xml;

import java.io.OutputStream;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import br.com.caelum.agentum.ws.modelo.Negocio;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class NegociosXmlizer {

	private final XStream stream;
	private final List<Negocio> negocios;

	public NegociosXmlizer(List<Negocio> negocios) {
		this.negocios = negocios;
		this.stream = new XStream(new DomDriver());
		this.stream.alias("negocio", Negocio.class);

		this.stream.alias("list", LinkedList.class);
	}

	@SuppressWarnings("unchecked")
	public List<Negocio> fromXML(Reader reader) {
		return (List<Negocio>) this.stream.fromXML(reader);
	}

	public void toXML(OutputStream os) {
		this.stream.toXML(negocios, os);
	}

	public String toXML() {
		return this.stream.toXML(negocios);		
	}

}

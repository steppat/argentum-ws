package br.com.caelum.agentum.ws.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.caelum.agentum.ws.modelo.GeradorNegociacoes;
import br.com.caelum.agentum.ws.modelo.Negociacao;
import br.com.caelum.argentum.ws.xml.NegociacaoXmlizer;

@Controller
@Scope("request")
public class NegociacaoController {
	
	
	@Autowired
	GeradorNegociacoes gerador;
	

	@RequestMapping(value = "/negociacoes", method = RequestMethod.GET)
	@ResponseBody
	public void negocios(HttpServletResponse response) throws IOException, ParseException  {
		
		List<Negociacao> negociacoes = gerador.getNegociacoes();

		new NegociacaoXmlizer(negociacoes).toXML(response.getOutputStream());
		
		response.setContentType("application/xml");
		response.flushBuffer();
	}

}

package br.com.caelum.agentum.ws.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.caelum.agentum.ws.modelo.GeradorDeNegocios;
import br.com.caelum.agentum.ws.modelo.Negocio;
import br.com.caelum.agentum.ws.modelo.NegociosDataFilter;
import br.com.caelum.argentum.ws.xml.NegociosXmlizer;

@Controller
@Scope("request")
public class NegociosController {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	GeradorDeNegocios gerador;

	
	@RequestMapping(value = "/negocios", method = RequestMethod.GET)
	@ResponseBody
	public void negocios(@RequestParam(value="data", required=false) String data, HttpServletResponse response) throws IOException, ParseException  {
		
		List<Negocio> negocios = gerador.getNegocios();

		if(data != null){
			negocios = filterNegocios(data, negocios);
		}
		
		new NegociosXmlizer(negocios).toXML(response.getOutputStream());
		
		response.setContentType("application/xml");
		response.flushBuffer();
	}


	private List<Negocio> filterNegocios(String dataAsString, List<Negocio> negocios)
			throws ParseException {
		Calendar dataFiltro = parseData(dataAsString);
		negocios = new NegociosDataFilter(negocios).pelaData(dataFiltro);
		return negocios;
	}


	private Calendar parseData(String data) throws ParseException {
		Date date = sdf.parse(data);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
}

package br.com.caelum.argentum.ws.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.agentum.ws.modelo.Data;
import br.com.caelum.agentum.ws.modelo.Quantidade;

public class DataTest {


	@Test
	public void deveCalcularCincoMesesAtras() {
	
		Calendar cal = new GregorianCalendar(2012, 1, 1);
		Data data = new Data(cal);
		
		Calendar umMesAtras = data.mesesAtras(Quantidade.CINCO).toCalendar();
		
		Assert.assertEquals(8, umMesAtras.get(Calendar.MONTH));
		Assert.assertEquals(2011, umMesAtras.get(Calendar.YEAR));
		Assert.assertEquals(1, umMesAtras.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void deveAdicionarCincoDias() {
	
		Calendar cal = new GregorianCalendar(2012, 1, 1);
		Data data = new Data(cal);
		
		Calendar umMesAtras = data.adicionDias(Quantidade.CINCO).toCalendar();
		
		Assert.assertEquals(1, umMesAtras.get(Calendar.MONTH));
		Assert.assertEquals(2012, umMesAtras.get(Calendar.YEAR));
		Assert.assertEquals(6, umMesAtras.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void deveSaberSeUmaDataEhAntesDaOutra() {
		
		Calendar cal = new GregorianCalendar(2012, 1, 1);
		Data data = new Data(cal);
		Calendar cal2 = new GregorianCalendar(2012, 1, 2);
		Data umDiaDepois = new Data(cal2);
	
		Assert.assertTrue(data.ehAntes(umDiaDepois));
	}

	@Test
	public void deveIgnoraAHoraParaSaberSeUmaDataEhAntesDaOutra() {
		
		Calendar cal = new GregorianCalendar(2012, 1, 1,12,00);
		Data data = new Data(cal);
		Calendar cal2 = new GregorianCalendar(2012, 1, 1,11,00);
		Data mesmaData = new Data(cal2);
	
		Assert.assertFalse(data.ehAntes(mesmaData));
	}

	@Test
	public void deveDevolverMillis() {

		Calendar cal = new GregorianCalendar(2012, 1, 1);
		Data data = new Data(cal);
		
		Assert.assertEquals(cal.getTimeInMillis(), data.millis().longValue());
	}

	@Test
	public void deveGuaradarADataSemHora() {

		Calendar cal = new GregorianCalendar(2012, 1, 1, 12, 30);
		Data dataSemHora = new Data(cal);
		Calendar calendarSemHora = new GregorianCalendar(2012, 1, 1);
	
		Assert.assertTrue(dataSemHora.toCalendar().equals(calendarSemHora));
	}

}

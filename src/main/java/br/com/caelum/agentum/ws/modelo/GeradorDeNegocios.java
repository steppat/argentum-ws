package br.com.caelum.agentum.ws.modelo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.caelum.argentum.ws.xml.NegociosXmlizer;

@Component
public class GeradorDeNegocios {

	private final LimitedList negocios;
	private final Data dataLimite;
    private Data dataUltimaGeracao;
    
    @Autowired
	public GeradorDeNegocios(Quantidade meses) {
    	
    		Data hoje = new Data();
    		dataLimite = hoje.mesesAtras(meses);
		this.dataUltimaGeracao = hoje.mesesAtras(meses);

		this.negocios = new LimitedList(meses);
	}

	public List<Negocio> getNegocios() {
		
		Data hoje = new Data();
		
		if(negociosJaForamGerados(hoje) ) {
			return negocios.toList();
		}
		
		dataUltimaGeracao = hoje;
		
		Data dataCorrente = new Data(dataLimite.getCalendar());
		geraNegociosEntreDatas(dataCorrente, hoje);
		
		return negocios.toList();
	}

	private boolean negociosJaForamGerados(Data hoje) {
		return dataUltimaGeracao.equals(hoje);
	}

	private void geraNegociosEntreDatas(Data dataCorrente, Data hoje) {
		while (dataCorrente.ehAntes(hoje)) {

			Random random = geraRandomComSeed(dataCorrente.millis());
			int totalDeNegociosNesseDia = geraTotalDeNegociosBaseadoNo(random);

			for (int i = 0; i < totalDeNegociosNesseDia; i++) {
				Negocio n = geraRandomNegocioPara(dataCorrente.getCalendar(), random);
				negocios.add(n);
			}

			adicioneUmDiaNa(dataCorrente);
		}
	}

	private int geraTotalDeNegociosBaseadoNo(Random random) {
		return (int) (random.nextDouble() +1 * 4);
	}

	private Random geraRandomComSeed(Long seed) {
		return new Random(seed);
	}

	private void adicioneUmDiaNa(Data dataCorrente) {
		dataCorrente.adicioneMes(Quantidade.UM);
	}
	
	private Negocio geraRandomNegocioPara(Calendar data, Random random) {
		int quantidade = (int) ((random.nextDouble() + 1) * 13 );
		double preco= round2((random.nextDouble() + quantidade) * 17);
		return new Negocio(preco, quantidade, data);
	}
	
	private double round2(double value) {
		double result = value * 100;
		result = Math.round(result);
		result = result / 100;
		return result;
		}

	

	
	public static void main(String[] args) throws IOException {
		Quantidade meses = Quantidade.DEZ;
		GeradorDeNegocios gerado = new GeradorDeNegocios(meses);
		
		long millis = System.currentTimeMillis();
		List<Negocio> negocios = gerado.getNegocios();
		System.out.println(negocios.size());
		System.out.println(System.currentTimeMillis() - millis);
		
		millis = System.currentTimeMillis();
		negocios = gerado.getNegocios();
		System.out.println(negocios.size());
		System.out.println(System.currentTimeMillis() - millis);

		millis = System.currentTimeMillis();
		OutputStream os = new FileOutputStream("xml.xml");
		new NegociosXmlizer(negocios).toXML(os);
		os.close();
		System.out.println(System.currentTimeMillis() - millis);
		
	}
}

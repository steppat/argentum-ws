package br.com.caelum.agentum.ws.modelo;

import java.util.Calendar;


public class Data {
	
	private final Calendar instance;
//	private final DataHelper helper = new DataHelper();

	public Data(Calendar instance) {
		this.instance = this.semHora((Calendar) instance.clone());
	}
	
	private Data(Calendar instance, boolean nada) {
		this.instance = instance;
	}
	
	public Data() {
		this.instance = this.hojeSemHora();
	}

	private Calendar semHora(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
		return cal;
}

	
	private Calendar hojeSemHora() {
		Calendar instance = Calendar.getInstance();
		return semHora(instance);
	}

	public Calendar getCalendar() {
		return (Calendar) instance.clone();
	}
	
	public Data mesesAtras(Quantidade meses) {
		Calendar cal = this.getCalendar();
		cal.add(Calendar.MONTH, - meses.ordinal());
		return new Data(cal,true);
	}

	public boolean ehAntes(Data outra) {
		return this.instance.before(outra.instance);
	}

	public Long millis() {
		return this.instance.getTimeInMillis();
	}

	public void adicioneMes(Quantidade um) {
		this.instance.add(Calendar.DAY_OF_MONTH, 1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((instance == null) ? 0 : instance.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		if (instance == null) {
			if (other.instance != null)
				return false;
		} else if (!instance.equals(other.instance))
			return false;
		return true;
	}

}

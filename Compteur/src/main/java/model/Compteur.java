package model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class Compteur
{
	private String nom ;
	private Locale locale ;
	private Calendar fin ;
	
	public Compteur(String nom, Locale locale, Calendar fin) {
		super();
		this.nom = nom;
		this.locale = locale;
		this.fin = fin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Calendar getFin() {
		return fin;
	}

	public void setFin(Calendar fin) {
		this.fin = fin;
	}
	
	
	
	/*
	public static void main(String[] args) throws ParseException
	{
		Calendar calendrier = Calendar.getInstance(Locale.FRENCH);
		System.out.println(calendrier.getTime().toString());
		
		Calendar date = Calendar.getInstance();
	    date.set(Calendar.YEAR, 2000);
	    date.set(Calendar.MONTH, 12-1);
	    date.set(Calendar.DAY_OF_MONTH, 31);
	    date.set(Calendar.HOUR, 13);
	    date.set(Calendar.MINUTE, 30);
	    date.set(Calendar.SECOND, 20);

	    System.out.println(date.getTime().toString());

	    String string1 = calendrier.getTime().toString().replace("CET", "") ;
	    String string2 = date.getTime().toString().replace("CET", "")  ;

	    DateTimeFormatter dtf = DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss  yyyy").withLocale(Locale.ENGLISH);

	    DateTime dateTime1 = dtf.parseDateTime(string1);
	    DateTime dateTime2 = dtf.parseDateTime(string2);
	    Period period = new Period(dateTime1, dateTime2);

	    PeriodFormatter formatter = new PeriodFormatterBuilder()
	        .appendYears().appendSuffix(" years ")
	        .appendMonths().appendSuffix(" months ")
	        .appendWeeks().appendSuffix(" weeks ")
	        .appendDays().appendSuffix(" days ")
	        .appendHours().appendSuffix(" hours ")
	        .appendMinutes().appendSuffix(" minutes ")
	        .appendSeconds().appendSuffix(" seconds ")
	        .printZeroNever()
	        .toFormatter();

	    
	    String elapsed = formatter.print(period);
	    System.out.println(elapsed);
	    
	}
	*/
}

package model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.json.JSONArray;
import org.json.JSONException;

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
	
	public Calendar getEnd() {
		return this.fin ;
	}

	public String getFin() {
		return fin.get(Calendar.YEAR)
    			+" "+ String.format("%02d",fin.get(Calendar.MONTH))
    			+" "+ String.format("%02d",fin.get(Calendar.DAY_OF_MONTH))
    			+" "+ String.format("%02d",fin.get(Calendar.HOUR_OF_DAY))
    			+" "+ String.format("%02d",fin.get(Calendar.MINUTE))
    			+" "+ String.format("%02d",fin.get(Calendar.SECOND));
	}

	public void setFin(Calendar fin) {
		this.fin = fin;
	}
	
	@Override
	public String toString() {
		return nom+" "+locale+" "+getFin();
	}
	
	public String getJson()
	{
		return "{ \"nom\": \""+nom+"\", \"locale\": \""+locale.getLanguage() + "," + locale.getCountry()+"\", \"endY\": \""+fin.get(Calendar.YEAR)+"\""
																										+", \"endM\":\""+fin.get(Calendar.MONTH)+"\""
																										+", \"endJ\":\""+fin.get(Calendar.DAY_OF_MONTH)+"\""	
																										+", \"endH\":\""+fin.get(Calendar.HOUR_OF_DAY)+"\""
																										+", \"endMin\":\""+fin.get(Calendar.MINUTE)+"\""
																										+", \"endS\":\""+fin.get(Calendar.SECOND)+"\" }";
	}
	
	public static Locale stringToLocale(String s)
	{
	    StringTokenizer tempStringTokenizer = new StringTokenizer(s,",");
	    String l = "", c = "" ;
	    if(tempStringTokenizer.hasMoreTokens())
		    l = (String) tempStringTokenizer.nextElement();
		
	    if(tempStringTokenizer.hasMoreTokens())
		    c = (String) tempStringTokenizer.nextElement();
		
	    return new Locale(l,c);
	}
	
	public static HashMap<String, Compteur> cookiesToMap(String json)
	{
		HashMap<String, Compteur> compteurs = new HashMap<String, Compteur>();
		
		JSONArray arr;
		Compteur c ;
		try 
		{
			arr = new JSONArray(json);

			for (int i = 0; i < arr.length(); i++)
			{
				String nom = arr.getJSONObject(i).getString("nom");
				String locale = arr.getJSONObject(i).getString("locale");
				int anneeFin = Integer.parseInt(arr.getJSONObject(i).getString("endY"));
				int moisFin = Integer.parseInt(arr.getJSONObject(i).getString("endM"));
				int jourFin = Integer.parseInt(arr.getJSONObject(i).getString("endJ"));
				int heureFin = Integer.parseInt(arr.getJSONObject(i).getString("endH"));
				int minuteFin = Integer.parseInt(arr.getJSONObject(i).getString("endMin"));
				int secondeFin = Integer.parseInt(arr.getJSONObject(i).getString("endS"));
				
				Locale l = Compteur.stringToLocale(locale) ;
				
				Calendar dateFin = Calendar.getInstance(l);
				dateFin.set(Calendar.YEAR, anneeFin);
				dateFin.set(Calendar.MONTH, moisFin);
				dateFin.set(Calendar.DAY_OF_MONTH, jourFin);
				dateFin.set(Calendar.HOUR_OF_DAY, heureFin);
				dateFin.set(Calendar.MINUTE, minuteFin);
				dateFin.set(Calendar.SECOND, secondeFin);
				
				c = new Compteur(nom, l, dateFin);
				compteurs.put(nom, c);
			}
		} 
		catch (JSONException e) { e.printStackTrace(); }
		
		return compteurs ;
	}
	
	
	public static void main(String[] args) throws ParseException
	{
		/*Calendar calendrier = Calendar.getInstance();
		System.out.println(calendrier.getTime().toString());
		
		Calendar date = Calendar.getInstance();
	    date.set(Calendar.YEAR, 2000);
	    date.set(Calendar.MONTH, 12-1);
	    date.set(Calendar.DAY_OF_MONTH, 31);
	    date.set(Calendar.HOUR, 13);
	    date.set(Calendar.MINUTE, 30);
	    date.set(Calendar.SECOND, 20);

	    System.out.println(date.getTime().toString());
	    System.out.println(" ");

	    String string1 = calendrier.getTime().toString().replace("CET ", "") ;
	    String string2 = date.getTime().toString().replace("CET ", "")  ;

	    DateTimeFormatter dtf = DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss yyyy").withLocale(Locale.ENGLISH);

	    DateTime dateTime1 = dtf.parseDateTime(string1);
	    DateTime dateTime2 = dtf.parseDateTime(string2);
	    Period period = new Period(dateTime1, dateTime2);
*/
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

	    /*
	    String elapsed = formatter.print(period);
	    System.out.println(elapsed);
	   
	    */
	    Calendar date = Calendar.getInstance(Locale.getDefault());
	    date.set(Calendar.YEAR, 2016);
	    date.set(Calendar.MONTH, 11-1);
	    date.set(Calendar.DAY_OF_MONTH, 10);
	    date.set(Calendar.HOUR, 13);
	    date.set(Calendar.MINUTE, 00);
	    date.set(Calendar.SECOND, 00);

		DateTime dateFin = new DateTime(date);
		
		DateTime debu = DateTime.now();
	    
		System.out.println(debu);
	    System.out.println(dateFin);

		Interval interval = new Interval(debu, dateFin);

		DateTime start = interval.getStart();
		DateTime end = interval.getEnd();
		Chronology chrono = interval.getChronology();
		Duration duration = interval.toDuration();
		Period period = interval.toPeriod();
		
		System.out.println(formatter.print(period));

	}
	
}

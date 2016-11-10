package web ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import model.Compteur;


@ServerEndpoint("/majTime")
@Controller
public class Controlleur implements Service
{
	private static HashMap<String, ArrayList<Compteur>> compteurs ;
	
	public Controlleur() 
	{
		compteurs = new HashMap<String, ArrayList<Compteur>>() ;
		
		ArrayList<Compteur> test = new ArrayList<Compteur>() ;
		compteurs.put("a", test);
		
	}
	
    @OnMessage
    public void majCompteur(Session session, String msg, boolean last)
    {
        try 
        {
            while (session.isOpen())
            {
            	String cptsEnd = "["; // sous forme json
            	
            	int i = 0 ;
            	for( Compteur c : compteurs.get("a") )
            	{
            		cptsEnd += "{\"end\":\"";
            		
            		PeriodFormatter formatter = new PeriodFormatterBuilder()
            	        .appendYears().appendSuffix(" Ans ")
            	        .appendMonths().appendSuffix(" Mois ")
            	        .appendWeeks().appendSuffix(" Semaines ")
            	        .appendDays().appendSuffix(" Jours ")
            	        .appendHours().appendSuffix(" Heures ")
            	        .appendMinutes().appendSuffix(" Minutes ")
            	        .appendSeconds().appendSuffix(" Secondes ")
            	        //.printZeroNever()
            	        .toFormatter();
            		
            		DateTime currentDate = DateTime.now();
            		
            		Interval interval = new Interval(currentDate, new DateTime(c.getEnd()));
            		Period period = interval.toPeriod();
            		
            	    cptsEnd += formatter.print(period)+"\"}";

            	    i++ ;
            	    if ( i != compteurs.get("a").size() )
            	    	cptsEnd += ",";
            	}
            	cptsEnd += "]";

            	session.getBasicRemote().sendText(cptsEnd);
                //session.getBasicRemote().sendText("test");
            	Thread.sleep(500);
            }
        } 
        catch (IOException e)
        {
            try {
                session.close();
            } 
            catch (IOException e1) { // Ignore
            }
        } 
		catch (InterruptedException e) {
			try {
				session.close();
			} 
			catch (IOException e1) { // Ignore
			}
		}
    }
	
	@RequestMapping(value = "/test/{i}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void test(@PathVariable("i") int i)
	{
		System.out.println("COUCOU "+i);
	}
	
	@RequestMapping(value = "/addCompteur/{nom}/{locale}/{moisFin}/{jourFin}/{anneeFin}/{heureFin}/{minuteFin}/{secondeFin}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void addCompteur(@PathVariable("nom") String nom, @PathVariable("locale") String locale,	@PathVariable("moisFin") int moisFin,
																									@PathVariable("jourFin") int jourFin, 
																									@PathVariable("anneeFin") int anneeFin,
																									@PathVariable("heureFin") int heureFin,
																									@PathVariable("minuteFin") int minuteFin,
																									@PathVariable("secondeFin") int secondeFin)
	{
		System.out.println("HEY "+nom+" "+locale+" "+moisFin+" "+jourFin+" "+anneeFin+" "+heureFin+" "+minuteFin+" "+secondeFin);
		
		Locale l = toLocale(locale) ;

		Calendar dateFin = Calendar.getInstance(l);
		dateFin.set(Calendar.YEAR, anneeFin);
		dateFin.set(Calendar.MONTH, moisFin-1);
		dateFin.set(Calendar.DAY_OF_MONTH, jourFin);
		dateFin.set(Calendar.HOUR_OF_DAY, heureFin);
		dateFin.set(Calendar.MINUTE, minuteFin);
		dateFin.set(Calendar.SECOND, secondeFin);
			
		Compteur c = new Compteur(nom, l, dateFin);
		System.out.println(c);
		compteurs.get("a").add(c) ;
	}


	public static Locale toLocale(String str) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (len != 2 && len != 5 && len < 7) {
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        char ch0 = str.charAt(0);
        char ch1 = str.charAt(1);
        if (ch0 < 'a' || ch0 > 'z' || ch1 < 'a' || ch1 > 'z') {
            throw new IllegalArgumentException("Invalid locale format: " + str);
        }
        if (len == 2) {
            return new Locale(str, "");
        } else {
            if (str.charAt(2) != '_') {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            char ch3 = str.charAt(3);
            if (ch3 == '_') {
                return new Locale(str.substring(0, 2), "", str.substring(4));
            }
            char ch4 = str.charAt(4);
            if (ch3 < 'A' || ch3 > 'Z' || ch4 < 'A' || ch4 > 'Z') {
                throw new IllegalArgumentException("Invalid locale format: " + str);
            }
            if (len == 5) {
                return new Locale(str.substring(0, 2), str.substring(3, 5));
            } else {
                if (str.charAt(5) != '_') {
                    throw new IllegalArgumentException("Invalid locale format: " + str);
                }
                return new Locale(str.substring(0, 2), str.substring(3, 5), str.substring(6));
            }
        }
    }

	@RequestMapping(value = "/getCompteurs", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Compteur> getCompteurs()
	{
		return compteurs.get("a"); 
	}
}
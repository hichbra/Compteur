package web ;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
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
	public static HashMap<String, Compteur> compteurs  ;

	
	public Controlleur() 
	{
		compteurs = new HashMap<String, Compteur>() ;
	}
	
    @OnMessage
    public void majCompteur(Session session, String msg, boolean last)
    {
        try 
        {
        	// Initialissation venant des cookies
        	compteurs = Compteur.cookiesToMap(msg);
    		
            while (session.isOpen())
            {
            	String cptsEnd = "["; // sous forme json
            	
            	int i = 0 ;
            	for( String k : compteurs.keySet() )
            	{
            		Compteur c = compteurs.get(k);

            		cptsEnd += "{\"end\":\"";

            		PeriodFormatter formatter = new PeriodFormatterBuilder()
            	        .appendYears().appendSuffix( " An ", " Ans ")
            	        .appendMonths().appendSuffix(" Mois ")
            	        .appendWeeks().appendSuffix(" Semaine ", " Semaines ")
            	        .appendDays().appendSuffix(" Jour ", " Jours ")
            	        .appendHours().appendSuffix(" Heure ", " Heures ")
            	        .appendMinutes().appendSuffix(" Minute ", " Minutes ")
            	        .appendSeconds().appendSuffix(" Seconde ", " Secondes ")
            	        //.printZeroNever()
            	        .toFormatter();

            		DateTime currentDate = DateTime.now();

            		try
            		{
            			Interval interval = new Interval(currentDate, new DateTime(c.getEnd()));
                		Period period = interval.toPeriod();
                	    cptsEnd += formatter.print(period)+"\"}";

            		}
            		catch(IllegalArgumentException e)
            		{
                	    cptsEnd += "Termin�e\"}";
            		}

            	    i++ ;
            	    if ( i != compteurs.size() )
            	    	cptsEnd += ",";
            	}
            	cptsEnd += "]";

            	session.getBasicRemote().sendText(cptsEnd);
            	
            	//System.out.print(compteurs.size()+" ");
            	//System.out.println( Integer.toHexString(System.identityHashCode(compteurs)));
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
            //	e1.printStackTrace();
            }
            // e.printStackTrace();
        } 
		catch (InterruptedException e) {
			try {
				session.close();
			} 
			catch (IOException e1) { // Ignore
			//	e1.printStackTrace();
			}
			// e.printStackTrace();
		}
    }
    
    @OnError
    public void onError(Session session, Throwable thr) {
    	try 
    	{
			session.close();
		} 
    	catch (IOException e)
    	{
			e.printStackTrace();
		}
    }

	/**
	 * Ajoute un compteur si il n'existe pas
	 * Modifie un compteur si il existe déjà
	 */
	@RequestMapping(value = "/addCompteur/{nom}/{locale}/{moisFin}/{jourFin}/{anneeFin}/{heureFin}/{minuteFin}/{secondeFin}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void addCompteur(HttpServletResponse response, @PathVariable("nom") String nom, @PathVariable("locale") String locale,	@PathVariable("moisFin") int moisFin,
																									@PathVariable("jourFin") int jourFin, 
																									@PathVariable("anneeFin") int anneeFin,
																									@PathVariable("heureFin") int heureFin,
																									@PathVariable("minuteFin") int minuteFin,
																									@PathVariable("secondeFin") int secondeFin)
	{
		//System.out.println(nom+" "+locale+" "+moisFin+" "+jourFin+" "+anneeFin+" "+heureFin+" "+minuteFin+" "+secondeFin);
		if ( nom != null && ! nom.equals("") && locale != null && ! locale.equals(""))
		{
			if ( moisFin <= 12 && moisFin > 0 && jourFin <= 31 && jourFin > 0 && anneeFin >= 1970 && heureFin < 24 && heureFin >= 0 && minuteFin < 60 && minuteFin >= 0 && secondeFin < 60 && secondeFin >= 0 )
			{
				// nb de jour coresspondant au mois
				if ( (moisFin == 2 && jourFin <= 29) || (jourFin == 31 && (moisFin == 1 || moisFin == 3 || moisFin == 5 || moisFin == 7 || moisFin == 8 || moisFin == 10 || moisFin == 12)) || jourFin < 31 )
				{
					// annee bissextile
					if ( ((moisFin == 2 && jourFin == 29) && (anneeFin%4 == 0 && anneeFin%100 == 0 && anneeFin%400 == 0)) || !(moisFin == 2 && jourFin == 29) )
					{
						Locale l = Compteur.stringToLocale(locale) ;

						Calendar dateFin = Calendar.getInstance(l);
						dateFin.set(Calendar.YEAR, anneeFin);
						dateFin.set(Calendar.MONTH, moisFin-1);
						dateFin.set(Calendar.DAY_OF_MONTH, jourFin);
						dateFin.set(Calendar.HOUR_OF_DAY, heureFin);
						dateFin.set(Calendar.MINUTE, minuteFin);
						dateFin.set(Calendar.SECOND, secondeFin);
							
						Compteur c = new Compteur(nom, l, dateFin);
						compteurs.put(nom, c);
						
						saveCookies(response);
					}
				}
			}
		}
		
	}
	
	/*
	 * Sauvegarde du cookie
	 */
	public void saveCookies(HttpServletResponse response)
	{
		String saveJson = "[" ;
		
		int i = 0 ;
		for(Compteur cpt : compteurs.values())
		{
			saveJson += cpt.getJson();
			if ( i < compteurs.size()-1 )
				saveJson += ",";
			
			i++;
		}
		saveJson +="]";
		
		Cookie cook = new Cookie("compteurs", saveJson);  
		cook.setPath("/");
		response.addCookie(cook);
	}
	
	private static String getCookieValue( HttpServletRequest request, String nom )
	{
        Cookie[] cookies = request.getCookies();

        if ( cookies != null ) 
            for ( Cookie cookie : cookies )
                if ( cookie != null && nom.equals( cookie.getName() ) ) 
                    return cookie.getValue();
                
        return null;
	}
	
	@RequestMapping(value = "/getCompteurs", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Object> getCompteurs(HttpServletRequest request)
	{
		if(compteurs.isEmpty())
		{
			String compteursString = getCookieValue( request, "compteurs" );
			if ( compteursString != null )
				compteurs = Compteur.cookiesToMap(compteursString); 
		}

		return Arrays.asList(compteurs.values().toArray()) ;
	}
	

	@RequestMapping(value = "/removeCompteur/{nom}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void removeCompteur(HttpServletResponse r, @PathVariable("nom") String nom)
	{
		compteurs.remove(nom);
		saveCookies(r);
	}
}
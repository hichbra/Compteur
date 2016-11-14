package tests;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Compteur;
import web.Controlleur;

//@ContextConfiguration(locations = {"classpath:/application-context.xml"})
@ContextConfiguration
({
	"file:src/main/webapp/WEB-INF/do-servlet.xml",
	"file:src/main/webapp/WEB-INF/web.xml",
})
@RunWith(SpringJUnit4ClassRunner.class)
public class Tests
{
	private Controlleur c ;
	private MockHttpServletRequest request;
    private MockHttpServletResponse response;
     
    @Before
    public void setUp()
    {
    	c = new Controlleur();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }
 
    @Test
    public void testInsertion()
    {
    	c.addCompteur(response, "a", "fr,", 11, 14, 2016, 00, 30, 00);

        assertEquals("Insertion correcte", 1, Controlleur.compteurs.size());
    }
    
    @Test
    public void testInsertionErreur()
    {
    	c.addCompteur(response, "", "fr,", 11, 14, 2016, 00, 30, 00);
    	
        assertEquals("Insertion non effectué", 0, Controlleur.compteurs.size());
    }
    
    @Test
    public void testGet()
    {
    	c.addCompteur(response, "a", "fr,", 11, 14, 2016, 00, 30, 00);
    	List<Object> list = c.getCompteurs(request);
    	assertEquals("Nombre Correcte", 1, list.size());
    	
    	Compteur cpt = (Compteur)list.get(0);
        assertEquals("Compteur correct", "a", cpt.getNom());
    }
    
    @Test
    public void testModification()
    {
    	c.addCompteur(response, "a", "fr,", 1, 14, 2016, 00, 30, 00);
    	List<Object> list = c.getCompteurs(request);
    	Compteur cpt = (Compteur)list.get(0);

    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Premiere insertion reussie", 0, cpt.getEnd().get(Calendar.MONTH));

    	c.addCompteur(response, "a", "fr,", 12, 14, 2016, 00, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Mois reussie", 11, cpt.getEnd().get(Calendar.MONTH));
    	
    	c.addCompteur(response, "a", "fr,", 12, 14, 2015, 00, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Annee reussie", 2015, cpt.getEnd().get(Calendar.YEAR));
    	
    	c.addCompteur(response, "a", "fr,", 12, 20, 2016, 00, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Jour reussie", 20, cpt.getEnd().get(Calendar.DAY_OF_MONTH));
    	
    	c.addCompteur(response, "a", "fr,", 12, 14, 2016, 13, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Heure reussie", 13, cpt.getEnd().get(Calendar.HOUR_OF_DAY));
    	
    	c.addCompteur(response, "a", "fr,", 12, 14, 2016, 12, 40, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Minute reussie", 40, cpt.getEnd().get(Calendar.MINUTE));
    	
    	c.addCompteur(response, "a", "fr,", 12, 14, 2016, 12, 30, 59);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Seconde reussie", 59, cpt.getEnd().get(Calendar.SECOND));
    }
    
    @Test
    public void testModificationErreur()
    {
    	c.addCompteur(response, "a", "fr,", 1, 14, 2016, 00, 30, 00);
    	List<Object> list = c.getCompteurs(request);
    	Compteur cpt = (Compteur)list.get(0);
    	
    	assertEquals("Insertion reussi", 1, list.size());

    	c.addCompteur(response, "a", "fr,", 13, 14, 2016, 00, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Mois 1 echec", 0, cpt.getEnd().get(Calendar.MONTH));
    	
    	c.addCompteur(response, "a", "fr,", 0, 14, 2016, 00, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Mois 2 echec", 0, cpt.getEnd().get(Calendar.MONTH));
    	
    	c.addCompteur(response, "a", "fr,", 12, 14, -5, 00, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Annee echec", 2016, cpt.getEnd().get(Calendar.YEAR));
    	
    	c.addCompteur(response, "a", "fr,", 1, 32, 2016, 00, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Jour 1 echec", 14, cpt.getEnd().get(Calendar.DAY_OF_MONTH));
    	
    	c.addCompteur(response, "a", "fr,", 1, 0, 2016, 00, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Jour 2 echec", 14, cpt.getEnd().get(Calendar.DAY_OF_MONTH));
    	
    	c.addCompteur(response, "a", "fr,", 11, 31, 2016, 00, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Jour 3 (Jour 31 du mois) echec", 14, cpt.getEnd().get(Calendar.DAY_OF_MONTH));
    	
    	c.addCompteur(response, "a", "fr,", 2, 29, 2016, 00, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Jour 4 (29 fevrier bissextile) echec", 14, cpt.getEnd().get(Calendar.DAY_OF_MONTH));
    	
    	c.addCompteur(response, "a", "fr,", 1, 14, 2016, -1, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Heure 1 echec", 00, cpt.getEnd().get(Calendar.HOUR_OF_DAY));
    	
    	c.addCompteur(response, "a", "fr,", 1, 14, 2016, 24, 30, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Heure 2 echec", 00, cpt.getEnd().get(Calendar.HOUR_OF_DAY));
    	
    	c.addCompteur(response, "a", "fr,", 1, 14, 2016, 00, -1, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Minute 1 echec", 30, cpt.getEnd().get(Calendar.MINUTE));
    	
    	c.addCompteur(response, "a", "fr,", 1, 14, 2016, 00, 60, 00);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Minute 2 echec", 30, cpt.getEnd().get(Calendar.MINUTE));
    	
    	c.addCompteur(response, "a", "fr,", 1, 14, 2016, 00, 30, -1);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Seconde 1 echec", 00, cpt.getEnd().get(Calendar.SECOND));
    	
    	c.addCompteur(response, "a", "fr,", 1, 14, 2016, 00, 30, 60);
    	list = c.getCompteurs(request);
    	cpt = (Compteur)list.get(0);
    	
    	assertEquals("Nombre Correcte", 1, list.size());
    	assertEquals("Modification Seconde 2 echec", 00, cpt.getEnd().get(Calendar.SECOND));
    	
    }
    
    @Test
    public void testSuppression()
    {
    	c.addCompteur(response, "a", "fr,", 1, 14, 2016, 00, 30, 00);

    	assertEquals("Insertion reussie", 1, Controlleur.compteurs.size());
    	
    	c.removeCompteur(response, "a");
    	
    	assertEquals("Suppression reussie", 0, Controlleur.compteurs.size());

    }
    
    @Test
    public void testSuppressionErreur()
    {
    	c.addCompteur(response, "a", "fr,", 1, 14, 2016, 00, 30, 00);

    	assertEquals("Insertion reussie", 1, Controlleur.compteurs.size());
    	
    	c.removeCompteur(response, "b");
    	
    	assertEquals("Suppression echec", 1, Controlleur.compteurs.size());

    }
}

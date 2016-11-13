package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    	//request.addParameter("username", "scott");
        //request.addParameter("password", "tiger");
    	c.addCompteur(response, "a", "fr,", 11, 14, 2016, 00, 30, 00);
    	
    	// c.getCompteurs(request);

        assertEquals("Insertion correcte", 1, Controlleur.compteurs.size());
  
    	//assertEquals("yep", "a", "a");
    }
}

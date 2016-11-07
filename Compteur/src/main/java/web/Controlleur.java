package web ;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ServerEndpoint("/echo")
@Controller
public class Controlleur implements Service
{
	/*
    @OnMessage
    public void echoTextMessage(Session session, String msg, boolean last)
    {
        try 
        {
            if (session.isOpen())
            {
                session.getBasicRemote().sendText(msg, last);
            }
        } 
        catch (IOException e)
        {
            try 
            {
                session.close();
            } 
            catch (IOException e1) 
            {
                // Ignore
            }
        }
    }
    */
	
	@OnMessage
    public void majCompteur(Session session, String msg, boolean last)
    {
		try 
        {
            if (session.isOpen())
            {
            	for ( int i = 0 ; i < 999999999 ; i++ )
        		{
                    session.getBasicRemote().sendText(i+"", last);
        		}
            }
        } 
        catch (IOException e)
        {
            try 
            {
                session.close();
            } 
            catch (IOException e1) 
            {
                // Ignore
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
	
	@RequestMapping(value = "/addCompteur/{nom}/{locale}/{fin}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void addCompteur(@PathVariable("nom") String nom, @PathVariable("locale") String locale, @PathVariable("fin") String fin) 
	{
		System.out.println("HEY "+nom+" "+locale+" "+fin);
	}

}
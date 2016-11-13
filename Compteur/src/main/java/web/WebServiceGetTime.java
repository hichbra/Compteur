package web;

import java.io.IOException;
import java.util.Calendar;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/getTime")
public class WebServiceGetTime
{	
	@OnMessage
    public void getTime(Session session, String msg, boolean last)
    {
		try 
        {
            while (session.isOpen())
            {
            	session.getBasicRemote().sendText(getCurrentTime());
            	
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
	
	public String getCurrentTime()
	{
		Calendar date = Calendar.getInstance();

		return date.get(Calendar.YEAR)
    			+" "+ String.format("%02d",date.get(Calendar.MONTH)+1)
    			+" "+ String.format("%02d",date.get(Calendar.DAY_OF_MONTH))
    			+" "+ String.format("%02d",date.get(Calendar.HOUR_OF_DAY))
    			+" "+ String.format("%02d",date.get(Calendar.MINUTE))
    			+" "+ String.format("%02d",date.get(Calendar.SECOND));
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
}

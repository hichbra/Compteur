package web;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface Service 
{
	@RequestMapping(value = "/test/{i}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void test(@PathVariable("i") int i);
	
	
	@RequestMapping(value = "/addCompteur/{nom}/{locale}/{fin}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void addCompteur(@PathVariable("nom") String nom, @PathVariable("locale") String locale, @PathVariable("fin") String fin);
}

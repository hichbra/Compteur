package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface Service 
{
	@RequestMapping(value = "/getCompteurs", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Object> getCompteurs(HttpServletRequest r);
	
	@RequestMapping(value = "/removeCompteur/{nom}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void removeCompteur(HttpServletResponse r, @PathVariable("nom") String nom);
	
	@RequestMapping(value = "/addCompteur/{nom}/{locale}/{moisFin}/{jourFin}/{anneeFin}/{heureFin}/{minuteFin}/{secondeFin}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void addCompteur(HttpServletResponse response, @PathVariable("nom") String nom, @PathVariable("locale") String locale,	@PathVariable("moisFin") int moisFin,
																									@PathVariable("jourFin") int jourFin, 
																									@PathVariable("anneeFin") int anneeFin,
																									@PathVariable("heureFin") int heureFin,
																									@PathVariable("minuteFin") int minuteFin,
																									@PathVariable("secondeFin") int secondeFin);
}

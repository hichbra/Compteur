package web;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import model.Compteur;

public interface Service 
{
	@RequestMapping(value = "/test/{i}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void test(@PathVariable("i") int i);
	
	
	@RequestMapping(value = "/addCompteur/{nom}/{locale}/{moisFin}/{jourFin}/{anneeFin}/{heureFin}/{minuteFin}/{secondeFin}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void addCompteur(@PathVariable("nom") String nom, @PathVariable("locale") String locale,	@PathVariable("moisFin") int moisFin,
																									@PathVariable("jourFin") int jourFin, 
																									@PathVariable("anneeFin") int anneeFin,
																									@PathVariable("heureFin") int heureFin,
																									@PathVariable("minuteFin") int minuteFin,
																									@PathVariable("secondeFin") int secondeFin);


	@RequestMapping(value = "/getCompteurs", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Compteur> getCompteurs();
}

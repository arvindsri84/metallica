package com.metaltraders.metallica.refdataservice.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metaltraders.metallica.refdataservice.collections.Commodity;
import com.metaltraders.metallica.refdataservice.collections.Counterparty;
import com.metaltraders.metallica.refdataservice.collections.Location;
import com.metaltraders.metallica.refdataservice.repository.CommodityRepository;
import com.metaltraders.metallica.refdataservice.repository.CounterpartyRepository;
import com.metaltraders.metallica.refdataservice.repository.LocationRespository;

@RestController
@RequestMapping("/api/ref/")
public class ReferenceResource {
	
	@Autowired
	private CommodityRepository comodityRespository;

	@Autowired
	private LocationRespository locationRespository;

	@Autowired
	private CounterpartyRepository counterpartyRespository;

	
	@RequestMapping(path = "commodity/{code}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Commodity>> getCommoditiesByCode(@PathVariable(value = "code") String code) {
		List<Commodity> comodities = comodityRespository.findByCode(code);
		return new ResponseEntity<List<Commodity>>(comodities,HttpStatus.OK);
	}
	
	
	@RequestMapping(path = "commodity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Commodity>> getCommoditiesByDescritpion(@RequestParam(value = "description",required = false) String description) {
		
		List<Commodity> comodities = new ArrayList<Commodity>();
		if(isNotBlank(description)){
			comodities.addAll(comodityRespository.findByDescriptionRegex(description));
			
		}else{
			comodities.addAll(comodityRespository.findAll());
		}
		return new ResponseEntity<List<Commodity>>(comodities,HttpStatus.OK);
	}
	

	@RequestMapping(path = "location/{code}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Location>> getLocationsByCode(@PathVariable(value = "code") String code) {
		List<Location> locations = locationRespository.findByCode(code);
		return new ResponseEntity<List<Location>>(locations,HttpStatus.OK);
	}
	
	
	@RequestMapping(path = "location", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Location>> getLocationsByDescritpion(@RequestParam(value = "description",required = false) String description) {
		
		List<Location> location = new ArrayList<Location>();
		if(isNotBlank(description)){
			location.addAll(locationRespository.findByDescriptionRegex(description));
			
		}else{
			location.addAll(locationRespository.findAll());
		}
		return new ResponseEntity<List<Location>>(location,HttpStatus.OK);
	}

	@RequestMapping(path = "counterparty/{code}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Counterparty>> getCounterpartyByCode(@PathVariable(value = "code") String code) {
		List<Counterparty> counterparties = counterpartyRespository.findByCode(code);
		return new ResponseEntity<List<Counterparty>>(counterparties,HttpStatus.OK);
	}
	
	
	@RequestMapping(path = "counterparty", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Counterparty>> getCounterpartiesByDescritpion(@RequestParam(value = "description",required = false) String description) {
		
		List<Counterparty> counterparties = new ArrayList<Counterparty>();
		if(isNotBlank(description)){
			counterparties.addAll(counterpartyRespository.findByDescriptionRegex(description));
			
		}else{
			counterparties.addAll(counterpartyRespository.findAll());
		}
		return new ResponseEntity<List<Counterparty>>(counterparties,HttpStatus.OK);
	}

	
	private boolean isNotBlank(String str){
		return str != null && str.trim().length() > 0;
	}
}

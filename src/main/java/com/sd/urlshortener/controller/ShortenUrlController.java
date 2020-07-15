package com.sd.urlshortener.controller;

import com.sd.urlshortener.entity.Site;
import com.sd.urlshortener.service.ShortenUrlService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("")
public class ShortenUrlController {

	@Autowired
	private ShortenUrlService service;


	@GetMapping("/fetch")
	public Site fetchShortUrl(@RequestParam("url") String url)
	{
		return service.getShortUrl(url);
	}

	@GetMapping("/tu/{id}")
	public ResponseEntity<String> redirectToUrl(@PathVariable(value = "id") String id){
				HttpHeaders headers = new HttpHeaders();
		headers.add("Location",service.getOriginalUrl(id));
		return new ResponseEntity<String>(headers, HttpStatus.FOUND);
	}

}

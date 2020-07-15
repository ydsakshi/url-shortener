package com.sd.urlshortener.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.sd.urlshortener.dao.ShortenUrlRepository;
import com.sd.urlshortener.entity.Site;

@Service
public class ShortenUrlService {

	@Autowired
	private ShortenUrlRepository repository;

	public String getOriginalUrl(String shorturl){
		return repository.findSiteByShortUrl(shorturl);
	}

	public Site getShortUrl(String url) {
		
		if(repository.findById(url).isPresent()) {
			System.out.println("~~~~~~~~~Entered here~~~~~~~~~");
			return repository.findById(url).get();
			}
		Site site=new Site();
		site.setOriginalUrl(url);
		site.setShortUrl(getShortForm(new Date().getTime()));
		return repository.save(site);
	}

	private String getShortForm(long value) {
		List<Integer> list=new ArrayList<Integer>();
		while(value>0) {
			list.add((int) (value % 64));
			value=value/64;
		}
		Collections.reverse(list);
		return afterMapping(list);
	}
	
	private String afterMapping(List<Integer> list) {
		String mapping="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_";
		String value="";
		for(int i:list) {
			value+=mapping.charAt(i);
		}
		return value;
	}

}

package com.sd.urlshortener.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sd.urlshortener.entity.Site;

@Repository 
public interface ShortenUrlRepository extends JpaRepository<Site,String> {

    @Query("SELECT u.originalUrl FROM Site u WHERE u.shortUrl = ?1")
    public String findSiteByShortUrl(String shortUrl);

}

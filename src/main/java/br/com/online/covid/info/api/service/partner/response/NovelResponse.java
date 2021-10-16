package br.com.online.covid.info.api.service.partner.response;

import lombok.Data;

@Data
public class NovelResponse {

    private String updated;
    private String country;
    private String cases;
    private String todayCases;
    private String deaths;
    private String todayDeaths;
    private String recovered;
    private String todayRecovered;
    private String active;
    private String critical;
    private String casesPerOneMillion;
    private String deathsPerOneMillion;
    private String tests;
    private String testsPerOneMillion;
    private String population;
    private String oneCasePerPeople;
    private String oneDeathPerPeople;
    private String oneTestPerPeople;
    private String activePerOneMillion;
    private String recoveredPerOneMillion;
    private String criticalPerOneMillion;
    private String affectedCountries;

}
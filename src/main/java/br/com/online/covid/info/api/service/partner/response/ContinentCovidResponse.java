package br.com.online.covid.info.api.service.partner.response;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContinentCovidResponse {

    private BigInteger	updated;
    private Integer cases;
    private Integer todayCases;
    private Integer deaths;
    private Integer todayDeaths;
    private Integer recovered;
    private Integer todayRecovered;
    private Integer active;
    private Integer critical;
    private Integer casesPerOneMillion;
    private Integer deathsPerOneMillion;
    private Integer tests;
    private Integer testsPerOneMillion;
    private Integer population;
    private String  continent;
    private Integer activePerOneMillion;
    private Integer recoveredPerOneMillion;
    private Integer criticalPerOneMillion;
    
}

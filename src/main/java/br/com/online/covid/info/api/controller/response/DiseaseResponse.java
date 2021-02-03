package br.com.online.covid.info.api.controller.response;

import java.time.LocalDateTime;

public class DiseaseResponse {

    private LocalDateTime date;
    private String country;
    private String cases;
    private String deaths;
    private String peopleRecovered;
    private String totalPopulation;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getPeopleRecovered() {
        return peopleRecovered;
    }

    public void setPeopleRecovered(String peopleRecovered) {
        this.peopleRecovered = peopleRecovered;
    }

    public String getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(String totalPopulation) {
        this.totalPopulation = totalPopulation;
    }
}

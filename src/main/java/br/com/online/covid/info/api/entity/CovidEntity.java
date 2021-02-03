package br.com.online.covid.info.api.entity;

import java.time.LocalDateTime;

public class CovidEntity {

    private String id;
    private LocalDateTime date;
    private String country;
    private Integer cases;
    private Integer death;
    private Integer recovered;
    private Long population;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "CovidEntity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", country='" + country + '\'' +
                ", cases=" + cases +
                ", death=" + death +
                ", recovered=" + recovered +
                ", population=" + population +
                '}';
    }
}

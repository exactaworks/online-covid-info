package br.com.online.covid.info.api.mapper;

import br.com.online.covid.info.api.controller.response.DiseaseResponse;
import br.com.online.covid.info.api.entity.CovidEntity;
import br.com.online.covid.info.api.service.partner.response.NovelResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DiseaseMapper {

    public CovidEntity toEntity(NovelResponse novelResponse) {
        CovidEntity covidEntity = new CovidEntity();
        covidEntity.setCases(Integer.parseInt(novelResponse.getCases()));
        covidEntity.setCountry("world");
        covidEntity.setDate(LocalDateTime.now());
        covidEntity.setDeath(Integer.parseInt(novelResponse.getCases()));
        covidEntity.setPopulation(Long.parseLong(novelResponse.getPopulation()));
        covidEntity.setRecovered(Integer.parseInt(novelResponse.getRecovered()));
        return covidEntity;
    }

    public DiseaseResponse toResponse(CovidEntity covidEntity) {
        DiseaseResponse response = new DiseaseResponse();
        response.setCases(String.valueOf(covidEntity.getCases()));
        response.setCountry(covidEntity.getCountry());
        response.setDate(covidEntity.getDate());
        response.setDeaths(String.valueOf(covidEntity.getDeath()));
        response.setPeopleRecovered(String.valueOf(covidEntity.getRecovered()));
        response.setTotalPopulation(String.valueOf(covidEntity.getPopulation()));
        return response;
    }

}

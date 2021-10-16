package br.com.online.covid.info.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.online.covid.info.api.controller.response.DiseaseResponse;
import br.com.online.covid.info.api.entity.ContinentCovidEntity;
import br.com.online.covid.info.api.entity.CovidEntity;
import br.com.online.covid.info.api.entity.dto.CovidEntityDTO;
import br.com.online.covid.info.api.mapper.DiseaseMapper;
import br.com.online.covid.info.api.repository.ContinentCovidEntityRepository;
import br.com.online.covid.info.api.repository.DiseaseRepository;
import br.com.online.covid.info.api.service.partner.NovelCovidApi;
import br.com.online.covid.info.api.service.partner.response.ContinentCovidResponse;
import br.com.online.covid.info.api.service.partner.response.NovelResponse;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class DiseaseService {

    private final NovelCovidApi novelCovidApi;

    private final DiseaseMapper mapper;

    private final DiseaseRepository diseaseRepository;

    private Random random = new Random(); 

    @Autowired
    ContinentCovidEntityRepository continentCovidEntityRepository;

    @Autowired
    ModelMapper modelMapper;

    public DiseaseService(NovelCovidApi novelCovidApi, DiseaseRepository diseaseRepository, DiseaseMapper diseaseMapper) {
        this.novelCovidApi = novelCovidApi;
        this.diseaseRepository = diseaseRepository;
        this.mapper = diseaseMapper;
    }

    public Optional<DiseaseResponse> findWorldDisease(Optional<String> country) {
        return novelCovidApi.findWorldDisease(country)
                .map(mapper::toEntity)
                .flatMap(this::saveIfNotRepeated)
                .map(mapper::toResponse);
    }

    public Optional<DiseaseResponse> findWorldDisease() {
        return this.findWorldDisease(Optional.empty());
    }

    @Transactional
    public List<CovidEntity> deleteByCountry(String country){
        try {
            return diseaseRepository.deleteByCountry(country);
        } catch(Exception e) {
            log.error(String.format("delete method error: %s", e.getLocalizedMessage()));
        }

        return new ArrayList<>();
    }

    public Optional<CovidEntity> saveIfNotRepeated(CovidEntity entity) {

        entity.setId(UUID.randomUUID().toString());

        try {
            List<CovidEntity> listCovidEntity = diseaseRepository
                    .findByDateAndCountry(entity.getDate().toLocalDate(),
                                          entity.getCountry());

            if(listCovidEntity.isEmpty()){
                diseaseRepository.save(entity);
            }
            return Optional.of(entity);
        }catch(Exception e) {
            log.error(String.format("saveIfNotRepeated method error: %s", e.getLocalizedMessage()));
        }

        return Optional.empty();
    }
    
    public Page<CovidEntity> findAll(Pageable pageable) {
        return diseaseRepository.findAll(pageable);
    }

    @Transactional
    public Optional<CovidEntity> postAddPartner(CovidEntityDTO covidEntityDTO) {
        
        try {

            Optional<NovelResponse> optionalNovelResponse = novelCovidApi
                .findWorldDisease(Optional.of(covidEntityDTO.getCountry().toLowerCase()));


            if(optionalNovelResponse.isPresent()){

                Optional<CovidEntity> optionalPartnerCovidEntity = optionalNovelResponse.map(mapper::toEntity);

                if(optionalPartnerCovidEntity.isEmpty()){
                    return Optional.empty();
                }

                CovidEntity partnerCovidEntity = optionalPartnerCovidEntity.get();
                
                diseaseRepository
                    .deleteByDateAndCountry(partnerCovidEntity.getDate().toLocalDate(), 
                                            partnerCovidEntity.getCountry());
                
                partnerCovidEntity = addCovidEntityDTOWithPartnerCovidEntity(covidEntityDTO, partnerCovidEntity);

                return Optional.of(diseaseRepository.save(partnerCovidEntity));
            } else {
                CovidEntity persistentCovidEntity = modelMapper.map(covidEntityDTO, CovidEntity.class);

                persistentCovidEntity.setId(UUID.randomUUID().toString());
                persistentCovidEntity.setDate(LocalDateTime.now());
                return Optional.of(diseaseRepository.save(persistentCovidEntity));
            }
        
        } catch(Exception e) {
            log.error(String.format("postAddPartner method error: %s", e.getLocalizedMessage()));
        }

        return Optional.empty();
    }

    private CovidEntity addCovidEntityDTOWithPartnerCovidEntity(CovidEntityDTO covidEntityDTO, CovidEntity partnerCovidEntity) {
        partnerCovidEntity.setId(UUID.randomUUID().toString());
        partnerCovidEntity.setCases(partnerCovidEntity.getCases() + covidEntityDTO.getCases());
        partnerCovidEntity.setDeath(partnerCovidEntity.getDeath() + covidEntityDTO.getDeath());
        partnerCovidEntity.setPopulation(partnerCovidEntity.getPopulation() + covidEntityDTO.getPopulation());
        partnerCovidEntity.setRecovered(partnerCovidEntity.getRecovered() + covidEntityDTO.getRecovered());

        return partnerCovidEntity;
    }

    public ContinentCovidEntity findContinentDisease(String continent) {

        Optional<ContinentCovidResponse> continentCovidResponse = novelCovidApi.findContinentDisease(continent);

        ContinentCovidEntity continentCovidEntity = new ContinentCovidEntity();

        if(continentCovidResponse.isPresent()){
            continentCovidEntity = modelMapper.map(continentCovidResponse.get(), ContinentCovidEntity.class);
        } else {
            continentCovidEntity.setContinent(continent);
            continentCovidEntity.setCases(random.nextInt(100000));
            continentCovidEntity.setDeaths(random.nextInt(10000));
            continentCovidEntity.setPopulation(random.nextInt(1000000));
            continentCovidEntity.setRecovered(random.nextInt(1000000));
        }


        continentCovidEntity.setId(UUID.randomUUID().toString());
        continentCovidEntity.setDate(LocalDateTime.now());

        return continentCovidEntityRepository.save(continentCovidEntity);
    }

}

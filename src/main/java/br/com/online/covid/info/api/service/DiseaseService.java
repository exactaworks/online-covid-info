package br.com.online.covid.info.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.online.covid.info.api.controller.response.DiseaseResponse;
import br.com.online.covid.info.api.entity.CovidEntity;
import br.com.online.covid.info.api.mapper.DiseaseMapper;
import br.com.online.covid.info.api.repository.DiseaseRepository;
import br.com.online.covid.info.api.service.partner.NovelCovidApi;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class DiseaseService {

    private final NovelCovidApi novelCovidApi;

    private final DiseaseMapper mapper;

    private final DiseaseRepository diseaseRepository;

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
            List<CovidEntity> listCovidEntity = 
                diseaseRepository
                    .findByDateAndCountry(entity.getDate().toLocalDate(),
                                          entity.getCountry());

            if(listCovidEntity.isEmpty()){
                diseaseRepository.save(entity);
            }
            return Optional.of(entity);
        }catch(Exception e) {
            log.error(String.format("save method error: %s", e.getLocalizedMessage()));
        }

        return Optional.empty();
    }
    
    public Page<CovidEntity> findAll(Pageable pageable) {
        return diseaseRepository.findAll(pageable);
    }


}

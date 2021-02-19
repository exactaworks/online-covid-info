package br.com.online.covid.info.api.service;

import br.com.online.covid.info.api.controller.response.DiseaseResponse;
import br.com.online.covid.info.api.mapper.DiseaseMapper;
import br.com.online.covid.info.api.repository.DiseaseRepository;
import br.com.online.covid.info.api.service.partner.NovelCovidApi;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DiseaseService {

    private final NovelCovidApi novelCovidApi;

    private final DiseaseMapper mapper;

    private final DiseaseRepository diseaseRepository;

    public DiseaseService(NovelCovidApi novelCovidApi, DiseaseRepository diseaseRepository, DiseaseMapper diseaseMapper) {
        this.novelCovidApi = novelCovidApi;
        this.diseaseRepository = diseaseRepository;
        this.mapper = diseaseMapper;
    }

    public Optional<DiseaseResponse> findWorldDisease() {
        return novelCovidApi.findWorldDisease()
                .map(mapper::toEntity)
                .flatMap(diseaseRepository::save)
                .map(mapper::toResponse);
    }

}

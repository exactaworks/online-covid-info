package br.com.online.covid.info.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.online.covid.info.api.controller.response.DiseaseResponse;
import br.com.online.covid.info.api.entity.CovidEntity;
import br.com.online.covid.info.api.repository.DiseaseRepository;
import br.com.online.covid.info.api.service.DiseaseService;

@RestController
@RequestMapping(value = "/disease")
public class DiseaseController {

    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping
    public ResponseEntity<DiseaseResponse> findWorldDisease(Optional<String> country) {

        Optional<DiseaseResponse> optionalDiseaseResponse = diseaseService.findWorldDisease(country);

        if (optionalDiseaseResponse.isPresent()){
            return new ResponseEntity<>(optionalDiseaseResponse.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping
    public ResponseEntity<List<CovidEntity>> deleteByCountry(@RequestParam String country) {
        return new ResponseEntity<>(diseaseService.deleteByCountry(country),
                                    HttpStatus.OK);

    }

    @GetMapping("/find_all")
    public ResponseEntity<Page<CovidEntity>> covidFindAll(
        @RequestParam Integer page, @RequestParam Integer size) {

        Pageable pageable = PageRequest.of(page, size);

        return new ResponseEntity<>(diseaseService.findAll(pageable),
                                    HttpStatus.OK);
    }

}

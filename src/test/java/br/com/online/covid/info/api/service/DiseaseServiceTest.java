package br.com.online.covid.info.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.jdbi.v3.core.Jdbi;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.online.covid.info.api.controller.response.DiseaseResponse;
import br.com.online.covid.info.api.mapper.DiseaseMapper;
import br.com.online.covid.info.api.repository.DiseaseRepository;
import br.com.online.covid.info.api.service.partner.NovelCovidApi;
import br.com.online.covid.info.api.service.partner.response.NovelResponse;

@RunWith(MockitoJUnitRunner.class)
public class DiseaseServiceTest {

    @Mock
    private NovelCovidApi novelCovidApi;

    @Mock
    private Jdbi jdbi;

    @Mock
    private DiseaseRepository diseaseRepository;

    @InjectMocks
    private DiseaseService diseaseService;

    @Before
    public void init() {
        diseaseService = new DiseaseService(novelCovidApi, diseaseRepository, new DiseaseMapper());
    }

    @Test
    public void shouldAccessClientAndSaveAndReturnSuccess() {
        when(novelCovidApi.findWorldDisease(Optional.empty())).thenReturn(Optional.of(novelResponse()));

        Optional<DiseaseResponse> diseaseResponse = diseaseService.findWorldDisease();

        assertNotNull(diseaseService.findWorldDisease());
        assertTrue(diseaseService.findWorldDisease().isPresent());
        assertEquals("21", diseaseResponse.get().getCases());
    }

    private NovelResponse novelResponse() {
        NovelResponse response = new NovelResponse();
        response.setCases("21");
        response.setDeaths("66");
        response.setPopulation("500");
        response.setRecovered("12");
        return response;
    }

}

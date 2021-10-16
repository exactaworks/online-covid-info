package br.com.online.covid.info.api.service.partner;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.online.covid.info.api.service.partner.response.ContinentCovidResponse;
import br.com.online.covid.info.api.service.partner.response.NovelResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Slf4j
@Service
public class NovelCovidApi extends NovelCovidGenerics {

    public NovelCovidApi(OkHttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    public Optional<NovelResponse> findWorldDisease(Optional<String> country) {

        String url;

        if(country.isPresent()){
            url = String.format("%s/%s/%s", host, countries, country.get());
        } else {
            url = String.format("%s/%s", host, findAll);
        }

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {

            NovelResponse novelResponse = mapper.readValue(Objects.requireNonNull(response.body()).string(), NovelResponse.class);


            if(novelResponse.getPopulation() == null) {
                return Optional.empty();
            }

            return Optional.of(novelResponse);

        } catch (Exception e) {
            log.error(String.format("findWorldDisease method error: %s", e.getLocalizedMessage()));
        }

        return Optional.empty();
    }

    public Optional<ContinentCovidResponse> findContinentDisease(String continent) {

        try{

            String url = String.format("https://disease.sh/v3/covid-19/continents/%s", continent);

            RestTemplate restTemplate = new RestTemplate();

            return Optional.of(restTemplate.getForObject(url, ContinentCovidResponse.class));
        } catch (Exception e) {
            log.error(String.format("findWorldDisease method error: %s", e.getLocalizedMessage()));
        }


        return Optional.empty();
    }

}

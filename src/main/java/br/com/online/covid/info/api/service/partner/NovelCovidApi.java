package br.com.online.covid.info.api.service.partner;

import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

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

    public Optional<NovelResponse> findWorldDisease() {

        Request request = new Request.Builder()
                .url(String.format("%s/%s", host, findAll))
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Optional.of(mapper.readValue(Objects.requireNonNull(response.body()).string(), NovelResponse.class));
        } catch (Exception e) {
            log.error(String.format("findWorldDisease method error: %s", e.getLocalizedMessage()));
        }

        return Optional.empty();
    }

}

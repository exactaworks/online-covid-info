package br.com.online.covid.info.api.service.partner;

import br.com.online.covid.info.api.service.partner.response.NovelResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class NovelCovidApi extends NovelCovidGenerics {

    private static final Logger LOG = getLogger(NovelCovidApi.class);

    public NovelCovidApi(OkHttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    public Optional<NovelResponse> findWorldDisease() {
        LOG.info("== Accessing partner ==");

        Request request = new Request.Builder()
                .url(String.format("%s/%s", host, findAll))
                .build();

        try (Response response = client.newCall(request).execute()) {
            return Optional.of(mapper.readValue(Objects.requireNonNull(response.body()).string(), NovelResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


}

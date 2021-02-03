package br.com.online.covid.info.api.service.partner;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NovelCovidGenerics {

    protected final OkHttpClient client;
    protected final ObjectMapper mapper;

    public NovelCovidGenerics(OkHttpClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    @Value("${partner.host}")
    protected String host;

    @Value("${partner.endpoints.all}")
    protected String findAll;

}

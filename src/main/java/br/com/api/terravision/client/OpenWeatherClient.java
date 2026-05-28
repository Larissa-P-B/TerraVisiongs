package br.com.api.terravision.client;

import br.com.api.terravision.dto.ClimaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.url}")
    private String apiUrl;

    public ClimaResponse buscarClima(String cidade) {

        String url =
                apiUrl
                        + "?q=" + cidade
                        + "&appid=" + apiKey
                        + "&units=metric"
                        + "&lang=pt_br";

        return restTemplate.getForObject(url, ClimaResponse.class);
    }
}
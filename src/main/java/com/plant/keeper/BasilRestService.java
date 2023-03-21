package com.plant.keeper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Component
@EnableScheduling
@EnableTransactionManagement
public class BasilRestService {

    private final RestTemplate restTemplate;
    private final String url = "http://192.168.0.123/getData";
    DateTimeFormatter formatter;
    BasilRepository basilRepository;

    @Autowired
    public BasilRestService(BasilRepository basilRepository) {
        restTemplate = new RestTemplateBuilder().build();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.basilRepository = basilRepository;
    }

    @Scheduled(fixedRate = 600000)
    @Transactional()
    public void insertActualBasilInformationToTable() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String json = restTemplate.getForObject(url, String.class);
        JsonObject data = new Gson().fromJson(json, JsonObject.class).get("data").getAsJsonObject();
        Double test = data.get("temperature").getAsDouble();
        System.out.println(test);
        Basil basil = new Basil(
                data.get("soilMoisture").getAsInt(),
                data.get("temperature").getAsDouble(),
                LocalDateTime.parse(data.get("date").getAsString(), formatter)
        );
        basilRepository.save(basil);
    }
}
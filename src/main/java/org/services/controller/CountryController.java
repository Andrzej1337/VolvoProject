package org.services.controller;

import org.services.api.model.CountryEntity;
import org.services.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @Value("${country-api.url}")
    private String restCountriesBaseUrl;
    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(CountryController.class);

    @GetMapping("/{alpha3Code}")
    public CountryEntity getCountry(@PathVariable String alpha3Code) {
        CountryEntity country = countryRepository.findByAlpha3Code(alpha3Code);

        if (country == null) {
            try {

                String url = restCountriesBaseUrl + alpha3Code;
                List<Map<String, Object>> responseList = restTemplate.getForObject(url, List.class);

                if (responseList == null || responseList.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
                }

                Map<String, Object> response = responseList.get(0);

                country = new CountryEntity();
                country.setAlpha3Code(alpha3Code);

                Map<String, Object> nameMap = (Map<String, Object>) response.get("name");
                country.setCommonName((String) nameMap.get("common"));
                country.setOfficialName((String) nameMap.get("official"));

                Map<String, Map<String, String>> currenciesMap = (Map<String, Map<String, String>>) response.get("currencies");
                if (currenciesMap != null) {
                    country.setCurrencies(String.join(", ", currenciesMap.keySet()));
                }

                List<String> capitalList = (List<String>) response.get("capital");
                if (capitalList != null) {
                    country.setCapital(String.join(", ", capitalList));
                }

                country.setRegion((String) response.get("region"));
                country.setSubregion((String) response.get("subregion"));

                Map<String, String> languagesMap = (Map<String, String>) response.get("languages");
                if (languagesMap != null) {
                    country.setLanguages(languagesMap);
                }

                country.setPopulation(((Number) response.get("population")).longValue());

                List<String> bordersList = (List<String>) response.get("borders");
                if (bordersList != null) {
                    country.setBorders(String.join(", ", bordersList));
                }

                List<String> timezonesList = Arrays.asList("UTC", "GMT", "PST");
                country.setTimeZones(timezonesList);

                countryRepository.save(country);
            }
             catch (HttpClientErrorException.NotFound e) {
                 logger.error("Not found for"+ alpha3Code + "stacktrace" +e.getMessage());
                return null;
            } catch (HttpClientErrorException e) {
                logger.error("Not found for"+ alpha3Code + "stacktrace" +e.getMessage());
                return null;
            }
        }
        return country;
    }
}

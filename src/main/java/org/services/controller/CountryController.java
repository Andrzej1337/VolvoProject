package org.services.controller;

import org.services.api.model.CountryEntity;
import org.services.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{alpha3Code}")
    public CountryEntity getCountry(@PathVariable String alpha3Code) {
        CountryEntity country = countryRepository.findByAlpha3Code(alpha3Code);
        if (country == null) {
            String url = "https://restcountries.com/v3.1/alpha/" + alpha3Code;
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
                country.setLanguages(String.join(", ", languagesMap.values()));
            }

            country.setPopulation(((Number) response.get("population")).longValue());

            List<String> bordersList = (List<String>) response.get("borders");
            if (bordersList != null) {
                country.setBorders(String.join(", ", bordersList));
            }

            List<String> timezonesList = (List<String>) response.get("timezones");
            if (timezonesList != null) {
                country.setTimeZones(String.join(", ", timezonesList));
            }

            countryRepository.save(country);
        }
        return country;
    }
}

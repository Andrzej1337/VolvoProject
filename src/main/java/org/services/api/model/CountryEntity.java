package org.services.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class CountryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alpha3Code;
    private String commonName;
    private String officialName;
    private String currencies;
    private String capital;
    private String region;
    private String subregion;
    private String languages;
    private Long population;
    private String borders;
    private String timeZones;


    public void setId(Long id) {
        this.id = id;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public void setTimeZones(String timeZones) {
        this.timeZones = timeZones;
    }

    public Long getId() {
        return id;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getOfficialName() {
        return officialName;
    }

    public String getCurrencies() {
        return currencies;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public String getLanguages() {
        return languages;
    }

    public Long getPopulation() {
        return population;
    }

    public String getBorders() {
        return borders;
    }

    public String getTimeZones() {
        return timeZones;
    }


}
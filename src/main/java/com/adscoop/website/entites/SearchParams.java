package com.adscoop.website.entites;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class SearchParams {


    @Getter
    @Setter
    private String zip;
    @Getter
    @Setter
    private String country;
    @Getter
    @Setter
    private String region;
    @Getter
    @Setter
    private String type;
    @Getter
    @Setter
    private String category;
    @Getter
    @Setter
    private String visitors;
    @Getter
    @Setter
    private String gender;
    @Getter
    @Setter
    private int mixAge;
    @Getter
    @Setter
    private int maxAge;
    @Getter
    @Setter
    private boolean physicalShop;



}

package com.adscoop.website.entites;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerSpace extends AbstractEntity {

    private String position;
    private Double price;
    private int width;
    private int height;

    @Builder.Default
    private List<String> categories = new ArrayList<>();

    @Builder.Default
    private List<String> targetgroups = new ArrayList<>();

}

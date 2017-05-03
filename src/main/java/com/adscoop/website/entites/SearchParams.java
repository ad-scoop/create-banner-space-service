package com.adscoop.website.entites;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class SearchParams {


    @Getter
    @Setter
    @Builder.Default
    List<String> names = newArrayList();




}

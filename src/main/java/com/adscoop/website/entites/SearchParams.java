package com.adscoop.website.entites;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by thokle on 29/04/2017.
 */
@Builder
public class SearchParams {


    @Getter
    @Setter
    String name;




}

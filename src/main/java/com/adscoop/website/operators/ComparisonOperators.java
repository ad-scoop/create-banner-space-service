package com.adscoop.website.operators;

import org.neo4j.ogm.cypher.NoOpPropertyValueTransformer;
import org.neo4j.ogm.cypher.PropertyValueTransformer;

/**
 * Created by thokle on 06/05/2017.
 */
public enum ComparisonOperators {

    CONTAINS("contains");

    private final String value;
    private final PropertyValueTransformer valueTransformer;

    ComparisonOperators(String value) {
        this(value, new NoOpPropertyValueTransformer());
    }

    ComparisonOperators(String value, PropertyValueTransformer propertyValueTransformer) {
        this.value = value;
        this.valueTransformer = propertyValueTransformer;
    }

}

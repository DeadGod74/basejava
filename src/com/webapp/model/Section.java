package com.webapp.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextSection.class, name = "text"),
        @JsonSubTypes.Type(value = CompanySection.class, name = "org")
})

public abstract class Section {
    public abstract List<Company> getContent();
}

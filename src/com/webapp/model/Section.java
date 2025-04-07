package com.webapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextSection.class, name = "text"),
        @JsonSubTypes.Type(value = CompanySection.class, name = "org")
})

public abstract class Section implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public abstract List<Company> getContent();
}

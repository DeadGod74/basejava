package com.webapp.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SectionText extends Section{
    private String text;

    public SectionText() {}

    public SectionText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SectionText)) return false;
        SectionText section = (SectionText) o;
        return Objects.equals(text, section.text);
    }


    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public List<Organization> getContent() {
        return Collections.emptyList();
    }
}
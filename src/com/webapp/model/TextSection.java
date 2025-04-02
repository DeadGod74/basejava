package com.webapp.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TextSection extends Section{
    private String text;

    public TextSection() {}

    public TextSection(String text) {
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
        if (!(o instanceof TextSection)) return false;
        TextSection section = (TextSection) o;
        return Objects.equals(text, section.text);
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public List<Company> getContent() {
        return Collections.emptyList();
    }
}
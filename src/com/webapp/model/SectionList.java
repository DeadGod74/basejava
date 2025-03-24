package com.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SectionList extends Section{
    private final List<String> list;

    public SectionList(String... list) {
        this(Arrays.asList(list));
    }

    public SectionList(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SectionList)) return false;
        SectionList section = (SectionList) o;
        return Objects.equals(list, section.list);
    }


    @Override
    public int hashCode() {
        return list != null ? list.hashCode() : 0;
    }

}

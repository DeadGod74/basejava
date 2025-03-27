package com.webapp.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private Link homePage;
    private List<Position> positions = new ArrayList<>();

    public Organization() {
        this.homePage = null;
        this.positions = new ArrayList<>();
    }

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), List.of(positions));
    }

    public Organization(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = List.copyOf(positions);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) && Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(homePage);
        result = 31 * result + Objects.hashCode(positions);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", positions=" + positions +
                '}';
    }

    public Iterable<? extends Position> getPositions() {
        return positions;
    }

    public Link getHomePage() {
        return homePage;
    }
}

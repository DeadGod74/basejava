package com.webapp.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private Link homePage;
    private List<Position> positions = new ArrayList<>();

    // Конструктор без параметров для Jackson
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

    // Вложенный класс Position
    public static class Position {
        private LocalDate startDate;
        private LocalDate endDate;
        private String title;
        private String description;

        // Конструктор без параметров для Jackson
        public Position() {
            this.startDate = LocalDate.now(); // или другое значение по умолчанию
            this.endDate = LocalDate.now(); // или другое значение по умолчанию
            this.title = "";
            this.description = "";
        }

        public Position(int startYear, Month startMonth, String title, String description) {
            this(LocalDate.of(startYear, startMonth.getValue(), 1), LocalDate.now(), title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(LocalDate.of(startYear, startMonth.getValue(), 1), LocalDate.of(endYear, endMonth.getValue(), 1), title, description);
        }

        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            if (endDate.isBefore(startDate)) {
                throw new IllegalArgumentException("endDate must not be before startDate");
            }
            this.startDate = Objects.requireNonNull(startDate, "startDate must not be null");
            this.endDate = Objects.requireNonNull(endDate, "endDate must not be null");
            this.title = Objects.requireNonNull(title, "title must not be null");
            this.description = Objects.requireNonNull(description, "description must not be null");
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            Position position = (Position) o;
            return Objects.equals(startDate, position.startDate) &&
                    Objects.equals(endDate, position.endDate) &&Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description);
        }

        @Override
        public int hashCode() {
            int result = Objects.hashCode(startDate);
            result = 31 * result + Objects.hashCode(endDate);
            result = 31 * result + Objects.hashCode(title);
            result = 31 * result + Objects.hashCode(description);
            return result;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}

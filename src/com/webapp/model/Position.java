package com.webapp.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public class Position {
        private LocalDate startDate;
        private LocalDate endDate;
        private String title;
        private String description;

        public Position() {
        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();
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
        return Objects.equals(startDate, position.startDate) && Objects.equals(endDate, position.endDate) && Objects.equals(title, position.title) && Objects.equals(description, position.description);
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

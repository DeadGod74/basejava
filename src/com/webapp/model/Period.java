package com.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String name;
    private final String description;

    public Period(LocalDate startDate, LocalDate endDate, String name, String description) {
        this.startDate = Objects.requireNonNull(startDate, "startDate must not be null");
        this.endDate = Objects.requireNonNull(endDate, "endDate must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");

        if (this.endDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException("endDate must not be before startDate");
        }

        // Устанавливаем значение по умолчанию для description
        this.description = description != null ? description : ""; // Заменяем null на пустую строку
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;
        return startDate.equals(period.startDate) && endDate.equals(period.endDate) && name.equals(period.name) && description.equals(period.description);
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Period{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

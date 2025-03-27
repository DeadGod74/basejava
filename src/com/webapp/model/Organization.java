package com.webapp.model;

import com.webapp.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private final String nameOrg;
    private final String website;
    private List<Position> positions = new ArrayList<>();
    private List<Period> periods = new ArrayList<>();

    public Organization() {
        this.nameOrg = null;
        this.website = null;
        this.positions = new ArrayList<>();
        this.periods = new ArrayList<>();
    }

    public Organization(String name, String url, Position... positions) {
        this(name, url, List.of(positions));
    }

    public Organization(String name, String url, List<Position> positions) {
        this.nameOrg = Objects.requireNonNull(name, "name must not be null");
        this.website = Objects.requireNonNull(url, "website must not be null");
        this.positions = List.copyOf(positions);
        this.periods = new ArrayList<>();
    }

    public Organization(String name, String url, List<Position> positions, List<Period> periods) {
        this.nameOrg = Objects.requireNonNull(name, "name must not be null");
        this.website = Objects.requireNonNull(url, "website must not be null");
        this.positions = List.copyOf(positions);
        this.periods = List.copyOf(periods);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;
        return Objects.equals(nameOrg, that.nameOrg) && Objects.equals(website, that.website) && Objects.equals(positions, that.positions) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(nameOrg);
        result = 31 * result + Objects.hashCode(website);
        result = 31 * result + Objects.hashCode(positions);
        result = 31 * result + Objects.hashCode(periods);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "nameOrg='" + nameOrg + '\'' +
                ", website='" + website + '\'' +
                ", positions=" + positions +
                ", periods=" + periods +
                '}';
    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getnameOrg() {
        return nameOrg;
    }

    public String getWebsite() {
        return website;
    }

    public void addPeriod(Period period) {
        periods.add(period);
    }


    public static class Position {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private String description;

        public Position(int startYear, Month startMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), DateUtil.NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth), title, description);
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

        public Object getTitle() {
            return title;
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
}

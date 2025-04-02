package com.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompanySection extends Section{
    private final List<Company> companies;

    public CompanySection() {
        this.companies = new ArrayList<>();
    }

    public CompanySection(List<Company> organizations) {
        this.companies = organizations;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;
        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(companies);
    }

    @Override
    public List<Company> getContent() {
        return companies;
    }
}

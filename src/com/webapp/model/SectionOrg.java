package com.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SectionOrg extends Section{
    private List<Organization> organizations;

    public SectionOrg() {
        this.organizations = new ArrayList<>();
    }

    public SectionOrg(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        SectionOrg that = (SectionOrg) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(organizations);
    }

    @Override
    public List<Organization> getContent() {
        return organizations;
    }
}

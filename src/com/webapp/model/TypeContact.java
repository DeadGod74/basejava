package com.webapp.model;

public enum TypeContact {
    PHONE("Телефон"),
    SKYPE("Skype"),
    MAIL("Электронная почта"),
    PROFILE_LINKEDN("Профиль LinkedIn"),
    PROFILE_GITHUB("Профиль GitHub"),
    PROFILE_STACKOVERFLOW("Профиль Stackoverflow"),
    HOME_PAGE("Домашняя страница");






    private String title;

    TypeContact(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}

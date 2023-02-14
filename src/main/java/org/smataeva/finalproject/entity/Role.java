package org.smataeva.finalproject.entity;

public enum Role {
    DOCTOR("DOCTOR"), GUEST("GUEST"), PATIENT("PATIENT");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

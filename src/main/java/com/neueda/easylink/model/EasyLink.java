package com.neueda.easylink.model;

import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EasyLink {

    @Id
    private String id;
    private String redirectLink;

    public EasyLink(String redirectLink, String id) {
        this.redirectLink = redirectLink;
        this.id = id;
    }

    public EasyLink(String redirectLink) {
        this.redirectLink = redirectLink;
        this.id = createId();
    }

    public EasyLink() {
        // This constructor is here because Spring Data JPA spec requires a no-arg constructor to be present.
    }

    public String getId() {
        return id;
    }

    public String getRedirectLink() {
        return redirectLink;
    }

    private String createId() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EasyLink easyLink = (EasyLink) o;
        return getRedirectLink().equals(easyLink.getRedirectLink()) && getId().equals(easyLink.getId());
    }
}

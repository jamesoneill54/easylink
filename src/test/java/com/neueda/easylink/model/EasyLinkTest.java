package com.neueda.easylink.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.matchesPattern;

class EasyLinkTest {

    @Test
    void createsAlphanumericId() {
        EasyLink easyLink = new EasyLink("http://original/link");
        assertThat(easyLink.getId(), matchesPattern("[a-zA-Z0-9]*$"));
    }

    @Test
    void returnsOriginalLinks() {
        String originalLink1 = "https://original/link/one";
        String originalLink2 = "https://original/link/two";
        EasyLink easyLink1 = new EasyLink(originalLink1);
        EasyLink easyLink2 = new EasyLink(originalLink2);
        Assertions.assertEquals(originalLink1, easyLink1.getRedirectLink());
        Assertions.assertEquals(originalLink2, easyLink2.getRedirectLink());
    }
}

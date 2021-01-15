package com.neueda.easylink.controller;

import com.neueda.easylink.model.EasyLink;
import com.neueda.easylink.repository.EasyLinkRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EasyLinkController.class)
class EasyLinkControllerTest {

    @MockBean
    private EasyLinkRepository repository;

    @Autowired
    private MockMvc mvc;

    @Test
    void createsShortLinkWithPrefix() throws Exception {
        String content = mvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON).content("{ \"link\": \"https://test/link\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.easyLink").exists())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println(content);
    }

    @Test
    void redirectsGivenShortLink() throws Exception {
        String id = "4Ghdf67q";
        String originalLink = "https://original/link";
        Mockito.when(repository.findById(id))
                .thenReturn(new EasyLink(originalLink, id));
        mvc.perform(get("/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(originalLink));
    }

    @Test
    void pageNotFoundWithInvalidId() throws Exception {
        String id = "thgty43B";
        Mockito.when(repository.findById(id))
                .thenReturn(null);
        mvc.perform(get("/" + id))
                .andExpect(status().isNotFound());
    }
}

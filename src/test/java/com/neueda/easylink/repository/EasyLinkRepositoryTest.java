package com.neueda.easylink.repository;

import com.neueda.easylink.model.EasyLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EasyLinkRepositoryTest {

    @Autowired
    private EasyLinkRepository repository;

    @Test
    void savesLinksToRepository() {
        EasyLink link1 = new EasyLink("https://test/link/1");
        EasyLink link2 = new EasyLink("https://test/link/2");
        repository.save(link1);
        repository.save(link2);
        Assertions.assertEquals(2, repository.count());
    }

    @Test
    void retrievesLinksByEasyLink() {
        EasyLink link = new EasyLink("https://test/link");
        repository.save(link);
        repository.save(new EasyLink("https://dummy/link"));
        Assertions.assertEquals(link, repository.findById(link.getId()));
    }
}

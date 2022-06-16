package io.zelun.pokeapiapp.Repo;

import io.zelun.pokeapiapp.model.Info;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class InfoRepoTest {
    @Autowired
    private InfoRepo infoRepo;

    private Info info;

    @Before
    public void setUp() {
        info = Info.builder()
                .name("ditto")
                .build();
    }

    @Test
    public void createInfo() {
        infoRepo.save(info);
        assertTrue(infoRepo.existsById(info.getId()));
        assertEquals(info.getCreateDate().getTime(), infoRepo.findInfoByName("ditto").getCreateDate().getTime());
        Date oldUpdatedAt = info.getModifyDate();
        infoRepo.save(info.setName("ditto"));
        assertEquals(oldUpdatedAt.getTime(), infoRepo.findInfoByName("ditto").getCreateDate().getTime());

    }
}
package org.tutor.botweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.tutor.botweb.demos.DemoControl;
import org.tutor.seleniumbot.service.DemoService;

@SpringBootTest
@AutoConfigureMockMvc
class BotWebApplicationTests {

//    @Autowired
//    DemoControl demoControl;

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/demo/getMessage"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Autowired
    DemoService demoService;

    @Test
    void contextLoads2() {
        demoService.computeTime4RequestUrl();
    }
}

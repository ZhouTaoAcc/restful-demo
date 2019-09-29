package com.kedacom;

import com.kedacom.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration/*开启web应用的配置*/
public class DemoRestfulApplicationTests {
	private MockMvc mvc;/*用于模拟调用Controller的接口发起请求*/
	@Before
	public void setUp(){
		mvc= MockMvcBuilders.standaloneSetup(new UserController()).build();
	}
	@Test
	public void contextLoads() {
		try {
			mvc.perform(MockMvcRequestBuilders.get("/user/all").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
					.andExpect(content().string(equalTo("ok")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

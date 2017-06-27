package com.piggymetrics.bike.controller;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piggymetrics.bike.BikeApplication;
import com.piggymetrics.bike.domain.BikeInfo;
import com.piggymetrics.bike.service.SharedBikeServiceImpl;
import com.sun.security.auth.UserPrincipal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BikeApplication.class)
@WebAppConfiguration
public class SharedBikeControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private SharedBikeController accountController;

	@Mock
	private SharedBikeServiceImpl sharedBikeService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}
	
	@Test
	public void shouldRegisterNewAccount() throws Exception {

		final BikeInfo user = new BikeInfo();
		user.setBikeId("test");

		String json = mapper.writeValueAsString(user);
		System.out.println(json);
		mockMvc.perform(post("/").principal(new UserPrincipal(user.getBikeId())).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldGetAccountByName() throws Exception {

		final BikeInfo account = new BikeInfo();
		account.setBikeId("test");

		when(sharedBikeService.findByBikeId(account.getBikeId())).thenReturn(account);

		mockMvc.perform(get("/" + account.getBikeId()))
				.andExpect(jsonPath("$.bikeId").value(account.getBikeId()))
				.andExpect(status().isOk());
	}

	
}

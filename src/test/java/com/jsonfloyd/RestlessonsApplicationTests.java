package com.jsonfloyd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsonfloyd.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import com.jsonfloyd.controller.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Before;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("classpath:data.sql")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RestlessonsApplicationTests {

	@Autowired
	private OrderController controller;
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before

	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		mockMvc.perform(post("/orders/", new Order(1,2,3, new Timestamp(System.currentTimeMillis()))));
		mockMvc.perform(post("/orders/", new Order(2,1,1, new Timestamp(System.currentTimeMillis()))));
		mockMvc.perform(post("/orders/", new Order(3,3,2, new Timestamp(System.currentTimeMillis()))));
		mockMvc.perform(post("/orders/", new Order(4,2,3, new Timestamp(System.currentTimeMillis()))));
	}
	@Test
	public void contextLoads() {
		assertThat(wac.getServletContext()).isNotNull();

	}
	//TODO: test coverage

	@Test
	 public void getOrder() throws Exception {
		this.mockMvc.perform(get("/orders/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("id").value(1));
	}
	@Test
	public void postOrder() throws Exception{
		Order order = new Order(5,2,3,new Timestamp(System.currentTimeMillis()));
		this.mockMvc.perform(post("/orders/")
				.content(asJsonString(order))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL))
				.andExpect(status().isOk());
	}
	@Test
	public void getAllOrders() throws Exception{
		this.mockMvc.perform(get("/orders/").accept(MediaType.parseMediaType("application/json")))
				.andExpect(status().isOk());
	}
	@Test
	public void updateOrder() throws Exception{
		Order order = new Order();
		order.setId(0);
		order.setUserId(2);
		order.setPositionId(3);
		order.setOrderDate(new Timestamp(System.currentTimeMillis()));
		this.mockMvc.perform(put("/orders/")
				.content(asJsonString(order))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andExpect(jsonPath("$.id").value(0))
				.andExpect(jsonPath("$.userId").value(2))
				.andExpect(jsonPath("$.positionId").value(3));
	}
	@Test
	public void deleteOrder() throws Exception{
		this.mockMvc.perform(delete("/orders/3").accept(MediaType.parseMediaType("application/json")))
				.andExpect(status().isOk());
	}
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

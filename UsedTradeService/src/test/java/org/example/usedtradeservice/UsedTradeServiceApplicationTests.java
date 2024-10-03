package org.example.usedtradeservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controller.ProductController;
import org.example.entity.Product;
import org.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsedTradeServiceApplicationTests {

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController productController;
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
		objectMapper = new ObjectMapper();
	}

//	@Test
//	void testCreateProduct() throws Exception {
//		Product product = new Product(1L, "Product A", 100);
//		when(productService.createProduct(any(Product.class)))
//	}


}

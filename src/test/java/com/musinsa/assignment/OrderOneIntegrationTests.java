package com.musinsa.assignment;

import com.musinsa.assignment.claim.ClaimType;
import com.musinsa.assignment.claim.dto.DeliveryFeeOut;
import com.musinsa.assignment.claim.dto.RegisterClaimIn;
import com.musinsa.assignment.claim.dto.RegisterClaimOut;
import com.musinsa.assignment.common.dto.CommonResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderOneIntegrationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@Test
	@DisplayName("주문번호 1 - 상품A 교환 접수")
	@Order(1)
	void registerClaim1(){

		RegisterClaimIn in = new RegisterClaimIn(1L, Collections.singletonList(1L), ClaimType.EXCHANGE);
		HttpEntity<RegisterClaimIn> requestEntity = createHttpEntity(in);

		ResponseEntity<CommonResponse<RegisterClaimOut>> responseEntity =
				testRestTemplate.exchange("/claim", HttpMethod.POST, requestEntity,
						new ParameterizedTypeReference<CommonResponse<RegisterClaimOut>>() {});

		CommonResponse<RegisterClaimOut> body = responseEntity.getBody();


		assert body != null;
		assertTrue(body.isSuccess());
	}

	@Test
	@DisplayName("주문번호 1 - 상품B 환불 접수")
	@Order(2)
	void registerClaim2(){

		RegisterClaimIn in = new RegisterClaimIn(1L, Collections.singletonList(2L), ClaimType.RETURN);
		HttpEntity<RegisterClaimIn> requestEntity = createHttpEntity(in);

		ResponseEntity<CommonResponse<RegisterClaimOut>> responseEntity =
				testRestTemplate.exchange("/claim", HttpMethod.POST, requestEntity,
						new ParameterizedTypeReference<CommonResponse<RegisterClaimOut>>() {});

		CommonResponse<RegisterClaimOut> body = responseEntity.getBody();


		assert body != null;
		assertTrue(body.isSuccess());
	}

	@Test
	@DisplayName("주문번호 1 - 상품A, 상품C 환불 접수")
	@Order(3)
	void registerClaim3(){

		RegisterClaimIn in = new RegisterClaimIn(1L, Arrays.asList(1L, 3L), ClaimType.RETURN);
		HttpEntity<RegisterClaimIn> requestEntity = createHttpEntity(in);

		ResponseEntity<CommonResponse<RegisterClaimOut>> responseEntity =
				testRestTemplate.exchange("/claim", HttpMethod.POST, requestEntity,
						new ParameterizedTypeReference<CommonResponse<RegisterClaimOut>>() {});

		CommonResponse<RegisterClaimOut> body = responseEntity.getBody();


		assert body != null;
		assertTrue(body.isSuccess());
	}

	@Test
	@DisplayName("주문번호 1 - 반품/교환 접수에 대한 반품비 조회")
	@Order(4)
	void getClaimResult(){

		ResponseEntity<CommonResponse<DeliveryFeeOut>> responseEntity =
				testRestTemplate.exchange("/claim/1", HttpMethod.GET, new HttpEntity<>(new HttpHeaders()),
						new ParameterizedTypeReference<CommonResponse<DeliveryFeeOut>>() {});

		CommonResponse<DeliveryFeeOut> body = responseEntity.getBody();


		assert body != null;
		assertTrue(body.isSuccess());
		assertEquals(12_500, body.getData().getDeliveryFee());
	}

	private <T> HttpEntity<T> createHttpEntity(T t){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(t, httpHeaders);
	}

}

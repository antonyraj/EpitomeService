package anto.epitome.service.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import anto.epitome.vo.EpitomeBaseResponse;
import anto.epitome.vo.EpitomeRequest;

@Controller
public class EpitomeHomeController {
	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {

		try {
			/*
			 * This is code to post and return a user object
			 */
			RestTemplate rt = new RestTemplate();
			rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			rt.getMessageConverters().add(new StringHttpMessageConverter());
			String uri = new String("http://localhost:1417/EpitomeService/payment/");
			String param="pay?key=SHARED_KEY";
			String nameParam="hello?key=SHARED_KEY&name=Antony raj S";
			EpitomeRequest u = new EpitomeRequest();
			u.setUserId(1);
			u.setItemId("item1");
			u.setDiscount(0.01D);
			EpitomeBaseResponse returns = rt.postForObject(uri+param, u, EpitomeBaseResponse.class);
			String serResponse = rt.postForObject(uri+nameParam, u, String.class);
			
			System.out.println("-----From caller 1----");
			System.out.println("Status : "+returns.getStatus());
			System.out.println("Status code : "+returns.getCode());
			System.out.println("-----From caller 2----");
			System.out.println("Name Service : "+serResponse);

		} catch (HttpClientErrorException e) {

		} catch (Exception e) {
		}

		return new ModelAndView("home");
	}
}

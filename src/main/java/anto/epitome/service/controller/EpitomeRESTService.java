package anto.epitome.service.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import anto.epitome.vo.EpitomeBaseResponse;
import anto.epitome.vo.EpitomeRequest;

@RestController
@RequestMapping("/payment")
public class EpitomeRESTService {

	private final String sharedKey = "SHARED_KEY";
	private static final String SUCCESS_STATUS = "success";
	private static final String ERROR_STATUS = "error";
	private static final int CODE_SUCCESS = 100;
	private static final int AUTH_FAILURE = 102;

	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	@ResponseBody
	public EpitomeBaseResponse pay(@RequestParam(value = "key") String key, @RequestBody EpitomeRequest request) {
		
		System.out.println("Hi Antony");
		EpitomeBaseResponse response = new EpitomeBaseResponse();
		if (sharedKey.equalsIgnoreCase(key)) {
			int userId = request.getUserId();
			String itemId = request.getItemId();
			double discount = request.getDiscount();
			
			System.out.println("-----From Service----");
			System.out.println("User Id : "+userId);
			System.out.println("Item Id : "+itemId);
			System.out.println("Discount : "+discount);
			// Process the request
			// ....
			// Return success response to the client.
			response.setStatus(SUCCESS_STATUS);
			response.setCode(CODE_SUCCESS);
		} else {
			response.setStatus(ERROR_STATUS);
			response.setCode(AUTH_FAILURE);
		}
		return response;
	}
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	@ResponseBody
	public String pay(@RequestParam(value = "key") String key,@RequestParam(value = "name") String name) {
		String response = "";
		if (sharedKey.equalsIgnoreCase(key)) {
			 
			response = "Hello..."+name;
		} else {
			response = "Hello...<Key does not match>";
		}
		return response;
	}
}

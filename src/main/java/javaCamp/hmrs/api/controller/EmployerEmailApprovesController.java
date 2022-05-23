package javaCamp.hmrs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.verification.email.EmployerEmailApproveService;

@RestController
@RequestMapping("/api/approves/employeremailaproves")
public class EmployerEmailApprovesController {

	private EmployerEmailApproveService employerEmailApproveService;

	@Autowired
	public EmployerEmailApprovesController(EmployerEmailApproveService employerEmailApproveService) {
		super();
		this.employerEmailApproveService = employerEmailApproveService;
	}
	

	@GetMapping("/verifyemail")
	Result verifyEmail(@RequestParam String code) {

		return this.employerEmailApproveService.verifyemail(code);

	}

	
//	//Kod bilgilerini getirir
//	@GetMapping("/getapprovedbyverifycode")
//	Result getApproveByVerifyCode(@RequestParam String code) {
//		return this.employerEmailApproveService.getApproveByVerifyCode(code);
//
//	}
	
	
}

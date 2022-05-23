package javaCamp.hmrs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javaCamp.hmrs.core.utilities.verification.email.EmailVerificationService;


@RestController
@RequestMapping("/api/approves/emailapproves")
public class EmailApprovesController {

	@Qualifier("emailVerificationManager")
	private EmailVerificationService emailVerificationService;

	@Autowired
	public EmailApprovesController(
			@Qualifier("emailVerificationManager") EmailVerificationService emailVerificationService) {
		super();
		this.emailVerificationService = emailVerificationService;
	}



//	@GetMapping("/getbyverifiycode")
//	DataResult<BaseEmailApprove> verifyCode(@RequestParam String verifyCode) {
//
//		return this.emailVerificationService.getApproveByVerifyCode(verifyCode);
//
//	}

}

package javaCamp.hmrs.api.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.verification.email.EmailVerificationService;
import javaCamp.hmrs.entites.concretes.BaseEmailApprove;
import javaCamp.hmrs.entites.concretes.EmployerUser;

@RestController
@RequestMapping("/api/approves/emailapproves")
public class EmailApprovesController {

	@Qualifier("emailVerificationManager")
	private EmailVerificationService emailVerificationService;

	public EmailApprovesController(
			@Qualifier("emailVerificationManager") EmailVerificationService emailVerificationService) {
		super();
		this.emailVerificationService = emailVerificationService;
	}

//	@PostMapping("/verifyCode")
//	Result verifyCode(@RequestBody BaseEmailApprove baseEmailApprove) {
//
//		return this.emailVerificationService.verifyCode(baseEmailApprove);
//
//	}

	@GetMapping("/getbyverifiycode")
	DataResult<BaseEmailApprove> verifyCode(@RequestParam String verifyCode) {

		return this.emailVerificationService.getApproveByVerifyCode(verifyCode);

	}

}

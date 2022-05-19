package javaCamp.hmrs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.hmrs.core.utilities.verification.email.EmailVerificationService;

@RestController
@RequestMapping("/api/approves")
public class ApprovesController {
	
	EmailVerificationService emailVerificationService;
	
	@Autowired
	public ApprovesController(
			@Qualifier("emailVerificationManager")	EmailVerificationService emailVerificationService) {
		super();
		this.emailVerificationService = emailVerificationService;
	}

}

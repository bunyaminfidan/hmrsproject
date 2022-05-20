package javaCamp.hmrs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.verification.email.JobSeekerEmailApproveService;
import javaCamp.hmrs.dataAccess.abstracts.JobSeekerEmailApproveDao;
import javaCamp.hmrs.entites.concretes.JobSeekerEmailApprove;
import javaCamp.hmrs.entites.concretes.User;

@RestController
@RequestMapping("/api/approves/jobseekeremailaproves")
public class JobSeekerEmailApprovesController {

	@Qualifier("jobSeekerEmailApproveManager")
	private JobSeekerEmailApproveService jobSeekerEmailApproveService;

	@Autowired
	public JobSeekerEmailApprovesController(
			@Qualifier("jobSeekerEmailApproveManager") JobSeekerEmailApproveService jobSeekerEmailApproveService) {
		super();
		this.jobSeekerEmailApproveService = jobSeekerEmailApproveService;
	}
	
//	@PostMapping("/add")
//	Result add(@RequestBody User user, String passwordAgain) {
//
//		return this.userService.add(user, passwordAgain);
//	}
	
	

	@GetMapping("/verifyemail")
	Result verifyEmail(@RequestParam String code) {

		return this.jobSeekerEmailApproveService.verifyemail(code);

	}

	
	//Kod bilgilerini getirir
	@GetMapping("/getapprovedbyverifycode")
	Result getApproveByVerifyCode(@RequestParam String code) {
		return this.jobSeekerEmailApproveService.getApproveByVerifyCode(code);

	}

}

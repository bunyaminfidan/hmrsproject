package javaCamp.hmrs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.hmrs.business.abstracts.JobSeekerUserService;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.JobSeekerUser;
import javaCamp.hmrs.entites.concretes.SystemUser;

@RestController
@RequestMapping("/api/users/jobseekerusers")
public class JobSeekerUsersController {

	@Qualifier("jobSeekerUserManager")
	private JobSeekerUserService jobSeekerUserService;

	@Autowired
	public JobSeekerUsersController(@Qualifier("jobSeekerUserManager") JobSeekerUserService jobSeekerUserService) {
		super();
		this.jobSeekerUserService = jobSeekerUserService;
	}
	
	@GetMapping("/getall")
	DataResult<List<JobSeekerUser>> getAll() {
		return this.jobSeekerUserService.getAll();

	}
	
	@PostMapping("/add")
	Result add(@RequestBody JobSeekerUser jobSeekerUser, @RequestParam String passwordAgain) {

		return this.jobSeekerUserService.add(jobSeekerUser, passwordAgain);

	}

	@GetMapping("/getbynationalityid")
	DataResult<JobSeekerUser> getByNationalityId(@RequestParam String nationalityId) {
		return this.jobSeekerUserService.getByNationalityId(nationalityId);
	}

}

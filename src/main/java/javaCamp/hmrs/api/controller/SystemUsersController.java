package javaCamp.hmrs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.hmrs.business.abstracts.SystemUserService;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.SystemUser;

@RestController
@RequestMapping("/api/users/systemusers")
public class SystemUsersController {

	@Qualifier("systemUserManager")
	private SystemUserService systemUserService;

	public SystemUsersController(@Qualifier("systemUserManager") SystemUserService systemUserService) {
		super();
		this.systemUserService = systemUserService;
	}

	@GetMapping("/getall")
	DataResult<List<SystemUser>> getAll() {
		return this.systemUserService.getAll();

	}

	@PostMapping("/add")
	Result add(@RequestBody SystemUser systemUser, String passwordAgain) {

		return this.systemUserService.add(systemUser, passwordAgain);

	}

	@GetMapping("/getbynationalityid")
	Result getByNationalityId(@RequestParam String nationalityId) {
		System.out.println(nationalityId);
		return this.systemUserService.getByNationalityId(nationalityId);
	}

}

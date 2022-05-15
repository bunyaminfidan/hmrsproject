package javaCamp.hmrs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.hmrs.business.abstracts.EmployerUserService;
import javaCamp.hmrs.core.utilities.results.DataResult;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.EmployerUser;

@RestController
@RequestMapping("/api/users/employerusers")
public class EmployerUsersController {

	private EmployerUserService employerUserService;

	@Autowired
	public EmployerUsersController(@Qualifier("EmployerUserManager") EmployerUserService employerUserService) {
		super();
		this.employerUserService = employerUserService;
	}

	@GetMapping("/getall")
	DataResult<List<EmployerUser>> getAll() {
		return this.employerUserService.getAll();

	}

	@PostMapping("/add")
	Result add(@RequestBody EmployerUser employerUser, String passwordAgain) {

		return this.employerUserService.add(employerUser, passwordAgain);

	}

}

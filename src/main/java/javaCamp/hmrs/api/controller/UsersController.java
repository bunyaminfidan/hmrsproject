package javaCamp.hmrs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javaCamp.hmrs.business.abstracts.UserService;
import javaCamp.hmrs.core.utilities.results.Result;


@RestController
@RequestMapping("/api/users")
public class UsersController {


	@Qualifier("userManager")
	UserService userService;

	@Autowired
	public UsersController(@Qualifier("userManager") UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/getbyemail")
	Result getByEmail(@RequestParam String email) {
		return this.userService.getByEmail(email);
	}

//	@PostMapping("/add")
//	Result add(@RequestBody User user, String passwordAgain) {
//
//		return this.userService.add(user, passwordAgain);
//	}

}

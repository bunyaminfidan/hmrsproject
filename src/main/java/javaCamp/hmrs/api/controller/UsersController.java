package javaCamp.hmrs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.hmrs.business.abstracts.UserService;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.User;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
	UserService userService;

	public UsersController(@Qualifier("UserManager") UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/getallemail")
	Result getall(@RequestBody User user) {
		return this.userService.emailCheck(user);
	}

}

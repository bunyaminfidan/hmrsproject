package javaCamp.hmrs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.hmrs.business.abstracts.SystemUserService;
import javaCamp.hmrs.core.utilities.results.DataResult;
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


}

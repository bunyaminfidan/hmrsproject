package javaCamp.hmrs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.core.utilities.verification.systemManager.SystemManagerVerificationService;

@RestController
@RequestMapping("/api/approves/systemmanageremployeruserapproves")
public class SystemManagerEmployerUserApprovesController {

	@Qualifier("systemManagerVerificationManager")
	private SystemManagerVerificationService systemManagerVerificationService;

	@Autowired
	public SystemManagerEmployerUserApprovesController(
			@Qualifier("systemManagerVerificationManager") SystemManagerVerificationService systemManagerVerificationService) {
		super();
		this.systemManagerVerificationService = systemManagerVerificationService;
	}

	@PostMapping("/add")
	Result add(@RequestParam int userId, int systemManagerId) {

		return this.systemManagerVerificationService.add(userId, systemManagerId);

	}

	// Kod bilgilerini getirir
	@GetMapping("/getbyemployeruserid")
	Result getByEmployerUserId(@RequestParam int id) {
		return this.systemManagerVerificationService.getByEmployerUserId(id);

	}

}

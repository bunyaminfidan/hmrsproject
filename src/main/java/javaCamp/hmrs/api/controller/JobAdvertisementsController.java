package javaCamp.hmrs.api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import javaCamp.hmrs.business.abstracts.JobAdvertisementService;
import javaCamp.hmrs.core.utilities.results.Result;
import javaCamp.hmrs.entites.concretes.JobAdvertisement;

@RestController
@RequestMapping("api/jobadvertisements")
public class JobAdvertisementsController {

	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}

	@PostMapping("/add")
	Result add(@RequestBody JobAdvertisement jobAdvertisement) {
		jobAdvertisement.setApplicationDeadline(LocalDate.now());
		return this.jobAdvertisementService.add(jobAdvertisement);
	}



	@GetMapping("/getAll")
	Result getAll() {

		return this.jobAdvertisementService.getAll();
	}

}

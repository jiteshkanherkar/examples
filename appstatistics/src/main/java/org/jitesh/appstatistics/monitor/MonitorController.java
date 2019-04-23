package org.jitesh.appstatistics.monitor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monitor")
public class MonitorController {

	@GetMapping("ping")
	public String ping() {
		return "SUCCESS";
	}
}

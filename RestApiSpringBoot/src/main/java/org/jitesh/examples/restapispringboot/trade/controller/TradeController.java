package org.jitesh.examples.restapispringboot.trade.controller;

import org.jitesh.examples.restapispringboot.trade.model.Trade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

	@PostMapping(value="/trade")
	public ResponseEntity<String> processTrade(@RequestBody Trade trade){
		System.out.println("trade :: "+trade);
		return ResponseEntity.ok("SUCCESS");
	}
}

package com.rank.ccms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rank.ccms.entities.BankMst;
import com.rank.ccms.service.BankMstService;

@RestController
public class BankMstController {
	@Autowired
	BankMstService bankMstService;

	//@RequestMapping(value = "/bank", method = RequestMethod.GET, produces = { "application/json" })
	@GetMapping("/bank")
	public List<BankMst> getAllEmployee() {
		System.out.println("hi");

		return bankMstService.getBankDetail();

	}

	// @RequestMapping(value = "/bank", method = RequestMethod.POST, produces =
	// { "application/json" })
	@PostMapping("/bank")
	public String addBank(@RequestBody BankMst bankMst) {

		int i = bankMstService.create(bankMst);

		return "Save Successfull and Id is " + i;

	}

	@PutMapping("/bank")
	public void updateBank(@RequestBody BankMst bankMst) {
		bankMstService.update(bankMst);
	}

	@DeleteMapping("/bank/{id}")
	public void daleteBank(@PathVariable int id) {
		bankMstService.delete(id);
		System.out.println(id);
	}
}

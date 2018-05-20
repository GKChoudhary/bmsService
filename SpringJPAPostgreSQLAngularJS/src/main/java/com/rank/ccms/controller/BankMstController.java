package com.rank.ccms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rank.ccms.entities.BankMst;
import com.rank.ccms.service.BankMstService;

@RestController
public class BankMstController {
	@Autowired
	BankMstService bankMstService;

	@RequestMapping(value = "/bank", method = RequestMethod.GET, produces = { "application/json" })
	public List<BankMst> getAllEmployee() {
		System.out.println("hi");

		return bankMstService.getBankDetail();

	}

	@RequestMapping(value = "/bank", method = RequestMethod.POST, produces = { "application/json" })
	public List<BankMst> addBank() {
		System.out.println("hi");

		return bankMstService.getBankDetail();

	}
}

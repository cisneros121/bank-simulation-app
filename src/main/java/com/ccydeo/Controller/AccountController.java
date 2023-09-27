package com.ccydeo.Controller;

import com.ccydeo.service.impl.AccountServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/index")
    public String returnAccount(Model model){

model.addAttribute("accountList",accountService.listAllAccounts());
        return "account/index";
    }
}

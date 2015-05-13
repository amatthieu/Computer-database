package com.excilys.malbert.controller.spring.mapper;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.malbert.controller.Page;
import com.excilys.malbert.service.ICompanyService;
import com.excilys.malbert.service.IComputerService;
import com.excilys.malbert.validator.Date.Pattern;
import com.excilys.malbert.validator.PageValidator;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardMapper {
    @Autowired
    private IComputerService serviceComputer;
    @Autowired
    private ICompanyService serviceCompany;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
	binder.setValidator(new PageValidator());
    }

    @RequestMapping(method = RequestMethod.GET)
    public String dashboard(@Valid @ModelAttribute Page page,
	    BindingResult result, Model model) {
	Locale locale = LocaleContextHolder.getLocale();
	Pattern language = Pattern.map(locale.getLanguage());
	if (result.hasErrors()) {
	    model.addAttribute("error", true);
	    model.addAttribute("errorMessage", "Invalid arguments");
	    page.toDefault();
	}
	page.populate(serviceComputer, serviceCompany, language);
	model.addAttribute(page);
	return "dashboard";
    }
}

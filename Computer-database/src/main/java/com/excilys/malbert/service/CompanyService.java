package com.excilys.malbert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.malbert.exceptions.ServiceException;
import com.excilys.malbert.persistence.ICompanyDAO;
import com.excilys.malbert.persistence.IComputerDAO;
import com.excilys.malbert.persistence.model.Company;
import com.excilys.malbert.validator.DbValidator;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private ICompanyDAO companyDAO;
    @Autowired
    private IComputerDAO computerDAO;

    @Override
    public List<Company> getAllCompanies() {
	return companyDAO.getAll();
    }

    @Override
    public Company getCompany(long id) {
	if (!DbValidator.isIdValid(id)) {
	    throw new ServiceException(DbValidator.INVALID_ID);
	}
	return companyDAO.getOne(id);
    }

    @Transactional
    @Override
    public void deleteCompany(long id) {
	computerDAO.deleteOfCompany(id);
	companyDAO.delete(id);
    }

    @Override
    public void setCompanyDAO(ICompanyDAO daoCompany) {
	this.companyDAO = daoCompany;
    }
}
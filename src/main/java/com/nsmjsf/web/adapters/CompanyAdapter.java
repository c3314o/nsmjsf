
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Company;
import com.nsmjsf.web.wrappers.CompanyWrapper;

public class CompanyAdapter {
private static final Log log = LogFactory
			.getLog(CompanyAdapter.class);
	
	public static List<CompanyWrapper> wrapAll(List<Company> companyList)
	{
		List<CompanyWrapper> companyWrapperList=new ArrayList<CompanyWrapper>();
		for(Company company:companyList)
		{
			CompanyWrapper companyWrapper=new CompanyWrapper();
			companyWrapper.setCompany(company);
			companyWrapperList.add(companyWrapper);
		}
		return companyWrapperList;
		
	}
	
	public static CompanyWrapper wrap(Company company)
	{
		CompanyWrapper companyWrapper=new CompanyWrapper();
		companyWrapper.setCompany(company);
		return companyWrapper;
		
	}

}


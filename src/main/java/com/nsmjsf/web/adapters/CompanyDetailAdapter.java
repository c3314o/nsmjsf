
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CompanyDetail;
import com.nsmjsf.web.wrappers.CompanyDetailWrapper;

public class CompanyDetailAdapter {
private static final Log log = LogFactory
			.getLog(CompanyDetailAdapter.class);
	
	public static List<CompanyDetailWrapper> wrapAll(List<CompanyDetail> companyDetailList)
	{
		List<CompanyDetailWrapper> companyDetailWrapperList=new ArrayList<CompanyDetailWrapper>();
		for(CompanyDetail companyDetail:companyDetailList)
		{
			CompanyDetailWrapper companyDetailWrapper=new CompanyDetailWrapper();
			companyDetailWrapper.setCompanyDetail(companyDetail);
			companyDetailWrapperList.add(companyDetailWrapper);
		}
		return companyDetailWrapperList;
		
	}
	
	public static CompanyDetailWrapper wrap(CompanyDetail companyDetail)
	{
		CompanyDetailWrapper companyDetailWrapper=new CompanyDetailWrapper();
		companyDetailWrapper.setCompanyDetail(companyDetail);
		return companyDetailWrapper;
		
	}

}


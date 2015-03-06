package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CompanyBranch;
import com.nsmjsf.web.wrappers.CompanyBranchWrapper;

public class CompanyBranchAdapter {
	private static final Log log = LogFactory
			.getLog(CompanyBranchAdapter.class);

	public static List<CompanyBranchWrapper> wrapAll(
			List<CompanyBranch> companyBranchList) {
		List<CompanyBranchWrapper> companyBranchWrapperList = new ArrayList<CompanyBranchWrapper>();
		for (CompanyBranch companyBranch : companyBranchList) {
			CompanyBranchWrapper companyBranchWrapper = new CompanyBranchWrapper();
			companyBranchWrapper.setCompanyBranch(companyBranch);
			companyBranchWrapperList.add(companyBranchWrapper);
		}
		return companyBranchWrapperList;

	}

	public static CompanyBranchWrapper wrap(CompanyBranch companyBranch) {
		CompanyBranchWrapper companyBranchWrapper = new CompanyBranchWrapper();
		companyBranchWrapper.setCompanyBranch(companyBranch);
		return companyBranchWrapper;

	}

}

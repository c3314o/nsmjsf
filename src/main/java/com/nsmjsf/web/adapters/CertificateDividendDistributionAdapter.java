
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CertificateDividendDistribution;
import com.nsmjsf.web.wrappers.CertificateDividendDistributionWrapper;

public class CertificateDividendDistributionAdapter {
private static final Log log = LogFactory
			.getLog(CertificateDividendDistributionAdapter.class);
	
	public static List<CertificateDividendDistributionWrapper> wrapAll(List<CertificateDividendDistribution> certificateDividendDistributionList)
	{
		List<CertificateDividendDistributionWrapper> certificateDividendDistributionWrapperList=new ArrayList<CertificateDividendDistributionWrapper>();
		for(CertificateDividendDistribution certificateDividendDistribution:certificateDividendDistributionList)
		{
			CertificateDividendDistributionWrapper certificateDividendDistributionWrapper=new CertificateDividendDistributionWrapper();
			certificateDividendDistributionWrapper.setCertificateDividendDistribution(certificateDividendDistribution);
			certificateDividendDistributionWrapperList.add(certificateDividendDistributionWrapper);
		}
		return certificateDividendDistributionWrapperList;
		
	}
	
	public static CertificateDividendDistributionWrapper wrap(CertificateDividendDistribution certificateDividendDistribution)
	{
		CertificateDividendDistributionWrapper certificateDividendDistributionWrapper=new CertificateDividendDistributionWrapper();
		certificateDividendDistributionWrapper.setCertificateDividendDistribution(certificateDividendDistribution);
		return certificateDividendDistributionWrapper;
		
	}

}


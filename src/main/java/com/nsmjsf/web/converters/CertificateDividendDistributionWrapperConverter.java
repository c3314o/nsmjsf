package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.CertificateDividendDistributionAdapter;
import com.nsmjsf.web.datasources.CertificateDividendDistributionDataSource;
import com.nsmjsf.web.datamodels.CertificateDividendDistribution;
import com.nsmjsf.web.wrappers.CertificateDividendDistributionWrapper;

@FacesConverter("certificateDividendDistributionWrapperConverter")
public class CertificateDividendDistributionWrapperConverter implements
		Converter {
	private static final Log log = LogFactory
			.getLog(CertificateDividendDistributionWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			CertificateDividendDistributionDataSource certificateDividendDistributionDataSource = new CertificateDividendDistributionDataSource();
			CertificateDividendDistribution certificateDividendDistribution = certificateDividendDistributionDataSource
					.get(Integer.parseInt(value));
			CertificateDividendDistributionWrapper certificateDividendDistributionWrapper = CertificateDividendDistributionAdapter
					.wrap(certificateDividendDistribution);
			return certificateDividendDistributionWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String
					.valueOf(((CertificateDividendDistributionWrapper) object)
							.getCertificateDividendDistribution()
							.getCertificateDividendDistributionId());
		} else {
			return null;
		}
	}

}

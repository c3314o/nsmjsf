
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CompanyDetail;

public class CompanyDetailWrapper {

private static final Log log = LogFactory
			.getLog(CompanyDetailWrapper.class);


	CompanyDetail companyDetail;

	public CompanyDetailWrapper(CompanyDetail companyDetail) {
		this.companyDetail = companyDetail;
	}

	public CompanyDetailWrapper() {

	}

	public CompanyDetail getCompanyDetail() {
		return companyDetail;
	}

	public void setCompanyDetail(CompanyDetail companyDetail) {
		this.companyDetail = companyDetail;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.companyDetail.getCompanyDetailId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CompanyDetailWrapper other = (CompanyDetailWrapper) obj;
		if (!Objects.equals(this.companyDetail.getCompanyDetailId(), other.getCompanyDetail().getCompanyDetailId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.companyDetail.toString();

	}

}


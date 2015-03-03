
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Company;

public class CompanyWrapper {

private static final Log log = LogFactory
			.getLog(CompanyWrapper.class);


	Company company;

	public CompanyWrapper(Company company) {
		this.company = company;
	}

	public CompanyWrapper() {

	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.company.getCompanyId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CompanyWrapper other = (CompanyWrapper) obj;
		if (!Objects.equals(this.company.getCompanyId(), other.getCompany().getCompanyId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.company.toString();

	}

}


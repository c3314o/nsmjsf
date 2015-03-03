
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CompanyBranch;

public class CompanyBranchWrapper {

private static final Log log = LogFactory
			.getLog(CompanyBranchWrapper.class);


	CompanyBranch companyBranch;

	public CompanyBranchWrapper(CompanyBranch companyBranch) {
		this.companyBranch = companyBranch;
	}

	public CompanyBranchWrapper() {

	}

	public CompanyBranch getCompanyBranch() {
		return companyBranch;
	}

	public void setCompanyBranch(CompanyBranch companyBranch) {
		this.companyBranch = companyBranch;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.companyBranch.getCompanyBranchId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CompanyBranchWrapper other = (CompanyBranchWrapper) obj;
		if (!Objects.equals(this.companyBranch.getCompanyBranchId(), other.getCompanyBranch().getCompanyBranchId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.companyBranch.toString();

	}

}


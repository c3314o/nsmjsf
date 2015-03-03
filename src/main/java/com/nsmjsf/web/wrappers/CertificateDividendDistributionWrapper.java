
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CertificateDividendDistribution;

public class CertificateDividendDistributionWrapper {

private static final Log log = LogFactory
			.getLog(CertificateDividendDistributionWrapper.class);


	CertificateDividendDistribution certificateDividendDistribution;

	public CertificateDividendDistributionWrapper(CertificateDividendDistribution certificateDividendDistribution) {
		this.certificateDividendDistribution = certificateDividendDistribution;
	}

	public CertificateDividendDistributionWrapper() {

	}

	public CertificateDividendDistribution getCertificateDividendDistribution() {
		return certificateDividendDistribution;
	}

	public void setCertificateDividendDistribution(CertificateDividendDistribution certificateDividendDistribution) {
		this.certificateDividendDistribution = certificateDividendDistribution;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.certificateDividendDistribution.getCertificateDividendDistributionId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CertificateDividendDistributionWrapper other = (CertificateDividendDistributionWrapper) obj;
		if (!Objects.equals(this.certificateDividendDistribution.getCertificateDividendDistributionId(), other.getCertificateDividendDistribution().getCertificateDividendDistributionId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.certificateDividendDistribution.toString();

	}

}


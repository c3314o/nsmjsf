
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.AuditStatus;

public class AuditStatusWrapper {

private static final Log log = LogFactory
			.getLog(AuditStatusWrapper.class);


	AuditStatus auditStatus;

	public AuditStatusWrapper(AuditStatus auditStatus) {
		this.auditStatus = auditStatus;
	}

	public AuditStatusWrapper() {

	}

	public AuditStatus getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(AuditStatus auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.auditStatus.getAuditStatusId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AuditStatusWrapper other = (AuditStatusWrapper) obj;
		if (!Objects.equals(this.auditStatus.getAuditStatusId(), other.getAuditStatus().getAuditStatusId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.auditStatus.toString();

	}

}


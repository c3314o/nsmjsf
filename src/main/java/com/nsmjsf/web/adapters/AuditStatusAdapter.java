package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.AuditStatus;
import com.nsmjsf.web.wrappers.AuditStatusWrapper;

public class AuditStatusAdapter {
	private static final Log log = LogFactory.getLog(AuditStatusAdapter.class);

	public static List<AuditStatusWrapper> wrapAll(
			List<AuditStatus> auditStatusList) {
		List<AuditStatusWrapper> auditStatusWrapperList = new ArrayList<AuditStatusWrapper>();
		for (AuditStatus auditStatus : auditStatusList) {
			AuditStatusWrapper auditStatusWrapper = new AuditStatusWrapper();
			auditStatusWrapper.setAuditStatus(auditStatus);
			auditStatusWrapperList.add(auditStatusWrapper);
		}
		return auditStatusWrapperList;

	}

	public static AuditStatusWrapper wrap(AuditStatus auditStatus) {
		AuditStatusWrapper auditStatusWrapper = new AuditStatusWrapper();
		auditStatusWrapper.setAuditStatus(auditStatus);
		return auditStatusWrapper;

	}

}

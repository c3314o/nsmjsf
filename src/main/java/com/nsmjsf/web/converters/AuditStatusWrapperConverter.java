package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.AuditStatusAdapter;
import com.nsmjsf.web.datasources.AuditStatusDataSource;
import com.nsmjsf.web.datamodels.AuditStatus;
import com.nsmjsf.web.wrappers.AuditStatusWrapper;


@FacesConverter("auditStatusWrapperConverter")
public class AuditStatusWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(AuditStatusWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			AuditStatusDataSource auditStatusDataSource = new AuditStatusDataSource();
			AuditStatus auditStatus = auditStatusDataSource.get(Integer.parseInt(value));
			AuditStatusWrapper auditStatusWrapper=AuditStatusAdapter.wrap(auditStatus);
			return auditStatusWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((AuditStatusWrapper) object).getAuditStatus().getAuditStatusId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


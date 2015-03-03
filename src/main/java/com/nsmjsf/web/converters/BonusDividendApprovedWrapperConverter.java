package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.BonusDividendApprovedAdapter;
import com.nsmjsf.web.datasources.BonusDividendApprovedDataSource;
import com.nsmjsf.web.datamodels.BonusDividendApproved;
import com.nsmjsf.web.wrappers.BonusDividendApprovedWrapper;


@FacesConverter("bonusDividendApprovedWrapperConverter")
public class BonusDividendApprovedWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(BonusDividendApprovedWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			BonusDividendApprovedDataSource bonusDividendApprovedDataSource = new BonusDividendApprovedDataSource();
			BonusDividendApproved bonusDividendApproved = bonusDividendApprovedDataSource.get(Integer.parseInt(value));
			BonusDividendApprovedWrapper bonusDividendApprovedWrapper=BonusDividendApprovedAdapter.wrap(bonusDividendApproved);
			return bonusDividendApprovedWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((BonusDividendApprovedWrapper) object).getBonusDividendApproved().getBonusDividendApprovedId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


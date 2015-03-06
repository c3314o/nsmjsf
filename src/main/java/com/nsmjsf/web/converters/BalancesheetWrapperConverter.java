package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.BalancesheetAdapter;
import com.nsmjsf.web.datasources.BalancesheetDataSource;
import com.nsmjsf.web.datamodels.Balancesheet;
import com.nsmjsf.web.wrappers.BalancesheetWrapper;

@FacesConverter("balancesheetWrapperConverter")
public class BalancesheetWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(BalancesheetWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			BalancesheetDataSource balancesheetDataSource = new BalancesheetDataSource();
			Balancesheet balancesheet = balancesheetDataSource.get(Integer
					.parseInt(value));
			BalancesheetWrapper balancesheetWrapper = BalancesheetAdapter
					.wrap(balancesheet);
			return balancesheetWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((BalancesheetWrapper) object)
					.getBalancesheet().getBalancesheetId());
		} else {
			return null;
		}
	}

}

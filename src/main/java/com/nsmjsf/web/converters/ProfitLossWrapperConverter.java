package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.ProfitLossAdapter;
import com.nsmjsf.web.datasources.ProfitLossDataSource;
import com.nsmjsf.web.datamodels.ProfitLoss;
import com.nsmjsf.web.wrappers.ProfitLossWrapper;

@FacesConverter("profitLossWrapperConverter")
public class ProfitLossWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(ProfitLossWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			ProfitLossDataSource profitLossDataSource = new ProfitLossDataSource();
			ProfitLoss profitLoss = profitLossDataSource.get(Integer
					.parseInt(value));
			ProfitLossWrapper profitLossWrapper = ProfitLossAdapter
					.wrap(profitLoss);
			return profitLossWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((ProfitLossWrapper) object).getProfitLoss()
					.getProfitLossId());
		} else {
			return null;
		}
	}

}

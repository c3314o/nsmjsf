package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.FinancialHighlightAdapter;
import com.nsmjsf.web.datasources.FinancialHighlightDataSource;
import com.nsmjsf.web.datamodels.FinancialHighlight;
import com.nsmjsf.web.wrappers.FinancialHighlightWrapper;

@FacesConverter("financialHighlightWrapperConverter")
public class FinancialHighlightWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(FinancialHighlightWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			FinancialHighlightDataSource financialHighlightDataSource = new FinancialHighlightDataSource();
			FinancialHighlight financialHighlight = financialHighlightDataSource
					.get(Integer.parseInt(value));
			FinancialHighlightWrapper financialHighlightWrapper = FinancialHighlightAdapter
					.wrap(financialHighlight);
			return financialHighlightWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((FinancialHighlightWrapper) object)
					.getFinancialHighlight().getFinancialHighlightId());
		} else {
			return null;
		}
	}

}

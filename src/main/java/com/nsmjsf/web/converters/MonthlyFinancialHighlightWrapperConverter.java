package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.MonthlyFinancialHighlightAdapter;
import com.nsmjsf.web.datasources.MonthlyFinancialHighlightDataSource;
import com.nsmjsf.web.datamodels.MonthlyFinancialHighlight;
import com.nsmjsf.web.wrappers.MonthlyFinancialHighlightWrapper;


@FacesConverter("monthlyFinancialHighlightWrapperConverter")
public class MonthlyFinancialHighlightWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(MonthlyFinancialHighlightWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			MonthlyFinancialHighlightDataSource monthlyFinancialHighlightDataSource = new MonthlyFinancialHighlightDataSource();
			MonthlyFinancialHighlight monthlyFinancialHighlight = monthlyFinancialHighlightDataSource.get(Integer.parseInt(value));
			MonthlyFinancialHighlightWrapper monthlyFinancialHighlightWrapper=MonthlyFinancialHighlightAdapter.wrap(monthlyFinancialHighlight);
			return monthlyFinancialHighlightWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((MonthlyFinancialHighlightWrapper) object).getMonthlyFinancialHighlight().getMonthlyFinancialHighlightId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


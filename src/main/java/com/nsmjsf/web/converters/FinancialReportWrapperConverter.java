package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.FinancialReportAdapter;
import com.nsmjsf.web.datasources.FinancialReportDataSource;
import com.nsmjsf.web.datamodels.FinancialReport;
import com.nsmjsf.web.wrappers.FinancialReportWrapper;

@FacesConverter("financialReportWrapperConverter")
public class FinancialReportWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(FinancialReportWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			FinancialReportDataSource financialReportDataSource = new FinancialReportDataSource();
			FinancialReport financialReport = financialReportDataSource
					.get(Integer.parseInt(value));
			FinancialReportWrapper financialReportWrapper = FinancialReportAdapter
					.wrap(financialReport);
			return financialReportWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((FinancialReportWrapper) object)
					.getFinancialReport().getFinancialReportId());
		} else {
			return null;
		}
	}

}

package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.SemiYearAverageAdapter;
import com.nsmjsf.web.datasources.SemiYearAverageDataSource;
import com.nsmjsf.web.datamodels.SemiYearAverage;
import com.nsmjsf.web.wrappers.SemiYearAverageWrapper;


@FacesConverter("semiYearAverageWrapperConverter")
public class SemiYearAverageWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(SemiYearAverageWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			SemiYearAverageDataSource semiYearAverageDataSource = new SemiYearAverageDataSource();
			SemiYearAverage semiYearAverage = semiYearAverageDataSource.get(Integer.parseInt(value));
			SemiYearAverageWrapper semiYearAverageWrapper=SemiYearAverageAdapter.wrap(semiYearAverage);
			return semiYearAverageWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((SemiYearAverageWrapper) object).getSemiYearAverage().getSemiYearAverageId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


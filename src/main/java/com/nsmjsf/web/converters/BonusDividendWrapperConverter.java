package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.BonusDividendAdapter;
import com.nsmjsf.web.datasources.BonusDividendDataSource;
import com.nsmjsf.web.datamodels.BonusDividend;
import com.nsmjsf.web.wrappers.BonusDividendWrapper;


@FacesConverter("bonusDividendWrapperConverter")
public class BonusDividendWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(BonusDividendWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			BonusDividendDataSource bonusDividendDataSource = new BonusDividendDataSource();
			BonusDividend bonusDividend = bonusDividendDataSource.get(Integer.parseInt(value));
			BonusDividendWrapper bonusDividendWrapper=BonusDividendAdapter.wrap(bonusDividend);
			return bonusDividendWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((BonusDividendWrapper) object).getBonusDividend().getBonusDividendId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.BullionPriceAdapter;
import com.nsmjsf.web.datasources.BullionPriceDataSource;
import com.nsmjsf.web.datamodels.BullionPrice;
import com.nsmjsf.web.wrappers.BullionPriceWrapper;


@FacesConverter("bullionPriceWrapperConverter")
public class BullionPriceWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(BullionPriceWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			BullionPriceDataSource bullionPriceDataSource = new BullionPriceDataSource();
			BullionPrice bullionPrice = bullionPriceDataSource.get(Integer.parseInt(value));
			BullionPriceWrapper bullionPriceWrapper=BullionPriceAdapter.wrap(bullionPrice);
			return bullionPriceWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((BullionPriceWrapper) object).getBullionPrice().getBullionPriceId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


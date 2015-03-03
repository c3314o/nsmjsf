package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.BullionTypeAdapter;
import com.nsmjsf.web.datasources.BullionTypeDataSource;
import com.nsmjsf.web.datamodels.BullionType;
import com.nsmjsf.web.wrappers.BullionTypeWrapper;


@FacesConverter("bullionTypeWrapperConverter")
public class BullionTypeWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(BullionTypeWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			BullionTypeDataSource bullionTypeDataSource = new BullionTypeDataSource();
			BullionType bullionType = bullionTypeDataSource.get(Integer.parseInt(value));
			BullionTypeWrapper bullionTypeWrapper=BullionTypeAdapter.wrap(bullionType);
			return bullionTypeWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((BullionTypeWrapper) object).getBullionType().getBullionTypeId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


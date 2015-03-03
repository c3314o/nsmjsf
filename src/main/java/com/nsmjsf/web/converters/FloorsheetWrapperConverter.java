package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.FloorsheetAdapter;
import com.nsmjsf.web.datasources.FloorsheetDataSource;
import com.nsmjsf.web.datamodels.Floorsheet;
import com.nsmjsf.web.wrappers.FloorsheetWrapper;


@FacesConverter("floorsheetWrapperConverter")
public class FloorsheetWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(FloorsheetWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			FloorsheetDataSource floorsheetDataSource = new FloorsheetDataSource();
			Floorsheet floorsheet = floorsheetDataSource.get(Integer.parseInt(value));
			FloorsheetWrapper floorsheetWrapper=FloorsheetAdapter.wrap(floorsheet);
			return floorsheetWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((FloorsheetWrapper) object).getFloorsheet().getFloorsheetId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


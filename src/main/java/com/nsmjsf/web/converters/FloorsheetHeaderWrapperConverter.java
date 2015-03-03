package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.FloorsheetHeaderAdapter;
import com.nsmjsf.web.datasources.FloorsheetHeaderDataSource;
import com.nsmjsf.web.datamodels.FloorsheetHeader;
import com.nsmjsf.web.wrappers.FloorsheetHeaderWrapper;


@FacesConverter("floorsheetHeaderWrapperConverter")
public class FloorsheetHeaderWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(FloorsheetHeaderWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			FloorsheetHeaderDataSource floorsheetHeaderDataSource = new FloorsheetHeaderDataSource();
			FloorsheetHeader floorsheetHeader = floorsheetHeaderDataSource.get(Integer.parseInt(value));
			FloorsheetHeaderWrapper floorsheetHeaderWrapper=FloorsheetHeaderAdapter.wrap(floorsheetHeader);
			return floorsheetHeaderWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((FloorsheetHeaderWrapper) object).getFloorsheetHeader().getFloorsheetHeaderId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


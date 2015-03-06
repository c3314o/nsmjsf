package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.FloorsheetDumpAdapter;
import com.nsmjsf.web.datasources.FloorsheetDumpDataSource;
import com.nsmjsf.web.datamodels.FloorsheetDump;
import com.nsmjsf.web.wrappers.FloorsheetDumpWrapper;

@FacesConverter("floorsheetDumpWrapperConverter")
public class FloorsheetDumpWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(FloorsheetDumpWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			FloorsheetDumpDataSource floorsheetDumpDataSource = new FloorsheetDumpDataSource();
			FloorsheetDump floorsheetDump = floorsheetDumpDataSource
					.get(Integer.parseInt(value));
			FloorsheetDumpWrapper floorsheetDumpWrapper = FloorsheetDumpAdapter
					.wrap(floorsheetDump);
			return floorsheetDumpWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((FloorsheetDumpWrapper) object)
					.getFloorsheetDump().getFloorsheetDumpId());
		} else {
			return null;
		}
	}

}

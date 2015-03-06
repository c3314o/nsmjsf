package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.SectorAdapter;
import com.nsmjsf.web.datasources.SectorDataSource;
import com.nsmjsf.web.datamodels.Sector;
import com.nsmjsf.web.wrappers.SectorWrapper;

@FacesConverter("sectorWrapperConverter")
public class SectorWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(SectorWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			SectorDataSource sectorDataSource = new SectorDataSource();
			Sector sector = sectorDataSource.get(Integer.parseInt(value));
			SectorWrapper sectorWrapper = SectorAdapter.wrap(sector);
			return sectorWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((SectorWrapper) object).getSector()
					.getSectorId());
		} else {
			return null;
		}
	}

}

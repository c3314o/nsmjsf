package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.BodTypeAdapter;
import com.nsmjsf.web.datasources.BodTypeDataSource;
import com.nsmjsf.web.datamodels.BodType;
import com.nsmjsf.web.wrappers.BodTypeWrapper;

@FacesConverter("bodTypeWrapperConverter")
public class BodTypeWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(BodTypeWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			BodTypeDataSource bodTypeDataSource = new BodTypeDataSource();
			BodType bodType = bodTypeDataSource.get(Integer.parseInt(value));
			BodTypeWrapper bodTypeWrapper = BodTypeAdapter.wrap(bodType);
			return bodTypeWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((BodTypeWrapper) object).getBodType()
					.getBodTypeId());
		} else {
			return null;
		}
	}

}

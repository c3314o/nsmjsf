package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.KeyRatioAdapter;
import com.nsmjsf.web.datasources.KeyRatioDataSource;
import com.nsmjsf.web.datamodels.KeyRatio;
import com.nsmjsf.web.wrappers.KeyRatioWrapper;

@FacesConverter("keyRatioWrapperConverter")
public class KeyRatioWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(KeyRatioWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			KeyRatioDataSource keyRatioDataSource = new KeyRatioDataSource();
			KeyRatio keyRatio = keyRatioDataSource.get(Integer.parseInt(value));
			KeyRatioWrapper keyRatioWrapper = KeyRatioAdapter.wrap(keyRatio);
			return keyRatioWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((KeyRatioWrapper) object).getKeyRatio()
					.getKeyRatioId());
		} else {
			return null;
		}
	}

}

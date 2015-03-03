package com.nsmjsf.web.utils;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ParameterManager {
	private static final Log log = LogFactory.getLog(ParameterManager.class);

	public static int getInt(String key) {
		String value;
		try {
			value = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get(key);
			return Integer.parseInt(value);
		} catch (Exception ex) {
			log.info(ex.getMessage());
			return 0;
		}

	}

	public static String getString(String key) {
		String value;
		try {
			value = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get(key);
			return value;
		} catch (Exception ex) {
			log.info(ex.getMessage());
			return null;
		}

	}
}

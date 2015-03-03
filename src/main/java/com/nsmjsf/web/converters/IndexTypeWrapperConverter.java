package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.IndexTypeAdapter;
import com.nsmjsf.web.datasources.IndexTypeDataSource;
import com.nsmjsf.web.datamodels.IndexType;
import com.nsmjsf.web.wrappers.IndexTypeWrapper;


@FacesConverter("indexTypeWrapperConverter")
public class IndexTypeWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(IndexTypeWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			IndexTypeDataSource indexTypeDataSource = new IndexTypeDataSource();
			IndexType indexType = indexTypeDataSource.get(Integer.parseInt(value));
			IndexTypeWrapper indexTypeWrapper=IndexTypeAdapter.wrap(indexType);
			return indexTypeWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((IndexTypeWrapper) object).getIndexType().getIndexTypeId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


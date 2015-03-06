package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.PostCategoryAdapter;
import com.nsmjsf.web.datasources.PostCategoryDataSource;
import com.nsmjsf.web.datamodels.PostCategory;
import com.nsmjsf.web.wrappers.PostCategoryWrapper;

@FacesConverter("postCategoryWrapperConverter")
public class PostCategoryWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(PostCategoryWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			PostCategoryDataSource postCategoryDataSource = new PostCategoryDataSource();
			PostCategory postCategory = postCategoryDataSource.get(Integer
					.parseInt(value));
			PostCategoryWrapper postCategoryWrapper = PostCategoryAdapter
					.wrap(postCategory);
			return postCategoryWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((PostCategoryWrapper) object)
					.getPostCategory().getPostCategoryId());
		} else {
			return null;
		}
	}

}

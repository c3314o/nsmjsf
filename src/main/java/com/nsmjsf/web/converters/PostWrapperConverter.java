package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.PostAdapter;
import com.nsmjsf.web.datasources.PostDataSource;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.wrappers.PostWrapper;

@FacesConverter("postWrapperConverter")
public class PostWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(PostWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			PostDataSource postDataSource = new PostDataSource();
			Post post = postDataSource.get(Integer.parseInt(value));
			PostWrapper postWrapper = PostAdapter.wrap(post);
			return postWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((PostWrapper) object).getPost().getPostId());
		} else {
			return null;
		}
	}

}

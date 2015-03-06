package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.PostImageAdapter;
import com.nsmjsf.web.datasources.PostImageDataSource;
import com.nsmjsf.web.datamodels.PostImage;
import com.nsmjsf.web.wrappers.PostImageWrapper;

@FacesConverter("postImageWrapperConverter")
public class PostImageWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(PostImageWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			PostImageDataSource postImageDataSource = new PostImageDataSource();
			PostImage postImage = postImageDataSource.get(Integer
					.parseInt(value));
			PostImageWrapper postImageWrapper = PostImageAdapter
					.wrap(postImage);
			return postImageWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((PostImageWrapper) object).getPostImage()
					.getPostImageId());
		} else {
			return null;
		}
	}

}

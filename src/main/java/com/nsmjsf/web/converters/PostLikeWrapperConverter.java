package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.PostLikeAdapter;
import com.nsmjsf.web.datasources.PostLikeDataSource;
import com.nsmjsf.web.datamodels.PostLike;
import com.nsmjsf.web.wrappers.PostLikeWrapper;


@FacesConverter("postLikeWrapperConverter")
public class PostLikeWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(PostLikeWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			PostLikeDataSource postLikeDataSource = new PostLikeDataSource();
			PostLike postLike = postLikeDataSource.get(Integer.parseInt(value));
			PostLikeWrapper postLikeWrapper=PostLikeAdapter.wrap(postLike);
			return postLikeWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((PostLikeWrapper) object).getPostLike().getPostLikeId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


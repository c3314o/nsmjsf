package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.CommentSocialAdapter;
import com.nsmjsf.web.datasources.CommentSocialDataSource;
import com.nsmjsf.web.datamodels.CommentSocial;
import com.nsmjsf.web.wrappers.CommentSocialWrapper;


@FacesConverter("commentSocialWrapperConverter")
public class CommentSocialWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(CommentSocialWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			CommentSocialDataSource commentSocialDataSource = new CommentSocialDataSource();
			CommentSocial commentSocial = commentSocialDataSource.get(Integer.parseInt(value));
			CommentSocialWrapper commentSocialWrapper=CommentSocialAdapter.wrap(commentSocial);
			return commentSocialWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((CommentSocialWrapper) object).getCommentSocial().getCommentSocialId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


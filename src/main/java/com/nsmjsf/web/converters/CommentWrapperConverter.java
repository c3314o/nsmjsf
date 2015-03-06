package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.CommentAdapter;
import com.nsmjsf.web.datasources.CommentDataSource;
import com.nsmjsf.web.datamodels.Comment;
import com.nsmjsf.web.wrappers.CommentWrapper;

@FacesConverter("commentWrapperConverter")
public class CommentWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(CommentWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			CommentDataSource commentDataSource = new CommentDataSource();
			Comment comment = commentDataSource.get(Integer.parseInt(value));
			CommentWrapper commentWrapper = CommentAdapter.wrap(comment);
			return commentWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((CommentWrapper) object).getComment()
					.getCommentId());
		} else {
			return null;
		}
	}

}

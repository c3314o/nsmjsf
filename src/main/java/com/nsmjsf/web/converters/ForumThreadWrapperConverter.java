package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.ForumThreadAdapter;
import com.nsmjsf.web.datasources.ForumThreadDataSource;
import com.nsmjsf.web.datamodels.ForumThread;
import com.nsmjsf.web.wrappers.ForumThreadWrapper;


@FacesConverter("forumThreadWrapperConverter")
public class ForumThreadWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(ForumThreadWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			ForumThreadDataSource forumThreadDataSource = new ForumThreadDataSource();
			ForumThread forumThread = forumThreadDataSource.get(Integer.parseInt(value));
			ForumThreadWrapper forumThreadWrapper=ForumThreadAdapter.wrap(forumThread);
			return forumThreadWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((ForumThreadWrapper) object).getForumThread().getForumThreadId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


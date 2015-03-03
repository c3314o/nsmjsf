package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.ArticleAdapter;
import com.nsmjsf.web.datasources.ArticleDataSource;
import com.nsmjsf.web.datamodels.Article;
import com.nsmjsf.web.wrappers.ArticleWrapper;


@FacesConverter("articleWrapperConverter")
public class ArticleWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(ArticleWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			ArticleDataSource articleDataSource = new ArticleDataSource();
			Article article = articleDataSource.get(Integer.parseInt(value));
			ArticleWrapper articleWrapper=ArticleAdapter.wrap(article);
			return articleWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((ArticleWrapper) object).getArticle().getArticleId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}


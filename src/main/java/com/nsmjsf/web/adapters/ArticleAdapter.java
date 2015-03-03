
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Article;
import com.nsmjsf.web.wrappers.ArticleWrapper;

public class ArticleAdapter {
private static final Log log = LogFactory
			.getLog(ArticleAdapter.class);
	
	public static List<ArticleWrapper> wrapAll(List<Article> articleList)
	{
		List<ArticleWrapper> articleWrapperList=new ArrayList<ArticleWrapper>();
		for(Article article:articleList)
		{
			ArticleWrapper articleWrapper=new ArticleWrapper();
			articleWrapper.setArticle(article);
			articleWrapperList.add(articleWrapper);
		}
		return articleWrapperList;
		
	}
	
	public static ArticleWrapper wrap(Article article)
	{
		ArticleWrapper articleWrapper=new ArticleWrapper();
		articleWrapper.setArticle(article);
		return articleWrapper;
		
	}

}


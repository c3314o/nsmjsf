package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Article;

public class ArticleWrapper {

	private static final Log log = LogFactory.getLog(ArticleWrapper.class);

	Article article;

	public ArticleWrapper(Article article) {
		this.article = article;
	}

	public ArticleWrapper() {

	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.article.getArticleId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ArticleWrapper other = (ArticleWrapper) obj;
		if (!Objects.equals(this.article.getArticleId(), other.getArticle()
				.getArticleId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.article.toString();

	}

}

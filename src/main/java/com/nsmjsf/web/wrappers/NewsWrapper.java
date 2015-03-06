package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.News;

public class NewsWrapper {

	private static final Log log = LogFactory.getLog(NewsWrapper.class);

	News news;

	public NewsWrapper(News news) {
		this.news = news;
	}

	public NewsWrapper() {

	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.news.getNewsId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final NewsWrapper other = (NewsWrapper) obj;
		if (!Objects.equals(this.news.getNewsId(), other.getNews().getNewsId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.news.toString();

	}

}

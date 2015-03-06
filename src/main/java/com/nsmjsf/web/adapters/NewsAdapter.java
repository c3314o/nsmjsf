package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.News;
import com.nsmjsf.web.wrappers.NewsWrapper;

public class NewsAdapter {
	private static final Log log = LogFactory.getLog(NewsAdapter.class);

	public static List<NewsWrapper> wrapAll(List<News> newsList) {
		List<NewsWrapper> newsWrapperList = new ArrayList<NewsWrapper>();
		for (News news : newsList) {
			NewsWrapper newsWrapper = new NewsWrapper();
			newsWrapper.setNews(news);
			newsWrapperList.add(newsWrapper);
		}
		return newsWrapperList;

	}

	public static NewsWrapper wrap(News news) {
		NewsWrapper newsWrapper = new NewsWrapper();
		newsWrapper.setNews(news);
		return newsWrapper;

	}

}

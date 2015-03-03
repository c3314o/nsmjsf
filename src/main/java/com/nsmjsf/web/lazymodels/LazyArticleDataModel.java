

package com.nsmjsf.web.lazymodels;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.nsmjsf.web.datamodels.Article;
import com.nsmjsf.web.sorters.ArticleSorter;

public class LazyArticleDataModel extends LazyDataModel<Article> {
	private static final Log log = LogFactory
			.getLog(LazyArticleDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Article> articleList;

	public LazyArticleDataModel(List<Article> articleList) {
		this.articleList = articleList;
	}

	@Override
	public Article getRowData(String rowKey) {
		for (Article article : articleList) {
			if (article.getArticleId().toString().equalsIgnoreCase(rowKey))
				return article;
		}

		return null;
	}

	@Override
	public Object getRowKey(Article object) {
		return object.getArticleId();
	}

	@Override
	public List<Article> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Article> data = new ArrayList<Article>();

		// filter
		for (Article article : articleList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = article.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(article));
						log.info("filterField:"+filterProperty);
						log.info("filterValue:"+fieldValue);

						if (filterValue == null
								|| fieldValue
										.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception e) {
						match = false;
					}
				}
			}

			if (match) {
				data.add(article);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new ArticleSorter(sortField, sortOrder));
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}

}


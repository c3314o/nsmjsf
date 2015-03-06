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

import com.nsmjsf.web.datamodels.News;
import com.nsmjsf.web.sorters.NewsSorter;

public class LazyNewsDataModel extends LazyDataModel<News> {
	private static final Log log = LogFactory.getLog(LazyNewsDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<News> newsList;

	public LazyNewsDataModel(List<News> newsList) {
		this.newsList = newsList;
	}

	@Override
	public News getRowData(String rowKey) {
		for (News news : newsList) {
			if (String.valueOf(news.getNewsId()).equalsIgnoreCase(rowKey))
				return news;
		}

		return null;
	}

	@Override
	public Object getRowKey(News object) {
		return object.getNewsId();
	}

	@Override
	public List<News> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<News> data = new ArrayList<News>();

		// filter
		for (News news : newsList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = news.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(news));
						log.info("filterField:" + filterProperty);
						log.info("filterValue:" + fieldValue);

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
				data.add(news);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new NewsSorter(sortField, sortOrder));
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

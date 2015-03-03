

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

import com.nsmjsf.web.datamodels.ContentSource;
import com.nsmjsf.web.sorters.ContentSourceSorter;

public class LazyContentSourceDataModel extends LazyDataModel<ContentSource> {
	private static final Log log = LogFactory
			.getLog(LazyContentSourceDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<ContentSource> contentSourceList;

	public LazyContentSourceDataModel(List<ContentSource> contentSourceList) {
		this.contentSourceList = contentSourceList;
	}

	@Override
	public ContentSource getRowData(String rowKey) {
		for (ContentSource contentSource : contentSourceList) {
			if (contentSource.getContentSourceId().toString().equalsIgnoreCase(rowKey))
				return contentSource;
		}

		return null;
	}

	@Override
	public Object getRowKey(ContentSource object) {
		return object.getContentSourceId();
	}

	@Override
	public List<ContentSource> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<ContentSource> data = new ArrayList<ContentSource>();

		// filter
		for (ContentSource contentSource : contentSourceList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = contentSource.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(contentSource));
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
				data.add(contentSource);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new ContentSourceSorter(sortField, sortOrder));
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


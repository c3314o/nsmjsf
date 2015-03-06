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

import com.nsmjsf.web.datamodels.PostCategory;
import com.nsmjsf.web.sorters.PostCategorySorter;

public class LazyPostCategoryDataModel extends LazyDataModel<PostCategory> {
	private static final Log log = LogFactory
			.getLog(LazyPostCategoryDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<PostCategory> postCategoryList;

	public LazyPostCategoryDataModel(List<PostCategory> postCategoryList) {
		this.postCategoryList = postCategoryList;
	}

	@Override
	public PostCategory getRowData(String rowKey) {
		for (PostCategory postCategory : postCategoryList) {
			if (postCategory.getPostCategoryId().toString()
					.equalsIgnoreCase(rowKey))
				return postCategory;
		}

		return null;
	}

	@Override
	public Object getRowKey(PostCategory object) {
		return object.getPostCategoryId();
	}

	@Override
	public List<PostCategory> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<PostCategory> data = new ArrayList<PostCategory>();

		// filter
		for (PostCategory postCategory : postCategoryList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = postCategory.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(postCategory));
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
				data.add(postCategory);
			}
		}

		// sort
		if (sortField != null) {
			Collections
					.sort(data, new PostCategorySorter(sortField, sortOrder));
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

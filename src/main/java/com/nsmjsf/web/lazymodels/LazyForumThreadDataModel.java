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

import com.nsmjsf.web.datamodels.ForumThread;
import com.nsmjsf.web.sorters.ForumThreadSorter;

public class LazyForumThreadDataModel extends LazyDataModel<ForumThread> {
	private static final Log log = LogFactory
			.getLog(LazyForumThreadDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<ForumThread> forumThreadList;

	public LazyForumThreadDataModel(List<ForumThread> forumThreadList) {
		this.forumThreadList = forumThreadList;
	}

	@Override
	public ForumThread getRowData(String rowKey) {
		for (ForumThread forumThread : forumThreadList) {
			if (forumThread.getForumThreadId().toString()
					.equalsIgnoreCase(rowKey))
				return forumThread;
		}

		return null;
	}

	@Override
	public Object getRowKey(ForumThread object) {
		return object.getForumThreadId();
	}

	@Override
	public List<ForumThread> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<ForumThread> data = new ArrayList<ForumThread>();

		// filter
		for (ForumThread forumThread : forumThreadList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = forumThread.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(forumThread));
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
				data.add(forumThread);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new ForumThreadSorter(sortField, sortOrder));
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

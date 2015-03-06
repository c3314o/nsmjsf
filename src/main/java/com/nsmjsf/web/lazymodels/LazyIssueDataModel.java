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

import com.nsmjsf.web.datamodels.Issue;
import com.nsmjsf.web.sorters.IssueSorter;

public class LazyIssueDataModel extends LazyDataModel<Issue> {
	private static final Log log = LogFactory.getLog(LazyIssueDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Issue> issueList;

	public LazyIssueDataModel(List<Issue> issueList) {
		this.issueList = issueList;
	}

	@Override
	public Issue getRowData(String rowKey) {
		for (Issue issue : issueList) {
			if (issue.getIssueId().toString().equalsIgnoreCase(rowKey))
				return issue;
		}

		return null;
	}

	@Override
	public Object getRowKey(Issue object) {
		return object.getIssueId();
	}

	@Override
	public List<Issue> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Issue> data = new ArrayList<Issue>();

		// filter
		for (Issue issue : issueList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = issue.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(issue));
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
				data.add(issue);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new IssueSorter(sortField, sortOrder));
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

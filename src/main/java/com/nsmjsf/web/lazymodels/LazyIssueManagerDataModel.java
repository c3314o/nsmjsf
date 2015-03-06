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

import com.nsmjsf.web.datamodels.IssueManager;
import com.nsmjsf.web.sorters.IssueManagerSorter;

public class LazyIssueManagerDataModel extends LazyDataModel<IssueManager> {
	private static final Log log = LogFactory
			.getLog(LazyIssueManagerDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<IssueManager> issueManagerList;

	public LazyIssueManagerDataModel(List<IssueManager> issueManagerList) {
		this.issueManagerList = issueManagerList;
	}

	@Override
	public IssueManager getRowData(String rowKey) {
		for (IssueManager issueManager : issueManagerList) {
			if (issueManager.getIssueManagerId().toString()
					.equalsIgnoreCase(rowKey))
				return issueManager;
		}

		return null;
	}

	@Override
	public Object getRowKey(IssueManager object) {
		return object.getIssueManagerId();
	}

	@Override
	public List<IssueManager> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<IssueManager> data = new ArrayList<IssueManager>();

		// filter
		for (IssueManager issueManager : issueManagerList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = issueManager.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(issueManager));
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
				data.add(issueManager);
			}
		}

		// sort
		if (sortField != null) {
			Collections
					.sort(data, new IssueManagerSorter(sortField, sortOrder));
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



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

import com.nsmjsf.web.datamodels.Interview;
import com.nsmjsf.web.sorters.InterviewSorter;

public class LazyInterviewDataModel extends LazyDataModel<Interview> {
	private static final Log log = LogFactory
			.getLog(LazyInterviewDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Interview> interviewList;

	public LazyInterviewDataModel(List<Interview> interviewList) {
		this.interviewList = interviewList;
	}

	@Override
	public Interview getRowData(String rowKey) {
		for (Interview interview : interviewList) {
			if (interview.getInterviewId().toString().equalsIgnoreCase(rowKey))
				return interview;
		}

		return null;
	}

	@Override
	public Object getRowKey(Interview object) {
		return object.getInterviewId();
	}

	@Override
	public List<Interview> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Interview> data = new ArrayList<Interview>();

		// filter
		for (Interview interview : interviewList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = interview.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(interview));
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
				data.add(interview);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new InterviewSorter(sortField, sortOrder));
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


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

import com.nsmjsf.web.datamodels.AllotmentResult;
import com.nsmjsf.web.sorters.AllotmentResultSorter;

public class LazyAllotmentResultDataModel extends
		LazyDataModel<AllotmentResult> {
	private static final Log log = LogFactory
			.getLog(LazyAllotmentResultDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<AllotmentResult> allotmentResultList;

	public LazyAllotmentResultDataModel(
			List<AllotmentResult> allotmentResultList) {
		this.allotmentResultList = allotmentResultList;
	}

	@Override
	public AllotmentResult getRowData(String rowKey) {
		for (AllotmentResult allotmentResult : allotmentResultList) {
			if (allotmentResult.getAllotmentResultId().toString()
					.equalsIgnoreCase(rowKey))
				return allotmentResult;
		}

		return null;
	}

	@Override
	public Object getRowKey(AllotmentResult object) {
		return object.getAllotmentResultId();
	}

	@Override
	public List<AllotmentResult> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<AllotmentResult> data = new ArrayList<AllotmentResult>();

		// filter
		for (AllotmentResult allotmentResult : allotmentResultList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = allotmentResult.getClass()
								.getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(allotmentResult));
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
				data.add(allotmentResult);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new AllotmentResultSorter(sortField,
					sortOrder));
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

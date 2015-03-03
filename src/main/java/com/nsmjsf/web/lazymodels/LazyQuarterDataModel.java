

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

import com.nsmjsf.web.datamodels.Quarter;
import com.nsmjsf.web.sorters.QuarterSorter;

public class LazyQuarterDataModel extends LazyDataModel<Quarter> {
	private static final Log log = LogFactory
			.getLog(LazyQuarterDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Quarter> quarterList;

	public LazyQuarterDataModel(List<Quarter> quarterList) {
		this.quarterList = quarterList;
	}

	@Override
	public Quarter getRowData(String rowKey) {
		for (Quarter quarter : quarterList) {
			if (quarter.getQuarterId().toString().equalsIgnoreCase(rowKey))
				return quarter;
		}

		return null;
	}

	@Override
	public Object getRowKey(Quarter object) {
		return object.getQuarterId();
	}

	@Override
	public List<Quarter> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Quarter> data = new ArrayList<Quarter>();

		// filter
		for (Quarter quarter : quarterList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = quarter.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(quarter));
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
				data.add(quarter);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new QuarterSorter(sortField, sortOrder));
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


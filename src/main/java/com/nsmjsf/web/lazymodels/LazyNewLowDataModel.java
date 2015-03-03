

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

import com.nsmjsf.web.datamodels.NewLow;
import com.nsmjsf.web.sorters.NewLowSorter;

public class LazyNewLowDataModel extends LazyDataModel<NewLow> {
	private static final Log log = LogFactory
			.getLog(LazyNewLowDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<NewLow> newLowList;

	public LazyNewLowDataModel(List<NewLow> newLowList) {
		this.newLowList = newLowList;
	}

	@Override
	public NewLow getRowData(String rowKey) {
		for (NewLow newLow : newLowList) {
			if (newLow.getNewLowId().toString().equalsIgnoreCase(rowKey))
				return newLow;
		}

		return null;
	}

	@Override
	public Object getRowKey(NewLow object) {
		return object.getNewLowId();
	}

	@Override
	public List<NewLow> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<NewLow> data = new ArrayList<NewLow>();

		// filter
		for (NewLow newLow : newLowList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = newLow.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(newLow));
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
				data.add(newLow);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new NewLowSorter(sortField, sortOrder));
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


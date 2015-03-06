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

import com.nsmjsf.web.datamodels.NewHigh;
import com.nsmjsf.web.sorters.NewHighSorter;

public class LazyNewHighDataModel extends LazyDataModel<NewHigh> {
	private static final Log log = LogFactory
			.getLog(LazyNewHighDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<NewHigh> newHighList;

	public LazyNewHighDataModel(List<NewHigh> newHighList) {
		this.newHighList = newHighList;
	}

	@Override
	public NewHigh getRowData(String rowKey) {
		for (NewHigh newHigh : newHighList) {
			if (newHigh.getNewHighId().toString().equalsIgnoreCase(rowKey))
				return newHigh;
		}

		return null;
	}

	@Override
	public Object getRowKey(NewHigh object) {
		return object.getNewHighId();
	}

	@Override
	public List<NewHigh> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<NewHigh> data = new ArrayList<NewHigh>();

		// filter
		for (NewHigh newHigh : newHighList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = newHigh.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(newHigh));
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
				data.add(newHigh);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new NewHighSorter(sortField, sortOrder));
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

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

import com.nsmjsf.web.datamodels.Balancesheet;
import com.nsmjsf.web.sorters.BalancesheetSorter;

public class LazyBalancesheetDataModel extends LazyDataModel<Balancesheet> {
	private static final Log log = LogFactory
			.getLog(LazyBalancesheetDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Balancesheet> balancesheetList;

	public LazyBalancesheetDataModel(List<Balancesheet> balancesheetList) {
		this.balancesheetList = balancesheetList;
	}

	@Override
	public Balancesheet getRowData(String rowKey) {
		for (Balancesheet balancesheet : balancesheetList) {
			if (balancesheet.getBalancesheetId().toString()
					.equalsIgnoreCase(rowKey))
				return balancesheet;
		}

		return null;
	}

	@Override
	public Object getRowKey(Balancesheet object) {
		return object.getBalancesheetId();
	}

	@Override
	public List<Balancesheet> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Balancesheet> data = new ArrayList<Balancesheet>();

		// filter
		for (Balancesheet balancesheet : balancesheetList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = balancesheet.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(balancesheet));
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
				data.add(balancesheet);
			}
		}

		// sort
		if (sortField != null) {
			Collections
					.sort(data, new BalancesheetSorter(sortField, sortOrder));
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

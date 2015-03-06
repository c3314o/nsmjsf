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

import com.nsmjsf.web.datamodels.MonthlyFinancialHighlight;
import com.nsmjsf.web.sorters.MonthlyFinancialHighlightSorter;

public class LazyMonthlyFinancialHighlightDataModel extends
		LazyDataModel<MonthlyFinancialHighlight> {
	private static final Log log = LogFactory
			.getLog(LazyMonthlyFinancialHighlightDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<MonthlyFinancialHighlight> monthlyFinancialHighlightList;

	public LazyMonthlyFinancialHighlightDataModel(
			List<MonthlyFinancialHighlight> monthlyFinancialHighlightList) {
		this.monthlyFinancialHighlightList = monthlyFinancialHighlightList;
	}

	@Override
	public MonthlyFinancialHighlight getRowData(String rowKey) {
		for (MonthlyFinancialHighlight monthlyFinancialHighlight : monthlyFinancialHighlightList) {
			if (monthlyFinancialHighlight.getMonthlyFinancialHighlightId()
					.toString().equalsIgnoreCase(rowKey))
				return monthlyFinancialHighlight;
		}

		return null;
	}

	@Override
	public Object getRowKey(MonthlyFinancialHighlight object) {
		return object.getMonthlyFinancialHighlightId();
	}

	@Override
	public List<MonthlyFinancialHighlight> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<MonthlyFinancialHighlight> data = new ArrayList<MonthlyFinancialHighlight>();

		// filter
		for (MonthlyFinancialHighlight monthlyFinancialHighlight : monthlyFinancialHighlightList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = monthlyFinancialHighlight.getClass()
								.getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(monthlyFinancialHighlight));
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
				data.add(monthlyFinancialHighlight);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new MonthlyFinancialHighlightSorter(
					sortField, sortOrder));
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

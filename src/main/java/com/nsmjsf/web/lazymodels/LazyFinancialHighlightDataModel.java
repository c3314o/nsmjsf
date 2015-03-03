

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

import com.nsmjsf.web.datamodels.FinancialHighlight;
import com.nsmjsf.web.sorters.FinancialHighlightSorter;

public class LazyFinancialHighlightDataModel extends LazyDataModel<FinancialHighlight> {
	private static final Log log = LogFactory
			.getLog(LazyFinancialHighlightDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<FinancialHighlight> financialHighlightList;

	public LazyFinancialHighlightDataModel(List<FinancialHighlight> financialHighlightList) {
		this.financialHighlightList = financialHighlightList;
	}

	@Override
	public FinancialHighlight getRowData(String rowKey) {
		for (FinancialHighlight financialHighlight : financialHighlightList) {
			if (financialHighlight.getFinancialHighlightId().toString().equalsIgnoreCase(rowKey))
				return financialHighlight;
		}

		return null;
	}

	@Override
	public Object getRowKey(FinancialHighlight object) {
		return object.getFinancialHighlightId();
	}

	@Override
	public List<FinancialHighlight> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<FinancialHighlight> data = new ArrayList<FinancialHighlight>();

		// filter
		for (FinancialHighlight financialHighlight : financialHighlightList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = financialHighlight.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(financialHighlight));
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
				data.add(financialHighlight);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new FinancialHighlightSorter(sortField, sortOrder));
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


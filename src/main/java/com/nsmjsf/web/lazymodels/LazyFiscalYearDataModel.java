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

import com.nsmjsf.web.datamodels.FiscalYear;
import com.nsmjsf.web.sorters.FiscalYearSorter;

public class LazyFiscalYearDataModel extends LazyDataModel<FiscalYear> {
	private static final Log log = LogFactory
			.getLog(LazyFiscalYearDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<FiscalYear> fiscalYearList;

	public LazyFiscalYearDataModel(List<FiscalYear> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
	}

	@Override
	public FiscalYear getRowData(String rowKey) {
		for (FiscalYear fiscalYear : fiscalYearList) {
			if (fiscalYear.getFiscalYearId().toString()
					.equalsIgnoreCase(rowKey))
				return fiscalYear;
		}

		return null;
	}

	@Override
	public Object getRowKey(FiscalYear object) {
		return object.getFiscalYearId();
	}

	@Override
	public List<FiscalYear> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<FiscalYear> data = new ArrayList<FiscalYear>();

		// filter
		for (FiscalYear fiscalYear : fiscalYearList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = fiscalYear.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(fiscalYear));
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
				data.add(fiscalYear);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new FiscalYearSorter(sortField, sortOrder));
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

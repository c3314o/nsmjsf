

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

import com.nsmjsf.web.datamodels.FinancialReport;
import com.nsmjsf.web.sorters.FinancialReportSorter;

public class LazyFinancialReportDataModel extends LazyDataModel<FinancialReport> {
	private static final Log log = LogFactory
			.getLog(LazyFinancialReportDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<FinancialReport> financialReportList;

	public LazyFinancialReportDataModel(List<FinancialReport> financialReportList) {
		this.financialReportList = financialReportList;
	}

	@Override
	public FinancialReport getRowData(String rowKey) {
		for (FinancialReport financialReport : financialReportList) {
			if (financialReport.getFinancialReportId().toString().equalsIgnoreCase(rowKey))
				return financialReport;
		}

		return null;
	}

	@Override
	public Object getRowKey(FinancialReport object) {
		return object.getFinancialReportId();
	}

	@Override
	public List<FinancialReport> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<FinancialReport> data = new ArrayList<FinancialReport>();

		// filter
		for (FinancialReport financialReport : financialReportList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = financialReport.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(financialReport));
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
				data.add(financialReport);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new FinancialReportSorter(sortField, sortOrder));
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


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

import com.nsmjsf.web.datamodels.CompanyBranch;
import com.nsmjsf.web.sorters.CompanyBranchSorter;

public class LazyCompanyBranchDataModel extends LazyDataModel<CompanyBranch> {
	private static final Log log = LogFactory
			.getLog(LazyCompanyBranchDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<CompanyBranch> companyBranchList;

	public LazyCompanyBranchDataModel(List<CompanyBranch> companyBranchList) {
		this.companyBranchList = companyBranchList;
	}

	@Override
	public CompanyBranch getRowData(String rowKey) {
		for (CompanyBranch companyBranch : companyBranchList) {
			if (companyBranch.getCompanyBranchId().toString()
					.equalsIgnoreCase(rowKey))
				return companyBranch;
		}

		return null;
	}

	@Override
	public Object getRowKey(CompanyBranch object) {
		return object.getCompanyBranchId();
	}

	@Override
	public List<CompanyBranch> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<CompanyBranch> data = new ArrayList<CompanyBranch>();

		// filter
		for (CompanyBranch companyBranch : companyBranchList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = companyBranch.getClass()
								.getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(companyBranch));
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
				data.add(companyBranch);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data,
					new CompanyBranchSorter(sortField, sortOrder));
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

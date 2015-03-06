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

import com.nsmjsf.web.datamodels.Company;
import com.nsmjsf.web.sorters.CompanySorter;

public class LazyCompanyDataModel extends LazyDataModel<Company> {
	private static final Log log = LogFactory
			.getLog(LazyCompanyDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Company> companyList;

	public LazyCompanyDataModel(List<Company> companyList) {
		this.companyList = companyList;
	}

	@Override
	public Company getRowData(String rowKey) {
		for (Company company : companyList) {
			if (company.getCompanyId().toString().equalsIgnoreCase(rowKey))
				return company;
		}

		return null;
	}

	@Override
	public Object getRowKey(Company object) {
		return object.getCompanyId();
	}

	@Override
	public List<Company> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Company> data = new ArrayList<Company>();

		// filter
		for (Company company : companyList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = company.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(company));
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
				data.add(company);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new CompanySorter(sortField, sortOrder));
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



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

import com.nsmjsf.web.datamodels.CompanyDetail;
import com.nsmjsf.web.sorters.CompanyDetailSorter;

public class LazyCompanyDetailDataModel extends LazyDataModel<CompanyDetail> {
	private static final Log log = LogFactory
			.getLog(LazyCompanyDetailDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<CompanyDetail> companyDetailList;

	public LazyCompanyDetailDataModel(List<CompanyDetail> companyDetailList) {
		this.companyDetailList = companyDetailList;
	}

	@Override
	public CompanyDetail getRowData(String rowKey) {
		for (CompanyDetail companyDetail : companyDetailList) {
			if (companyDetail.getCompanyDetailId().toString().equalsIgnoreCase(rowKey))
				return companyDetail;
		}

		return null;
	}

	@Override
	public Object getRowKey(CompanyDetail object) {
		return object.getCompanyDetailId();
	}

	@Override
	public List<CompanyDetail> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<CompanyDetail> data = new ArrayList<CompanyDetail>();

		// filter
		for (CompanyDetail companyDetail : companyDetailList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = companyDetail.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(companyDetail));
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
				data.add(companyDetail);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new CompanyDetailSorter(sortField, sortOrder));
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


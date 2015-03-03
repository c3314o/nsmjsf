

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

import com.nsmjsf.web.datamodels.MapPostCompany;
import com.nsmjsf.web.sorters.MapPostCompanySorter;

public class LazyMapPostCompanyDataModel extends LazyDataModel<MapPostCompany> {
	private static final Log log = LogFactory
			.getLog(LazyMapPostCompanyDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<MapPostCompany> mapPostCompanyList;

	public LazyMapPostCompanyDataModel(List<MapPostCompany> mapPostCompanyList) {
		this.mapPostCompanyList = mapPostCompanyList;
	}

	@Override
	public MapPostCompany getRowData(String rowKey) {
		for (MapPostCompany mapPostCompany : mapPostCompanyList) {
			if (mapPostCompany.getMapPostCompanyId().toString().equalsIgnoreCase(rowKey))
				return mapPostCompany;
		}

		return null;
	}

	@Override
	public Object getRowKey(MapPostCompany object) {
		return object.getMapPostCompanyId();
	}

	@Override
	public List<MapPostCompany> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<MapPostCompany> data = new ArrayList<MapPostCompany>();

		// filter
		for (MapPostCompany mapPostCompany : mapPostCompanyList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = mapPostCompany.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(mapPostCompany));
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
				data.add(mapPostCompany);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new MapPostCompanySorter(sortField, sortOrder));
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


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

import com.nsmjsf.web.datamodels.BodType;
import com.nsmjsf.web.sorters.BodTypeSorter;

public class LazyBodTypeDataModel extends LazyDataModel<BodType> {
	private static final Log log = LogFactory
			.getLog(LazyBodTypeDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<BodType> bodTypeList;

	public LazyBodTypeDataModel(List<BodType> bodTypeList) {
		this.bodTypeList = bodTypeList;
	}

	@Override
	public BodType getRowData(String rowKey) {
		for (BodType bodType : bodTypeList) {
			if (bodType.getBodTypeId().toString().equalsIgnoreCase(rowKey))
				return bodType;
		}

		return null;
	}

	@Override
	public Object getRowKey(BodType object) {
		return object.getBodTypeId();
	}

	@Override
	public List<BodType> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<BodType> data = new ArrayList<BodType>();

		// filter
		for (BodType bodType : bodTypeList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = bodType.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(bodType));
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
				data.add(bodType);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new BodTypeSorter(sortField, sortOrder));
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

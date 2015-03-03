

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

import com.nsmjsf.web.datamodels.KeyRatio;
import com.nsmjsf.web.sorters.KeyRatioSorter;

public class LazyKeyRatioDataModel extends LazyDataModel<KeyRatio> {
	private static final Log log = LogFactory
			.getLog(LazyKeyRatioDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<KeyRatio> keyRatioList;

	public LazyKeyRatioDataModel(List<KeyRatio> keyRatioList) {
		this.keyRatioList = keyRatioList;
	}

	@Override
	public KeyRatio getRowData(String rowKey) {
		for (KeyRatio keyRatio : keyRatioList) {
			if (keyRatio.getKeyRatioId().toString().equalsIgnoreCase(rowKey))
				return keyRatio;
		}

		return null;
	}

	@Override
	public Object getRowKey(KeyRatio object) {
		return object.getKeyRatioId();
	}

	@Override
	public List<KeyRatio> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<KeyRatio> data = new ArrayList<KeyRatio>();

		// filter
		for (KeyRatio keyRatio : keyRatioList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = keyRatio.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(keyRatio));
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
				data.add(keyRatio);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new KeyRatioSorter(sortField, sortOrder));
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


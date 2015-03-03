

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

import com.nsmjsf.web.datamodels.IndexType;
import com.nsmjsf.web.sorters.IndexTypeSorter;

public class LazyIndexTypeDataModel extends LazyDataModel<IndexType> {
	private static final Log log = LogFactory
			.getLog(LazyIndexTypeDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<IndexType> indexTypeList;

	public LazyIndexTypeDataModel(List<IndexType> indexTypeList) {
		this.indexTypeList = indexTypeList;
	}

	@Override
	public IndexType getRowData(String rowKey) {
		for (IndexType indexType : indexTypeList) {
			if (indexType.getIndexTypeId().toString().equalsIgnoreCase(rowKey))
				return indexType;
		}

		return null;
	}

	@Override
	public Object getRowKey(IndexType object) {
		return object.getIndexTypeId();
	}

	@Override
	public List<IndexType> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<IndexType> data = new ArrayList<IndexType>();

		// filter
		for (IndexType indexType : indexTypeList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = indexType.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(indexType));
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
				data.add(indexType);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new IndexTypeSorter(sortField, sortOrder));
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


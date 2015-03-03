

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

import com.nsmjsf.web.datamodels.MapPostCategory;
import com.nsmjsf.web.sorters.MapPostCategorySorter;

public class LazyMapPostCategoryDataModel extends LazyDataModel<MapPostCategory> {
	private static final Log log = LogFactory
			.getLog(LazyMapPostCategoryDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<MapPostCategory> mapPostCategoryList;

	public LazyMapPostCategoryDataModel(List<MapPostCategory> mapPostCategoryList) {
		this.mapPostCategoryList = mapPostCategoryList;
	}

	@Override
	public MapPostCategory getRowData(String rowKey) {
		for (MapPostCategory mapPostCategory : mapPostCategoryList) {
			if (mapPostCategory.getMapPostCategoryId().toString().equalsIgnoreCase(rowKey))
				return mapPostCategory;
		}

		return null;
	}

	@Override
	public Object getRowKey(MapPostCategory object) {
		return object.getMapPostCategoryId();
	}

	@Override
	public List<MapPostCategory> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<MapPostCategory> data = new ArrayList<MapPostCategory>();

		// filter
		for (MapPostCategory mapPostCategory : mapPostCategoryList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = mapPostCategory.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(mapPostCategory));
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
				data.add(mapPostCategory);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new MapPostCategorySorter(sortField, sortOrder));
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


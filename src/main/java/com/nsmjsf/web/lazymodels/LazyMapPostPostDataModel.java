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

import com.nsmjsf.web.datamodels.MapPostPost;
import com.nsmjsf.web.sorters.MapPostPostSorter;

public class LazyMapPostPostDataModel extends LazyDataModel<MapPostPost> {
	private static final Log log = LogFactory
			.getLog(LazyMapPostPostDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<MapPostPost> mapPostPostList;

	public LazyMapPostPostDataModel(List<MapPostPost> mapPostPostList) {
		this.mapPostPostList = mapPostPostList;
	}

	@Override
	public MapPostPost getRowData(String rowKey) {
		for (MapPostPost mapPostPost : mapPostPostList) {
			if (mapPostPost.getMapPostPostId().toString()
					.equalsIgnoreCase(rowKey))
				return mapPostPost;
		}

		return null;
	}

	@Override
	public Object getRowKey(MapPostPost object) {
		return object.getMapPostPostId();
	}

	@Override
	public List<MapPostPost> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<MapPostPost> data = new ArrayList<MapPostPost>();

		// filter
		for (MapPostPost mapPostPost : mapPostPostList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = mapPostPost.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(mapPostPost));
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
				data.add(mapPostPost);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new MapPostPostSorter(sortField, sortOrder));
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

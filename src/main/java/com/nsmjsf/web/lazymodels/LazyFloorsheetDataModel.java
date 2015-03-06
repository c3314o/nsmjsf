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

import com.nsmjsf.web.datamodels.Floorsheet;
import com.nsmjsf.web.sorters.FloorsheetSorter;

public class LazyFloorsheetDataModel extends LazyDataModel<Floorsheet> {
	private static final Log log = LogFactory
			.getLog(LazyFloorsheetDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Floorsheet> floorsheetList;

	public LazyFloorsheetDataModel(List<Floorsheet> floorsheetList) {
		this.floorsheetList = floorsheetList;
	}

	@Override
	public Floorsheet getRowData(String rowKey) {
		for (Floorsheet floorsheet : floorsheetList) {
			if (floorsheet.getFloorsheetId().toString()
					.equalsIgnoreCase(rowKey))
				return floorsheet;
		}

		return null;
	}

	@Override
	public Object getRowKey(Floorsheet object) {
		return object.getFloorsheetId();
	}

	@Override
	public List<Floorsheet> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Floorsheet> data = new ArrayList<Floorsheet>();

		// filter
		for (Floorsheet floorsheet : floorsheetList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = floorsheet.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(floorsheet));
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
				data.add(floorsheet);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new FloorsheetSorter(sortField, sortOrder));
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

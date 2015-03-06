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

import com.nsmjsf.web.datamodels.FloorsheetDump;
import com.nsmjsf.web.sorters.FloorsheetDumpSorter;

public class LazyFloorsheetDumpDataModel extends LazyDataModel<FloorsheetDump> {
	private static final Log log = LogFactory
			.getLog(LazyFloorsheetDumpDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<FloorsheetDump> floorsheetDumpList;

	public LazyFloorsheetDumpDataModel(List<FloorsheetDump> floorsheetDumpList) {
		this.floorsheetDumpList = floorsheetDumpList;
	}

	@Override
	public FloorsheetDump getRowData(String rowKey) {
		for (FloorsheetDump floorsheetDump : floorsheetDumpList) {
			if (floorsheetDump.getFloorsheetDumpId().toString()
					.equalsIgnoreCase(rowKey))
				return floorsheetDump;
		}

		return null;
	}

	@Override
	public Object getRowKey(FloorsheetDump object) {
		return object.getFloorsheetDumpId();
	}

	@Override
	public List<FloorsheetDump> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<FloorsheetDump> data = new ArrayList<FloorsheetDump>();

		// filter
		for (FloorsheetDump floorsheetDump : floorsheetDumpList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = floorsheetDump.getClass()
								.getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(floorsheetDump));
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
				data.add(floorsheetDump);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new FloorsheetDumpSorter(sortField,
					sortOrder));
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

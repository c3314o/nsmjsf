

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

import com.nsmjsf.web.datamodels.FloorsheetHeader;
import com.nsmjsf.web.sorters.FloorsheetHeaderSorter;

public class LazyFloorsheetHeaderDataModel extends LazyDataModel<FloorsheetHeader> {
	private static final Log log = LogFactory
			.getLog(LazyFloorsheetHeaderDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<FloorsheetHeader> floorsheetHeaderList;

	public LazyFloorsheetHeaderDataModel(List<FloorsheetHeader> floorsheetHeaderList) {
		this.floorsheetHeaderList = floorsheetHeaderList;
	}

	@Override
	public FloorsheetHeader getRowData(String rowKey) {
		for (FloorsheetHeader floorsheetHeader : floorsheetHeaderList) {
			if (floorsheetHeader.getFloorsheetHeaderId().toString().equalsIgnoreCase(rowKey))
				return floorsheetHeader;
		}

		return null;
	}

	@Override
	public Object getRowKey(FloorsheetHeader object) {
		return object.getFloorsheetHeaderId();
	}

	@Override
	public List<FloorsheetHeader> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<FloorsheetHeader> data = new ArrayList<FloorsheetHeader>();

		// filter
		for (FloorsheetHeader floorsheetHeader : floorsheetHeaderList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = floorsheetHeader.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(floorsheetHeader));
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
				data.add(floorsheetHeader);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new FloorsheetHeaderSorter(sortField, sortOrder));
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


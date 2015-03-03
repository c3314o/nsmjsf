

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

import com.nsmjsf.web.datamodels.Sector;
import com.nsmjsf.web.sorters.SectorSorter;

public class LazySectorDataModel extends LazyDataModel<Sector> {
	private static final Log log = LogFactory
			.getLog(LazySectorDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Sector> sectorList;

	public LazySectorDataModel(List<Sector> sectorList) {
		this.sectorList = sectorList;
	}

	@Override
	public Sector getRowData(String rowKey) {
		for (Sector sector : sectorList) {
			if (String.valueOf(sector.getSectorId()).equalsIgnoreCase(rowKey))
				return sector;
		}

		return null;
	}

	@Override
	public Object getRowKey(Sector object) {
		return object.getSectorId();
	}

	@Override
	public List<Sector> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Sector> data = new ArrayList<Sector>();

		// filter
		for (Sector sector : sectorList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = sector.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(sector));
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
				data.add(sector);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new SectorSorter(sortField, sortOrder));
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


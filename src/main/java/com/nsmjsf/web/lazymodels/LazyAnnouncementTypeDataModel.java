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

import com.nsmjsf.web.datamodels.AnnouncementType;
import com.nsmjsf.web.sorters.AnnouncementTypeSorter;

public class LazyAnnouncementTypeDataModel extends
		LazyDataModel<AnnouncementType> {
	private static final Log log = LogFactory
			.getLog(LazyAnnouncementTypeDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<AnnouncementType> announcementTypeList;

	public LazyAnnouncementTypeDataModel(
			List<AnnouncementType> announcementTypeList) {
		this.announcementTypeList = announcementTypeList;
	}

	@Override
	public AnnouncementType getRowData(String rowKey) {
		for (AnnouncementType announcementType : announcementTypeList) {
			if (announcementType.getAnnouncementTypeId().toString()
					.equalsIgnoreCase(rowKey))
				return announcementType;
		}

		return null;
	}

	@Override
	public Object getRowKey(AnnouncementType object) {
		return object.getAnnouncementTypeId();
	}

	@Override
	public List<AnnouncementType> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<AnnouncementType> data = new ArrayList<AnnouncementType>();

		// filter
		for (AnnouncementType announcementType : announcementTypeList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = announcementType.getClass()
								.getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(announcementType));
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
				data.add(announcementType);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new AnnouncementTypeSorter(sortField,
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

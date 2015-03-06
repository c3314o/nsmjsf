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

import com.nsmjsf.web.datamodels.Notification;
import com.nsmjsf.web.sorters.NotificationSorter;

public class LazyNotificationDataModel extends LazyDataModel<Notification> {
	private static final Log log = LogFactory
			.getLog(LazyNotificationDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Notification> notificationList;

	public LazyNotificationDataModel(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}

	@Override
	public Notification getRowData(String rowKey) {
		for (Notification notification : notificationList) {
			if (notification.getNotificationId().toString()
					.equalsIgnoreCase(rowKey))
				return notification;
		}

		return null;
	}

	@Override
	public Object getRowKey(Notification object) {
		return object.getNotificationId();
	}

	@Override
	public List<Notification> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Notification> data = new ArrayList<Notification>();

		// filter
		for (Notification notification : notificationList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = notification.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(notification));
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
				data.add(notification);
			}
		}

		// sort
		if (sortField != null) {
			Collections
					.sort(data, new NotificationSorter(sortField, sortOrder));
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

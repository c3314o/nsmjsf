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

import com.nsmjsf.web.datamodels.CrawlerSettings;
import com.nsmjsf.web.sorters.CrawlerSettingsSorter;

public class LazyCrawlerSettingsDataModel extends
		LazyDataModel<CrawlerSettings> {
	private static final Log log = LogFactory
			.getLog(LazyCrawlerSettingsDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<CrawlerSettings> crawlerSettingsList;

	public LazyCrawlerSettingsDataModel(
			List<CrawlerSettings> crawlerSettingsList) {
		this.crawlerSettingsList = crawlerSettingsList;
	}

	@Override
	public CrawlerSettings getRowData(String rowKey) {
		for (CrawlerSettings crawlerSettings : crawlerSettingsList) {
			if (crawlerSettings.getCrawlerSettingsId().toString()
					.equalsIgnoreCase(rowKey))
				return crawlerSettings;
		}

		return null;
	}

	@Override
	public Object getRowKey(CrawlerSettings object) {
		return object.getCrawlerSettingsId();
	}

	@Override
	public List<CrawlerSettings> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<CrawlerSettings> data = new ArrayList<CrawlerSettings>();

		// filter
		for (CrawlerSettings crawlerSettings : crawlerSettingsList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = crawlerSettings.getClass()
								.getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(crawlerSettings));
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
				data.add(crawlerSettings);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new CrawlerSettingsSorter(sortField,
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

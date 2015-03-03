

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

import com.nsmjsf.web.datamodels.PostImage;
import com.nsmjsf.web.sorters.PostImageSorter;

public class LazyPostImageDataModel extends LazyDataModel<PostImage> {
	private static final Log log = LogFactory
			.getLog(LazyPostImageDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<PostImage> postImageList;

	public LazyPostImageDataModel(List<PostImage> postImageList) {
		this.postImageList = postImageList;
	}

	@Override
	public PostImage getRowData(String rowKey) {
		for (PostImage postImage : postImageList) {
			if (postImage.getPostImageId().toString().equalsIgnoreCase(rowKey))
				return postImage;
		}

		return null;
	}

	@Override
	public Object getRowKey(PostImage object) {
		return object.getPostImageId();
	}

	@Override
	public List<PostImage> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<PostImage> data = new ArrayList<PostImage>();

		// filter
		for (PostImage postImage : postImageList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = postImage.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(postImage));
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
				data.add(postImage);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new PostImageSorter(sortField, sortOrder));
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


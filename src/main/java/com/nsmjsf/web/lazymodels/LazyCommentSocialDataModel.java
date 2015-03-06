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

import com.nsmjsf.web.datamodels.CommentSocial;
import com.nsmjsf.web.sorters.CommentSocialSorter;

public class LazyCommentSocialDataModel extends LazyDataModel<CommentSocial> {
	private static final Log log = LogFactory
			.getLog(LazyCommentSocialDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<CommentSocial> commentSocialList;

	public LazyCommentSocialDataModel(List<CommentSocial> commentSocialList) {
		this.commentSocialList = commentSocialList;
	}

	@Override
	public CommentSocial getRowData(String rowKey) {
		for (CommentSocial commentSocial : commentSocialList) {
			if (commentSocial.getCommentSocialId().toString()
					.equalsIgnoreCase(rowKey))
				return commentSocial;
		}

		return null;
	}

	@Override
	public Object getRowKey(CommentSocial object) {
		return object.getCommentSocialId();
	}

	@Override
	public List<CommentSocial> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<CommentSocial> data = new ArrayList<CommentSocial>();

		// filter
		for (CommentSocial commentSocial : commentSocialList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = commentSocial.getClass()
								.getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(commentSocial));
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
				data.add(commentSocial);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data,
					new CommentSocialSorter(sortField, sortOrder));
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

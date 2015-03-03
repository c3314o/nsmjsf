

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

import com.nsmjsf.web.datamodels.Comment;
import com.nsmjsf.web.sorters.CommentSorter;

public class LazyCommentDataModel extends LazyDataModel<Comment> {
	private static final Log log = LogFactory
			.getLog(LazyCommentDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Comment> commentList;

	public LazyCommentDataModel(List<Comment> commentList) {
		this.commentList = commentList;
	}

	@Override
	public Comment getRowData(String rowKey) {
		for (Comment comment : commentList) {
			if (comment.getCommentId().toString().equalsIgnoreCase(rowKey))
				return comment;
		}

		return null;
	}

	@Override
	public Object getRowKey(Comment object) {
		return object.getCommentId();
	}

	@Override
	public List<Comment> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Comment> data = new ArrayList<Comment>();

		// filter
		for (Comment comment : commentList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = comment.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(comment));
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
				data.add(comment);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new CommentSorter(sortField, sortOrder));
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



package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Comment;

public class CommentWrapper {

private static final Log log = LogFactory
			.getLog(CommentWrapper.class);


	Comment comment;

	public CommentWrapper(Comment comment) {
		this.comment = comment;
	}

	public CommentWrapper() {

	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.comment.getCommentId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CommentWrapper other = (CommentWrapper) obj;
		if (!Objects.equals(this.comment.getCommentId(), other.getComment().getCommentId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.comment.toString();

	}

}


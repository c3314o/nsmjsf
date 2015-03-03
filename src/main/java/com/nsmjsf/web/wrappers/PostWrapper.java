
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Post;

public class PostWrapper {

private static final Log log = LogFactory
			.getLog(PostWrapper.class);


	Post post;

	public PostWrapper(Post post) {
		this.post = post;
	}

	public PostWrapper() {

	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.post.getPostId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PostWrapper other = (PostWrapper) obj;
		if (!Objects.equals(this.post.getPostId(), other.getPost().getPostId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.post.toString();

	}

}


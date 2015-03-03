
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.ForumThread;

public class ForumThreadWrapper {

private static final Log log = LogFactory
			.getLog(ForumThreadWrapper.class);


	ForumThread forumThread;

	public ForumThreadWrapper(ForumThread forumThread) {
		this.forumThread = forumThread;
	}

	public ForumThreadWrapper() {

	}

	public ForumThread getForumThread() {
		return forumThread;
	}

	public void setForumThread(ForumThread forumThread) {
		this.forumThread = forumThread;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.forumThread.getForumThreadId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ForumThreadWrapper other = (ForumThreadWrapper) obj;
		if (!Objects.equals(this.forumThread.getForumThreadId(), other.getForumThread().getForumThreadId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.forumThread.toString();

	}

}


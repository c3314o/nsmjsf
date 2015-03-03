
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserWatchList;

public class UserWatchListWrapper {

private static final Log log = LogFactory
			.getLog(UserWatchListWrapper.class);


	UserWatchList userWatchList;

	public UserWatchListWrapper(UserWatchList userWatchList) {
		this.userWatchList = userWatchList;
	}

	public UserWatchListWrapper() {

	}

	public UserWatchList getUserWatchList() {
		return userWatchList;
	}

	public void setUserWatchList(UserWatchList userWatchList) {
		this.userWatchList = userWatchList;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.userWatchList.getUserWatchListId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserWatchListWrapper other = (UserWatchListWrapper) obj;
		if (!Objects.equals(this.userWatchList.getUserWatchListId(), other.getUserWatchList().getUserWatchListId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.userWatchList.toString();

	}

}


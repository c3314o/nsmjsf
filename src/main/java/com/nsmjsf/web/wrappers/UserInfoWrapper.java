
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserInfo;

public class UserInfoWrapper {

private static final Log log = LogFactory
			.getLog(UserInfoWrapper.class);


	UserInfo userInfo;

	public UserInfoWrapper(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserInfoWrapper() {

	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.userInfo.getUserInfoId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserInfoWrapper other = (UserInfoWrapper) obj;
		if (!Objects.equals(this.userInfo.getUserInfoId(), other.getUserInfo().getUserInfoId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.userInfo.toString();

	}

}


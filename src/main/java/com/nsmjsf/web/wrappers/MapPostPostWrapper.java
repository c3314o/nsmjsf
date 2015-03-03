
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.MapPostPost;

public class MapPostPostWrapper {

private static final Log log = LogFactory
			.getLog(MapPostPostWrapper.class);


	MapPostPost mapPostPost;

	public MapPostPostWrapper(MapPostPost mapPostPost) {
		this.mapPostPost = mapPostPost;
	}

	public MapPostPostWrapper() {

	}

	public MapPostPost getMapPostPost() {
		return mapPostPost;
	}

	public void setMapPostPost(MapPostPost mapPostPost) {
		this.mapPostPost = mapPostPost;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.mapPostPost.getMapPostPostId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MapPostPostWrapper other = (MapPostPostWrapper) obj;
		if (!Objects.equals(this.mapPostPost.getMapPostPostId(), other.getMapPostPost().getMapPostPostId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.mapPostPost.toString();

	}

}


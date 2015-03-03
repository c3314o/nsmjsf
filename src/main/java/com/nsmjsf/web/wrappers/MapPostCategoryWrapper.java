
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.MapPostCategory;

public class MapPostCategoryWrapper {

private static final Log log = LogFactory
			.getLog(MapPostCategoryWrapper.class);


	MapPostCategory mapPostCategory;

	public MapPostCategoryWrapper(MapPostCategory mapPostCategory) {
		this.mapPostCategory = mapPostCategory;
	}

	public MapPostCategoryWrapper() {

	}

	public MapPostCategory getMapPostCategory() {
		return mapPostCategory;
	}

	public void setMapPostCategory(MapPostCategory mapPostCategory) {
		this.mapPostCategory = mapPostCategory;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.mapPostCategory.getMapPostCategoryId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MapPostCategoryWrapper other = (MapPostCategoryWrapper) obj;
		if (!Objects.equals(this.mapPostCategory.getMapPostCategoryId(), other.getMapPostCategory().getMapPostCategoryId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.mapPostCategory.toString();

	}

}


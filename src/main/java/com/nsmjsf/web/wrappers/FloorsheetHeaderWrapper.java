
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.FloorsheetHeader;

public class FloorsheetHeaderWrapper {

private static final Log log = LogFactory
			.getLog(FloorsheetHeaderWrapper.class);


	FloorsheetHeader floorsheetHeader;

	public FloorsheetHeaderWrapper(FloorsheetHeader floorsheetHeader) {
		this.floorsheetHeader = floorsheetHeader;
	}

	public FloorsheetHeaderWrapper() {

	}

	public FloorsheetHeader getFloorsheetHeader() {
		return floorsheetHeader;
	}

	public void setFloorsheetHeader(FloorsheetHeader floorsheetHeader) {
		this.floorsheetHeader = floorsheetHeader;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.floorsheetHeader.getFloorsheetHeaderId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FloorsheetHeaderWrapper other = (FloorsheetHeaderWrapper) obj;
		if (!Objects.equals(this.floorsheetHeader.getFloorsheetHeaderId(), other.getFloorsheetHeader().getFloorsheetHeaderId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.floorsheetHeader.toString();

	}

}


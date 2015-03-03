
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Floorsheet;

public class FloorsheetWrapper {

private static final Log log = LogFactory
			.getLog(FloorsheetWrapper.class);


	Floorsheet floorsheet;

	public FloorsheetWrapper(Floorsheet floorsheet) {
		this.floorsheet = floorsheet;
	}

	public FloorsheetWrapper() {

	}

	public Floorsheet getFloorsheet() {
		return floorsheet;
	}

	public void setFloorsheet(Floorsheet floorsheet) {
		this.floorsheet = floorsheet;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.floorsheet.getFloorsheetId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FloorsheetWrapper other = (FloorsheetWrapper) obj;
		if (!Objects.equals(this.floorsheet.getFloorsheetId(), other.getFloorsheet().getFloorsheetId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.floorsheet.toString();

	}

}


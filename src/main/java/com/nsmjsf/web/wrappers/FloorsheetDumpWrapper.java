
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.FloorsheetDump;

public class FloorsheetDumpWrapper {

private static final Log log = LogFactory
			.getLog(FloorsheetDumpWrapper.class);


	FloorsheetDump floorsheetDump;

	public FloorsheetDumpWrapper(FloorsheetDump floorsheetDump) {
		this.floorsheetDump = floorsheetDump;
	}

	public FloorsheetDumpWrapper() {

	}

	public FloorsheetDump getFloorsheetDump() {
		return floorsheetDump;
	}

	public void setFloorsheetDump(FloorsheetDump floorsheetDump) {
		this.floorsheetDump = floorsheetDump;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.floorsheetDump.getFloorsheetDumpId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FloorsheetDumpWrapper other = (FloorsheetDumpWrapper) obj;
		if (!Objects.equals(this.floorsheetDump.getFloorsheetDumpId(), other.getFloorsheetDump().getFloorsheetDumpId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.floorsheetDump.toString();

	}

}


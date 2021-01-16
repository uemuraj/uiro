package dev.adzuki.win32;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

/**
 * @see https://docs.microsoft.com/en-us/windows/win32/api/tcpmib/ns-tcpmib-mib_tcptable
 */
@FieldOrder({ "dwNumEntries", "table" })
public class MIB_TCPTABLE extends Structure {

	public int dwNumEntries;

	public MIB_TCPROW[] table;

	// https://stackoverflow.com/questions/65285060/jna-how-to-specify-a-variable-length-0-array-in-a-structure

	public MIB_TCPTABLE() {
	}

	public MIB_TCPTABLE(Pointer p) {
		super(p);
	}

	@Override
	protected void ensureAllocated() {

		if (dwNumEntries == 0) {
			table = new MIB_TCPROW[1];
		}

		super.ensureAllocated();

		if (dwNumEntries == 0) {
			table = new MIB_TCPROW[0];
		}
	}

	@Override
	protected Object readField(StructField structField) {

		if ("table".equals(structField.name)) {

			table = new MIB_TCPROW[dwNumEntries];

			if (dwNumEntries == 0) {
				return null;
			}
		}

		return super.readField(structField);
	}

	@Override
	protected void writeField(StructField structField) {

		if ("table".equals(structField.name) && dwNumEntries == 0) {
			return;
		}

		super.writeField(structField);
	}
}

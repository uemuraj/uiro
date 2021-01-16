package dev.adzuki.win32;

import com.sun.jna.Memory;
import com.sun.jna.platform.win32.WinError;
import com.sun.jna.ptr.IntByReference;

/**
 * JNA に関する詳細を隠すラッパークラス。
 */
public class IPHelper {

	private IPHelperAPI iphelper = IPHelperAPI.INSTANCE;

	MIB_TCPROW[] getTcpTable() {

		IntByReference size = new IntByReference();

		if (iphelper.GetTcpTable(null, size, true) == WinError.ERROR_INSUFFICIENT_BUFFER) {

			MIB_TCPTABLE tcpTable = new MIB_TCPTABLE(new Memory(size.getValue()));

			if (iphelper.GetTcpTable(tcpTable, size, true) == WinError.NO_ERROR) {
				return tcpTable.table;
			}
		}

		return new MIB_TCPROW[0];
	}
}

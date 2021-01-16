package dev.adzuki.win32;

import static org.junit.Assert.assertEquals;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.sun.jna.Memory;
import com.sun.jna.platform.win32.WinError;
import com.sun.jna.ptr.IntByReference;

public class IPHelperAPITest {

	IPHelperAPI iphelper = IPHelperAPI.INSTANCE;

	@Test
	public void GetTcpTable() {

		IntByReference size = new IntByReference();
		assertEquals(WinError.ERROR_INSUFFICIENT_BUFFER, iphelper.GetTcpTable(null, size, true));
		assertThat(size.getValue(), greaterThan(0));

		MIB_TCPTABLE tcpTable = new MIB_TCPTABLE(new Memory(size.getValue()));
		assertEquals(WinError.NO_ERROR, iphelper.GetTcpTable(tcpTable, size, true));
		assertThat(tcpTable.table.length, greaterThan(0));

		for (MIB_TCPROW row : tcpTable.table) {
			System.out.printf("TCP\t%s\t%d\t%s\t%d\t%s\n", row.getLocalAddress(), row.getLocalPort(),
					row.getRemoteAddress(), row.getRemotePort(), row.getState());
		}
	}
}

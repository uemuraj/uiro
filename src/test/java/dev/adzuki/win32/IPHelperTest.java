package dev.adzuki.win32;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

import org.junit.Test;

public class IPHelperTest {

	IPHelperAPI iphelper = IPHelperAPI.INSTANCE;

	@Test
	public void GetTcpTable() {

		MIB_TCPROW[] tcpTable = new IPHelper().getTcpTable();

		assertThat(tcpTable.length, greaterThan(0));

		for (MIB_TCPROW row : tcpTable) {
			System.out.printf("TCP\t%s\t%d\t%s\t%d\t%s\n", row.getLocalAddress(), row.getLocalPort(),
					row.getRemoteAddress(), row.getRemotePort(), row.getState());
		}
	}
}

package dev.adzuki.win32;

import java.net.InetAddress;
import java.nio.ByteBuffer;

import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

/**
 * @see https://docs.microsoft.com/en-us/windows/win32/api/tcpmib/ns-tcpmib-mib_tcprow_lh
 */
@FieldOrder({ "dwState", "dwLocalAddr", "dwLocalPort", "dwRemoteAddr", "dwRemotePort" })
public class MIB_TCPROW extends Structure {

	public int dwState;

	public int dwLocalAddr;

	public int dwLocalPort;

	public int dwRemoteAddr;

	public int dwRemotePort;

	private static MIB_TCP_STATE[] states = MIB_TCP_STATE.values();

	public MIB_TCP_STATE getState() {
		return states[dwState - 1];
	}

	private static InetAddress getAddress(int addr) {
		try {
			// https://stackoverflow.com/questions/2183240/java-integer-to-byte-array
			return InetAddress.getByAddress(ByteBuffer.allocate(4).putInt(addr).array());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public InetAddress getLocalAddress() {
		return getAddress(Integer.reverseBytes(dwLocalAddr));
	}

	public InetAddress getRemoteAddress() {
		return getAddress(Integer.reverseBytes(dwRemoteAddr));
	}

	public int getLocalPort() {
		return Short.toUnsignedInt(Short.reverseBytes((short) dwLocalPort));
	}

	public int getRemotePort() {
		return Short.toUnsignedInt(Short.reverseBytes((short) dwRemotePort));
	}
}

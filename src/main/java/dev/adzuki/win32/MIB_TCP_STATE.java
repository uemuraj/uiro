package dev.adzuki.win32;

/**
 * TCP接続の状態を表す。
 * 
 * @see https://docs.microsoft.com/en-us/windows/win32/api/tcpmib/ns-tcpmib-mib_tcprow_lh
 */
public enum MIB_TCP_STATE {
	CLOSED, LISTEN, SYN_SENT, SYN_RCVD, ESTABLISHED, FIN_WAIT1, FIN_WAIT2, CLOSE_WAIT, CLOSING, LAST_ACK, TIME_WAIT,
	DELETE_TCB;
}

package dev.adzuki.win32;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.W32APIOptions;

/**
 * {@link com.sun.jna.platform.win32.IPHlpAPI} では定義されていない API を使用するために作成。
 * 
 * @see https://docs.microsoft.com/en-us/windows/win32/api/iphlpapi/nf-iphlpapi-gettcptable
 */
interface IPHelperAPI extends Library {

	IPHelperAPI INSTANCE = Native.load("IPHlpAPI", IPHelperAPI.class, W32APIOptions.DEFAULT_OPTIONS);

	int GetTcpTable(MIB_TCPTABLE tcpTable, IntByReference size, boolean order);
}

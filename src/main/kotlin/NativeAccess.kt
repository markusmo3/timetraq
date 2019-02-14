
import com.sun.jna.Native
import com.sun.jna.platform.win32.*

var WINEVENT_OUTOFCONTEXT = 0
var WINEVENT_SKIPOWNPROCESS = 2

fun main() {
    val k32 = Native.load("kernel32", Kernel32::class.java) as Kernel32
    val u32 = Native.load("user32", User32::class.java) as User32

    val setWinEventHook = u32.SetWinEventHook(0x0003, 0x0007, null, callback,
        0, 0, WINEVENT_OUTOFCONTEXT or WINEVENT_SKIPOWNPROCESS)
    val lastError = Native.getLastError()
    println("LastError: $lastError")
    if (lastError != 0) {
        return
    }
    try {
        Thread.sleep(10 * 1000)
    } finally {
        u32.UnhookWinEvent(setWinEventHook)
    }
}

object callback : WinUser.WinEventProc {
    override fun callback(
        hWinEventHook: WinNT.HANDLE?,
        event: WinDef.DWORD?,
        hwnd: WinDef.HWND?,
        idObject: WinDef.LONG?,
        idChild: WinDef.LONG?,
        dwEventThread: WinDef.DWORD?,
        dwmsEventTime: WinDef.DWORD?
    ) {
        println("HookProc $event")
    }

}

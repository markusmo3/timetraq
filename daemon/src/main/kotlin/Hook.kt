
import MessageGetterThread.lib
import com.sun.jna.Native
import com.sun.jna.platform.win32.*

var WINEVENT_OUTOFCONTEXT = 0
var WINEVENT_SKIPOWNPROCESS = 2

fun main() {
    val lib = User32.INSTANCE
    val u32 = Native.load("user32", User32::class.java) as User32

    val hookHandle = u32.SetWinEventHook(
        WinEventConst.S_FOREGROUND, WinEventConst.S_DESKTOPSWITCH,
        null, WinEventProcCallback,
        0, 0, WINEVENT_OUTOFCONTEXT or WINEVENT_SKIPOWNPROCESS)

    var result: Int
    val msg = WinUser.MSG()
    while(true) {
        result = lib.GetMessage(msg, null,
            WinEventConst.S_FOREGROUND, WinEventConst.S_DESKTOPSWITCH)
        if (result <= 0) {
            System.err.println("error in get message")
            break
        } else {
            lib.TranslateMessage(msg)
            lib.DispatchMessage(msg)
        }
    }
    lib.UnhookWinEvent(hookHandle)
}

object WinEventProcCallback : WinUser.WinEventProc {
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

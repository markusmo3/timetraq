import com.sun.jna.Native;
import com.sun.jna.platform.win32.*;

public class HookListener {
    static WinUser.WinEventProc hook = new WinUser.WinEventProc() {
        @Override
        public void callback(WinNT.HANDLE hWinEventHook, WinDef.DWORD event, WinDef.HWND hwnd, WinDef.LONG idObject, WinDef.LONG idChild, WinDef.DWORD dwEventThread, WinDef.DWORD dwmsEventTime) {
            System.out.println("Test");
        }
    };

    public static void main(String[] args) throws InterruptedException {
        User32 u32 = User32.INSTANCE;
        WinDef.HMODULE hMod = Kernel32.INSTANCE.GetModuleHandle(null);

        WinNT.HANDLE handle = u32.SetWinEventHook(0, 30, null, hook, 0, 0, 0);
        int lastError = Native.getLastError();
        System.out.println("LastError: " + lastError);
        if (lastError != 0) {
            return;
        }
        try {
            Thread.sleep(5 * 1000);
            System.out.println(hook.toString());
        } finally {
            u32.UnhookWinEvent(handle);
        }
    }

}

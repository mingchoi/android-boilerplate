package world.fallen.android_boilerplate.utils;

import android.os.Build;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class DeviceInfoHelper {
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : all) {
                if (!ni.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] bytes = ni.getHardwareAddress();
                if (bytes == null) {
                    return "";
                }

                StringBuilder str = new StringBuilder();
                for (byte b : bytes) {
                    str.append(String.format("%02X:", b));
                }

                if (str.length() > 0) {
                    str.deleteCharAt(str.length() - 1);
                }
                return str.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    public static String getDeviceName() {
        if (Build.MODEL.startsWith(Build.MANUFACTURER)) {
            return Build.MODEL;
        }
        return Build.MANUFACTURER + "/" + Build.MODEL;
    }
}

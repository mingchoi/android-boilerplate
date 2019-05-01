package world.fallen.android_boilerplate.utils;

public class URLHelper {
    public static String join(String host, String path) {
        String endWith = host.substring(host.length() - 1);
        String startWith = path.substring(0, 1);
        if (!endWith.equals("/") && !startWith.equals("/")) {
            return host + "/" + path;
        } else if (endWith.equals("/") && startWith.equals("/")) {
            return host + path.substring(1);
        }
        return host + path;
    }
}

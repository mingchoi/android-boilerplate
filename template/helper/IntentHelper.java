package {{.Print "Package"}}.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class IntentHelper {
    public static void OpenWebBrowser(Context ctx, String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        ctx.startActivity(intent);
    }
}

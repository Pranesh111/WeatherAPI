package atrue.pranesh.creditmantri_weatherapi.helper;

import android.content.Context;
import android.content.SharedPreferences;



public class PreferencesCredit implements PreferenceKeys {



    public static String getStringValue(Context context, String key) {
        String temp = "";
        if (context != null && context.getSharedPreferences(KEY, Context.MODE_PRIVATE) != null) {
            SharedPreferences temp_pref = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
            temp = temp_pref.getString(key, "");
        }
        return temp;
    }

    public static void setStringValue(Context ctx, String key, String value) {
        if (ctx != null) {
            SharedPreferences.Editor edt = ctx.getSharedPreferences(KEY, Context.MODE_PRIVATE).edit();
            edt.putString(key, value);
            edt.commit();
        }
    }
}
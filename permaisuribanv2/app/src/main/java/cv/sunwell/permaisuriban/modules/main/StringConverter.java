package cv.sunwell.permaisuriban.modules.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import cv.sunwell.permaisuriban.modules.auth.AuthActivity;

public class StringConverter {

    public static String removeQuotation(String line){
        line = line.replace("\"", "");
        return line;
    }
}

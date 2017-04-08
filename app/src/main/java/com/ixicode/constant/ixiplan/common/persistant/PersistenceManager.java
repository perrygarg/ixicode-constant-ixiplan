package com.ixicode.constant.ixiplan.common.persistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;



public class PersistenceManager
{
    private static SharedPreferences getSharedPrerence(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void clearPreference(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * Persist token.
     * @param token
     */
    public static void persistToken(String token, Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SharedPreferenceKeys.TOKEN_API_AUTH, token);
        editor.apply();
    }

    /**
     * Get saved token.
     * @return
     */
    public static String getToken(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String token = sharedPreferences.getString(SharedPreferenceKeys.TOKEN_API_AUTH, null);
        return token;
    }

    public static boolean isToShowWalkthrough(Context context)
    {
        SharedPreferences sharedPreferences = getSharedPrerence(context);
        boolean isShowOverlay = sharedPreferences.getBoolean(SharedPreferenceKeys.PREF_IS_TO_SHOW_WALKTHROUGH, true); //Default is True, Show Overlay for the first time only
        return isShowOverlay;
    }

    public static void persistIsToShowWalkthrough(boolean isShow, Context context)
    {
        SharedPreferences sharedPreferences = getSharedPrerence(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(SharedPreferenceKeys.PREF_IS_TO_SHOW_WALKTHROUGH, isShow);
        editor.apply();
    }
}

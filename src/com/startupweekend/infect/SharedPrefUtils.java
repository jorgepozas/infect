/**
 * 
 */
package com.startupweekend.infect;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author Deep
 * 
 *         Class provides for app shared pref
 */
public class SharedPrefUtils {
	private static final String SHARED_PREF_APP_FIRST_OPEN = "firstopen";
	
	private static String SHARED_PREF_FILE = "infect_prefs";

	private static long getSharedPrefLongData(Context context, String key) {
		SharedPreferences userAcountPreference = context.getSharedPreferences(
		        SHARED_PREF_FILE, Context.MODE_PRIVATE);
		return userAcountPreference.getLong(key, 0l);

	}

	private static void setSharedPrefLongData(Context context, String key,
			long value) {
		SharedPreferences appInstallInfoSharedPref = context
				.getSharedPreferences(SHARED_PREF_FILE, context.MODE_PRIVATE);
		Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
		appInstallInfoEditor.putLong(key, value);
		appInstallInfoEditor.commit();
	}

	private static boolean getSharedPrefBooleanData(Context context, String key) {
		SharedPreferences userAcountPreference = context.getSharedPreferences(
		        SHARED_PREF_FILE, Context.MODE_PRIVATE);
		return userAcountPreference.getBoolean(key, false);
	}

	private static void setSharedPrefBooleanData(Context context, String key,
			boolean value) {
		SharedPreferences appInstallInfoSharedPref = context
				.getSharedPreferences(SHARED_PREF_FILE, context.MODE_PRIVATE);
		Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
		appInstallInfoEditor.putBoolean(key, value);
		appInstallInfoEditor.commit();
	}

	private static int getSharedPrefIntData(Context context, String key) {
		SharedPreferences userAcountPreference = context.getSharedPreferences(
		        SHARED_PREF_FILE, Context.MODE_PRIVATE);
		return userAcountPreference.getInt(key, 0);
	}

	private static void setSharedPrefIntData(Context context, String key,
			int value) {
		SharedPreferences appInstallInfoSharedPref = context
				.getSharedPreferences(SHARED_PREF_FILE, context.MODE_PRIVATE);
		Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
		appInstallInfoEditor.putInt(key, value);
		appInstallInfoEditor.commit();
	}

	private static String getSharedPrefStringData(Context context, String key) {
		SharedPreferences userAcountPreference = context.getSharedPreferences(
		        SHARED_PREF_FILE, Context.MODE_PRIVATE);
		return userAcountPreference.getString(key, "");
	}

	private static void setSharedPreStringData(Context context, String key,
			String value) {
		SharedPreferences appInstallInfoSharedPref = context
				.getSharedPreferences(SHARED_PREF_FILE, context.MODE_PRIVATE);
		Editor appInstallInfoEditor = appInstallInfoSharedPref.edit();
		appInstallInfoEditor.putString(key, value);
		appInstallInfoEditor.commit();
	}

	public static String getUserId(Context context) {
		return getSharedPrefStringData(context,
				"userId");
	}

	public static void setUserId(Context context, String userId) {
		setSharedPreStringData(context, "userId",
		        userId);
	}
}

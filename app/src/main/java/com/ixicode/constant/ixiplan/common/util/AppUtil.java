package com.ixicode.constant.ixiplan.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;

import java.util.Collection;

public class AppUtil
{
	/************** String/Collection/Integer Related *************/

	/**
	 * Check String Empty
	 * 
	 * @param stringRes
	 * @return
	 */
	//Is String Empty
	public static boolean isStringEmpty(String stringRes)
	{
		return (stringRes == null || stringRes.trim().isEmpty());
	}

	//Check Collection for Empty
	public static boolean isCollectionEmpty(Collection<? extends Object> collection)
	{
		return (collection == null || collection.isEmpty());
	}

    //Check Collection for Empty
    public static<T> boolean isArrayEmpty(Object[] objectArray)
    {
        return objectArray == null || objectArray.length == 0;
    }

//	//Check String For Null Or Empty
//	public static boolean isStringArrayEmpty(String array[])
//	{
//		if(array == null || array.length == 0)
//		{
//			return true;
//		}
//
//		return false;
//	}

	//Set String Not NULL
	public static String setStringNotNull(String stringRes)
	{
		if(stringRes == null || stringRes.isEmpty())
		{
			return "";
		}
		
		//Convert Non_breaking (ASCI 160) to Simple Space. trim() does'nt handle it.
		stringRes = stringRes.replace(String.valueOf((char) 160), " ");

		String tempString = stringRes.trim();
		return tempString;
	}

	//Check for NULL - Integer
	public static Integer setIntegerNotNull(Integer value)
	{
		return ((value == null) ? 0 : value);
	}


	/*************** Check Android Version *************/

	/**
	 * To check if Android version is 4.1 and above
	 * @return True if Android version is 4.1 and above else False
	 */
	public static boolean isCurrentVersionJellyBeanAndAbove()
	{
		try
		{
			int currentApiVersion = Build.VERSION.SDK_INT;

			return (currentApiVersion >= Build.VERSION_CODES.JELLY_BEAN);

		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * To check if Android version is 4.1 and above
	 * @return True if Android version is 4.1 and above else False
	 */
	public static boolean isCurrentVersionKitkatAndAbove()
	{
		try
		{
			int currentApiVersion = Build.VERSION.SDK_INT;

			return (currentApiVersion >= Build.VERSION_CODES.KITKAT);
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * To check if Android version is 4.0 and above
	 * @return True if Android version is 4.0 and above else False
	 */
	public static boolean isCurrentVersionICSAndAbove()
	{
		try
		{
			int currentapiVersion = Build.VERSION.SDK_INT;

			return (currentapiVersion >= Build.VERSION_CODES.ICE_CREAM_SANDWICH);

		}
		catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * To check if Android version is 3.0 and above
	 * @return True if Android version is 3.0 and above else False
	 */
	public static boolean isCurrentVersionHoneycombAndAbove()
	{
		try
		{
			int currentapiVersion = Build.VERSION.SDK_INT;

			if (currentapiVersion >= Build.VERSION_CODES.HONEYCOMB)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public static boolean IsGreaterThanApi19()
	{
		int sdkCode = Build.VERSION.SDK_INT;

		if(Build.VERSION_CODES.KITKAT <= sdkCode)
		{
			return true;
		}

		return false;
	}

	public static boolean isGreaterThanApi21()
	{
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
			return true;

		return false;
	}

	public static boolean isGreaterThanApi23()
	{
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
			return true;

		return false;
	}

	public static boolean isGreaterThanApi24()
	{
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
			return true;

		return false;
	}

	/**
	 * To check if Android version is 3.0 and above
	 * @return True if Android version is 3.0 and above else False
	 */
	public static boolean isCurrentVersionHoneycombmr2AndAbove()
	{
		try
		{
			int currentapiVersion = Build.VERSION.SDK_INT;

			if (currentapiVersion >= Build.VERSION_CODES.HONEYCOMB_MR2)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e)
		{
			return false;
		}
	}

	/***
	 * Close cursor 
	 * @param cursor
	 */
	public static void closeCursor(Cursor cursor)
	{
		if (cursor != null && !cursor.isClosed())
		{
			try 
			{
				cursor.close();
			} 
			catch (Exception e) 
			{
				//Do Nothing
				e.printStackTrace();
			}
		}
	}

	/**
	 * Get App Version
	 */
	public static int getAppVersionCode(Context context)
	{
		//String appVersionName = "";
		int appVersionCode = 0;

		try
		{
			PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			//appVersionName = pInfo.versionName;
			appVersionCode = pInfo.versionCode;
		}
		catch (PackageManager.NameNotFoundException e)
		{
			//Consume
		}
		return appVersionCode;
	}

	/**
	 * Get Device Id - Android Secure Settings ID
	 * @return
	 */
	public static String getDeviceId(Context context)
	{
		String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

		return deviceId;
	}

	/** Returns the consumer friendly device name */
	public static String getDeviceName()
	{
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;

		return manufacturer + " " + model;
	}


	/** ************************ Deprecated Methods Handling ******************/

	@TargetApi(Build.VERSION_CODES.M)
	public static int getColor(int colorId, Context context)
	{
		int color = 0;

		if(AppUtil.isGreaterThanApi23())
		{
			color = context.getResources().getColor(colorId, null);
		}
		else
		{
			color = context.getResources().getColor(colorId);
		}

		return color;
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public static Drawable getDrawable(int resourceId, Context context)
	{
		Drawable drawable = null;

		if(AppUtil.isGreaterThanApi21())
		{
			drawable = context.getResources().getDrawable(resourceId, null);
		}
		else
		{
			drawable = context.getResources().getDrawable(resourceId);
		}

		return drawable;
	}

	/**
	 * This method provides information whether the Internet is available on device or not
	 * @return true if internet available otherwise false
	 */
	public static boolean isInternetAvailableOnDevice(Context context)
	{
		boolean isConnected = false;

		try
		{
			ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

			if (connectivity != null)
			{
				int numberOfNetworkInfo = 0;
				NetworkInfo[] networkInfo = connectivity.getAllNetworkInfo();

				if (networkInfo != null)
				{
					numberOfNetworkInfo = networkInfo.length;
					for (int i = 0; i < numberOfNetworkInfo; i++)
					{
						if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
						{
							isConnected = true;
							break;
						}
					}
				}
			}
		}
		catch (Exception exception)
		{
			// Consume it
		}

		return isConnected;
	}

	public static boolean isCursorEmpty(Cursor cursor)
	{
		return (cursor == null || cursor.isClosed() || cursor.getCount() == 0);
	}

	public static String getCursorString(Cursor cursor, String name)
	{
		if(isCursorEmpty(cursor))
		{
			return "";
		}
		else
		{
			return cursor.getString(cursor.getColumnIndex(name));
		}
	}

	public static int getCursorInt(Cursor cursor, String name)
	{
		if(isCursorEmpty(cursor))
		{
			return 0;
		}
		else
		{
			return cursor.getInt(cursor.getColumnIndex(name));
		}
	}

	public static float getCursorFloat(Cursor cursor, String name)
	{
		if(isCursorEmpty(cursor))
		{
			return 0;
		}
		else
		{
			return cursor.getFloat(cursor.getColumnIndex(name));
		}
	}
}

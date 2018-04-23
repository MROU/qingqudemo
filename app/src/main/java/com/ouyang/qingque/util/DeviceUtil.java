package com.ouyang.qingque.util;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.io.File;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Log输出
 * @author 欧阳
 *
 */
public class DeviceUtil {

	public static final int NETWORK_CLASS_UNKNOWN = 0;
	public static final int NETWORK_WIFI = 1;
	public static final int NETWORK_CLASS_2_G = 2;
	public static final int NETWORK_CLASS_3_G = 3;
	public static final int NETWORK_CLASS_4_G = 4;

	public DeviceUtil() {
	}

	public static boolean existSDCard() {
		return Environment.getExternalStorageState().equals("mounted");
	}

	public static String getLocalIPAddress() {
		try {
			Enumeration ex = NetworkInterface.getNetworkInterfaces();

			while(ex.hasMoreElements()) {
				NetworkInterface intf = (NetworkInterface)ex.nextElement();
				Enumeration enumIpAddr = intf.getInetAddresses();

				while(enumIpAddr.hasMoreElements()) {
					InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
					if(!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}

			return "0.0.0.0";
		} catch (SocketException var4) {
			return "0.0.0.0";
		}
	}

	public static String getExternalStorageDirectory() {
		Map map = System.getenv();
		String[] values = new String[map.values().size()];
		map.values().toArray(values);
		String path = values[values.length - 1];
		return path.startsWith("/mnt/") && !Environment.getExternalStorageDirectory().getAbsolutePath().equals(path)?path:null;
	}

	public static long getAvailaleSize() {
		if(!existSDCard()) {
			return 0L;
		} else {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = (long)stat.getBlockSize();
			long availableBlocks = (long)stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		}
	}

	public static long getAllSize() {
		if(!existSDCard()) {
			return 0L;
		} else {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = (long)stat.getBlockSize();
			long availableBlocks = (long)stat.getBlockCount();
			return availableBlocks * blockSize;
		}
	}













	public static String getLatestCameraPicture(Context context) {
		if(!existSDCard()) {
			return null;
		} else {
			String[] projection = new String[]{"_id", "_data", "bucket_display_name", "datetaken", "mime_type"};
			Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, (String)null, (String[])null, "datetaken DESC");
			if(cursor.moveToFirst()) {
				String path = cursor.getString(1);
				return path;
			} else {
				return null;
			}
		}
	}

	public static DisplayMetrics getScreenPix(Activity activity) {
		DisplayMetrics displaysMetrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(displaysMetrics);
		return displaysMetrics;
	}


	public static List<String> getAppPackageNamelist(Context context) {
		ArrayList packList = new ArrayList();
		PackageManager pm = context.getPackageManager();
		List packinfos = pm.getInstalledPackages(0);
		Iterator var4 = packinfos.iterator();

		while(var4.hasNext()) {
			PackageInfo packinfo = (PackageInfo)var4.next();
			String packname = packinfo.packageName;
			packList.add(packname);
		}

		return packList;
	}

	public static boolean isAppInstall(Context context, String packageName) {
		PackageManager packageManager = context.getPackageManager();
		List packageInfos = packageManager.getInstalledPackages(0);
		ArrayList packageNames = new ArrayList();
		if(packageInfos != null) {
			for(int i = 0; i < packageInfos.size(); ++i) {
				String packName = ((PackageInfo)packageInfos.get(i)).packageName;
				packageNames.add(packName);
			}
		}

		return packageNames.contains(packageName);
	}

	public static int dip2px(Context context, float dipValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale + 0.5F);
	}

	public static int px2dip(Context context, float pxValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int)(pxValue / scale + 0.5F);
	}



	public static int getStatusBarHeight(Context context) {
		int height = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if(resourceId > 0) {
			height = context.getResources().getDimensionPixelSize(resourceId);
		}

		return height;
	}

	public static int getNavigationBarHeight(Context context) {
		int height = 0;
		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		if(resourceId > 0) {
			height = resources.getDimensionPixelSize(resourceId);
		}

		return height;
	}

	








	public static void callPhone(Context context, String phoneNumber) {
		context.startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber)));
	}

	public static void callDial(Context context, String phoneNumber) {
		context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phoneNumber)));
	}

	public static void sendSms(Context context, String phoneNumber, String content) {
		Uri uri = Uri.parse("smsto:" + (TextUtils.isEmpty(phoneNumber)?"":phoneNumber));
		Intent intent = new Intent("android.intent.action.SENDTO", uri);
		intent.putExtra("sms_body", TextUtils.isEmpty(content)?"":content);
		context.startActivity(intent);
	}


   
}

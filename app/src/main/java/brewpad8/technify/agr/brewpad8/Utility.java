package brewpad8.technify.agr.brewpad8;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Utility {
	
	

	public static String GetColumnValue(Cursor cur, String ColumnName) {
		try {
			return cur.getString(cur.getColumnIndex(ColumnName));
		} catch (Exception ex) {
			return "";
		}
	}
 	
 //	public static String GetString(EditText source) {
//		try {
//			return GetString(source.getText().toString());
//		} catch (Exception ex) {
//			return "";
//		}
//	}
//
//	public static String GetString(TextView source) {
//		try {
//			return GetString(source.getText().toString());
//		} catch (Exception ex) {
//			return "";
//		}
//	}
//
//	public static String GetString(Object source) {
//		try {
//			return GetString(source.toString());
//		} catch (Exception ex) {
//			return "";
//		}
//	}
	
	public static void ShowMessageBox(Context cont, String msg, float txtsize)
	{
		Toast toast = Toast.makeText(cont, msg, Toast.LENGTH_SHORT);
		LinearLayout toastLayout = (LinearLayout)toast.getView();
		TextView toastTV = (TextView) toastLayout.getChildAt(0);
		toastTV.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtsize);
		// toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
		toast.show();
	}
	/*
	public static void ShowMessageBox(Context cont, String msg) {
		float txt = 0;
		
		//ShowMessageBox(cont, msg, txt);
	}*/
}

package brewpad8.technify.agr.brewpad8;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TestAdapter { 
    protected static final String TAG = "DataAdapter"; 
 
    private final Context mContext; 
    private SQLiteDatabase mDb; 
    private BrewPadDB mDbHelper; 
 
    public TestAdapter(Context context)  
    { 
        this.mContext = context; 
        mDbHelper = new BrewPadDB(mContext); 
    } 
 
    public TestAdapter createDatabase() throws SQLException  
    { 
    	//System.out.println("Create DB");
        try  
        { 
            mDbHelper.createDataBase(); 
        }  
        catch (IOException mIOException)  
        { 
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase"); 
            throw new Error("UnableToCreateDatabase"); 
        } 
        return this; 
    } 
 
    public TestAdapter open() throws SQLException  
    { 
    	//System.out.println("Open DB");
        try  
        { 
        	//System.out.println("Open DB try");
            mDbHelper.openDataBase(); 
            mDbHelper.close(); 
            //mDb = mDbHelper.getReadableDatabase();
            mDb = mDbHelper.getWritableDatabase();
        }  
        catch (SQLException mSQLException)  
        { 
        	//System.out.println("Open DB catch");
            Log.e(TAG, "open >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
        //System.out.println(this);
        return this; 
    } 
 
    public void close()  
    { 
        mDbHelper.close(); 
    } 
 
     public Cursor getTestData() 
     { 
    	 
         try 
         { 
             String sql ="SELECT name FROM sqlite_master WHERE type='table'"; 
             //System.out.println("getTestData");
             Cursor mCur = mDb.rawQuery(sql, null); 
             //System.out.println(mCur);
             if (mCur!=null) 
             { 
                (mCur).moveToNext(); 
             } 
             return mCur; 
         } 
         catch (SQLException mSQLException)  
         { 
             Log.e(TAG, "getTestData >>"+ mSQLException.toString()); 
             throw mSQLException; 
         } 
     }
     
     public Cursor getFieldData(String sql) 
     { 
    	 
         try 
         { 
             //String sql ="SELECT name FROM sqlite_master WHERE type='table'"; 
             System.out.println("getTestData");
             Cursor mCur = mDb.rawQuery(sql, null); 
             if (mCur!=null) 
             { 
                (mCur).moveToFirst(); 
             }
             return mCur; 
         } 
         catch (SQLException mSQLException)  
         { 
        	 //System.out.println("Error");
             Log.e(TAG, "getTestData >>"+ mSQLException.toString()); 
             throw mSQLException; 
         } 
     }
     

 	public boolean SaveDataa(ArrayList<String> al_qry) 
 	{
 		//System.out.println("Execute Query"+al_qry);
 		try
 		{
 			/*ContentValues cv = new ContentValues();
 			cv.put("Name", name);
 			cv.put("Email", email);
 			
 			mDb.insert("Employees", null, cv);*/
 			
 			//String qry = arg;
 			/*ArrayList<String> arl = al_qry;
 			for(int i = 0; !arl.isEmpty();i++)
 			{
 				String s = arl.get(i);*/
 			Iterator ir = al_qry.iterator();
 			while (ir.hasNext())
 			{
 				String s = (String) ir.next();
 				mDb.execSQL(s);

 			}
 			Log.d("SaveDataa", "informationsaved");
 			
 			return true;
 			
 		}
 		catch(Exception ex)
 		{
 			System.out.println("Save Failed");
 			Log.d("SaveDataa", ex.toString());
 			return false;
 		}
 	}
 	public boolean UpdateData(ArrayList<String> al_qry)
 	{
 		System.out.println("TestAdapter===>"+al_qry);
 		
 		try
 		{
 			Iterator ir = al_qry.iterator();
 			while (ir.hasNext())
 			{
 				String s = (String) ir.next();
 				mDb.execSQL(s);

 			}
 			Log.d("Update Success", "informationUpdated");
 			
 			return true;
 		}
 		catch(Exception ex)
 		{
 			System.out.println("Update Failed");
 			Log.d("UpdateDataa", ex.toString());
 			return false;
 		}
 	}
     

}

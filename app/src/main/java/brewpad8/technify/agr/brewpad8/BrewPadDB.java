package brewpad8.technify.agr.brewpad8;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BrewPadDB extends SQLiteOpenHelper {
	
	private static String TAG = "BrewPadDB"; // Tag just for LogCat window
	private static String DB_PATH = "";
	//private static String DB_NAME ="dbinsertBrewpad8";// Database name 
	//private static String DB_NAME ="Brewpad8";
	private static String DB_NAME ="NewBrewpad8";
	private SQLiteDatabase myDb;  
	private final Context mContext; 
	 
	public BrewPadDB(Context context)  
	{ 
	    super(context, DB_NAME, null, 1);// 1? its Database Version 
	    DB_PATH = "/data/data/" + context.getPackageName() + "/databases/"; 
	    this.mContext = context; 
	}    
	 
	public void createDataBase() throws IOException 
	{ 
	    //If database not exists copy it from the assets 
	    boolean myDbExist = checkDataBase(); 
	    if(!myDbExist) 
	    { 
	        this.getReadableDatabase(); 
	        this.close(); 
	        try  
	        { 
	            //Copy the database from assests 
	            copyDataBase(); 
	            Log.e(TAG, "createDatabase database created");
	        }  
	        catch (IOException mIOException)  
	        { 
	            throw new Error("ErrorCopyingDataBase"); 
	        } 
	        
	    } 
	} 
	    //Check that the database exists here: /data/data/your package/databases/Da Name 
	    private boolean checkDataBase() 
	    { 
	        File dbFile = new File(DB_PATH + DB_NAME); 
	        Log.v("dbFile", dbFile + "   "+ dbFile.exists()); 
	        return dbFile.exists(); 
	    } 
	 
	    //Copy the database from assets 
	    private void copyDataBase() throws IOException 
	    { 
	        InputStream mInput = mContext.getAssets().open(DB_NAME); 
	        String outFileName = DB_PATH + DB_NAME; 
	        OutputStream mOutput = new FileOutputStream(outFileName); 
	        byte[] mBuffer = new byte[1024]; 
	        int mLength; 
	        while ((mLength = mInput.read(mBuffer))>0) 
	        { 
	            mOutput.write(mBuffer, 0, mLength); 
	        } 
	        mOutput.flush(); 
	        mOutput.close(); 
	        mInput.close(); 
	    } 
	 
	    //Open the database, so we can query it 
	    public boolean openDataBase() throws SQLException 
	    { 
	        String mPath = DB_PATH + DB_NAME; 
	        //Log.v("mPath", mPath); 
	        myDb = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY); 
	        //myDb = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS); 
	        return myDb != null; 
	    } 
	 
	    @Override 
	    public synchronized void close()  
	    { 
	        if(myDb != null) 
	            myDb.close(); 
	        super.close(); 
	    }


	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}

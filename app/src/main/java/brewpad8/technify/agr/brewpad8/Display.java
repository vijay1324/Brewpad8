package brewpad8.technify.agr.brewpad8;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Display extends Activity{

	SQLiteDatabase db;
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displaydb);
		lv = (ListView) findViewById(R.id.listView1);
		displayList();
		/*ArrayList<String> arrTblNames = new ArrayList<String>();
		Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table", null);
		//Cursor c = db.rawQuery("Show tables", null);

		    if (c.moveToFirst()) {
		    	System.out.println("2");
		        while ( !c.isAfterLast() ) {
		            arrTblNames.add( c.getString( c.getColumnIndex("name")) );
		            c.moveToNext();
		        }
		    }
		    
		    ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrTblNames);
		lv.setAdapter(aa);    */
		
	}
	
	
	/*public void disp(ArrayAdapter<String> aa) {
		// TODO Auto-generated method stub
		
		lv.setAdapter(aa);
		
	}*/

	
	public void displayList()
	{
		TestAdapter mDbHelper = new TestAdapter(this);         
    	mDbHelper.createDatabase();       
    	mDbHelper.open(); 
    	 //System.out.println("Cursor");
    	Cursor testdata = mDbHelper.getTestData(); 
    	//System.out.println("SQLite Cursor=========>"+testdata.toString());
    	
    	ArrayList<String> arrTblNames = new ArrayList<String>();
		
		    if (testdata.moveToFirst()) {
		    	//System.out.println("2");
		        while ( !testdata.isAfterLast() ) {
		        	//System.out.println(testdata);
		            arrTblNames.add( testdata.getString( testdata.getColumnIndex("name")).toUpperCase() );
		            testdata.moveToNext();
		        }
		    }
		    //System.out.println("testdata======>"+arrTblNames);
		    ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrTblNames);
		    mDbHelper.close();
		    lv.setAdapter(aa);
		    
		   /* lv.getAdapter();
		    setContentView(lv);*/
		    lv.setOnItemClickListener(new OnItemClickListener() {
		    	
				@Override
				public void onItemClick(AdapterView<?> parent, View arg1,
						int position, long id) {
					// TODO Auto-generated method stub
					int pos1 = position + 1;
					//Toast.makeText(Display.this, "" + pos1, Toast.LENGTH_SHORT).show();
					final String item = (String) parent.getItemAtPosition(position);
					//System.out.println("Item Display=================>"+item);
					Intent i = new Intent(Display.this, EditTableLayout.class);
					i.putExtra("Item", item);
					startActivity(i);
					Display.this.finish();
				}
			});
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent back = new Intent(Display.this, IndexPage.class);
		startActivity(back);
		Display.this.finish();
	}
}

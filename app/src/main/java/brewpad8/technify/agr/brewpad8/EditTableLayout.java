package brewpad8.technify.agr.brewpad8;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

public class EditTableLayout extends Activity{

	ArrayList<String> arrTblField = new ArrayList<String>();
	ArrayList<String> head = new ArrayList<String>();
	ArrayList<String> arrRowField = new ArrayList<String>();
	ArrayList<String> chngRowField = new ArrayList<String>();
	ArrayList<EditText> allEds = new ArrayList<EditText>();
	ArrayList<String> al_qry=new ArrayList<String>();
	ArrayList<String> spinnerArray = new ArrayList<>();
	String tblname, rh, getqry, whereValue, batchid, batchrefno, finalbatchno;
	Intent geti;
	EditText et;
	Iterator itf;
	String qry = "";
	Calendar myCalendar = Calendar.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edittable);
		geti = getIntent();
		arrTblField = geti.getStringArrayListExtra("arrTblField");
		arrRowField = geti.getStringArrayListExtra("arrRowField");
		tblname = geti.getStringExtra("TblName");
		getqry = geti.getStringExtra("Query");
		head = arrTblField;
		TableLayout tbl = new TableLayout(this);
		tbl.setLayoutParams(new LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
		tbl.setGravity(Gravity.CENTER_HORIZONTAL);
		LinearLayout lv = (LinearLayout) findViewById(R.id.llv1);
		TextView tvh = new TextView(this);
		tvh.setText("Update");
		tvh.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.headtextzize));
		tvh.setTextColor(Color.GRAY);
		tvh.setTypeface(Typeface.DEFAULT, Typeface.BOLD_ITALIC);
		tvh.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		tvh.setGravity(Gravity.CENTER);
		lv.addView(tvh);
		
		itf = arrTblField.iterator();
		Iterator irf = arrRowField.iterator();
		int count = 0;
		while (itf.hasNext())
		{
			TableRow tr = new TableRow(this);
			tr.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));

				String tf = (String) itf.next();
				String rf = (String) irf.next();
				
				TextView tv = new TextView(this);
				tv.setText(tf);
				tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
				tv.setId(10 + count);
				tv.setPadding(10, 5, 5, 5);

				et = new EditText(this);
				et.setText(rf);
				et.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
				et.setEnabled(false);
				if(tf.equalsIgnoreCase("tankname"))
				{
					
				}else
				allEds.add(et);
				et.setId(60 + count);
				et.setPadding(10, 5, 5, 5);

				if(tf.equalsIgnoreCase("constartdate"))
				{
					et.setCursorVisible(false);
					final int iddd = 60 + count;
					final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

					    @Override
					    public void onDateSet(DatePicker view, int year, int monthOfYear,
					            int dayOfMonth) {
					        myCalendar.set(Calendar.YEAR, year);
					        myCalendar.set(Calendar.MONTH, monthOfYear);
					        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
					        updateDate();
					    }

						private void updateDate() {
							EditText etdate = (EditText) findViewById(iddd);
							String myFormat = "MM-dd-yyyy"; //In which you need put here
							SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
							etdate.setText(sdf.format(myCalendar.getTime()));
							etdate.setCursorVisible(false);
							etdate.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
							
						}

					};
					
					et.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							new DatePickerDialog(EditTableLayout.this, date, myCalendar
				                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
				                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
							 
						}
					});
					tr.addView(tv, 0);
					tr.addView(et, 1);
				}else if(tf.equalsIgnoreCase("tankname"))
				{
					final EditText etspin = new EditText(EditTableLayout.this);
					etspin.setVisibility(View.GONE);
					String sltqry = "select tankname from am_tanklist";
					updateLabel(sltqry);
					Spinner spinner = new Spinner(this);
				    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,R.layout.my_spinner, spinnerArray);
				    spinner.setAdapter(spinnerArrayAdapter);
				    spinner.setId(110 + count);
				    System.out.println("spinner.getSelectedItem().toString()>>>>"+spinner.getSelectedItem().toString());
				    int pos = spinnerArrayAdapter.getPosition(et.getText().toString());
				    spinner.setSelection(pos);
				    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							etspin.setText(arg0.getSelectedItem().toString());
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
						}
					});
				    System.out.println("=================="+etspin.getText().toString());
				    allEds.add(etspin);
				    System.out.println("spinner.getSelectedItem().toString()<<<<<"+spinner.getSelectedItem().toString());
				    
				    tr.addView(tv, 0);
				    tr.addView(spinner, 1);
				    
				}else if(tf.equalsIgnoreCase("conColorValue") || tf.equalsIgnoreCase("colorvalue")) {

                }else
				{
					tr.addView(tv, 0);
					tr.addView(et, 1);
				}
				tbl.addView(tr);
				count++;
				
				if(tf.equalsIgnoreCase("lineid"))
				{
                    et.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
					whereValue = "lineid = " + et.getText().toString();
				}else if(tf.equalsIgnoreCase("batchid"))
				{
                    et.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
					batchid = "batchid = " + et.getText().toString();
				}else if(tf.equalsIgnoreCase("batchrefno"))
				{
                    et.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
					batchrefno = "batchrefno = '" + et.getText().toString() +"'";
				}else if(tf.equalsIgnoreCase("finalbatchno"))
				{
                    et.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
					finalbatchno = "finalbatchno = '" + et.getText().toString()+"'";
				}else
					et.setEnabled(true);
		}
		lv.addView(tbl);
		Button update = new Button(this);
		update.setGravity(Gravity.CENTER);
		update.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.headtextzize));
		update.setText("Update");
		update.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		lv.addView(update);
		
				update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				System.out.println("Before-+-+-+-+"+chngRowField);
				chngRowField.clear();
				Intent intent = getIntent();
			    finish();
			    startActivity(intent);
				for(int i=0; i < allEds.size(); i++){
				    chngRowField.add(allEds.get(i).getText().toString());
				}
				Iterator hd = head.iterator();
				Iterator crf = chngRowField.iterator();
				String rf = null ;
				ArrayList<String> cv = new ArrayList<String>();
				int gid = 0;
				while(crf.hasNext())
				{
					
					rf = "'"+(String) crf.next()+"'";
					if(gid != 0)
					rh = ", "+ (String) hd.next();
					else
						rh = (String) hd.next();
					 cv.add(rh+" = "+rf);
					 gid++;
				}
				Iterator cvi = cv.iterator();
				String newField = "";
				int chk = 0 ;
				while(cvi.hasNext())
				{
					if(chk == 0)
					newField = newField + cvi.next();
					else
						newField = newField + cvi.next();
					chk++;
				}
				String where = batchid + " AND " + batchrefno + " AND " + finalbatchno;
				if(tblname.equalsIgnoreCase("am_tanklist"))
					qry = " update "+ tblname+ " set "+ newField + " where " + whereValue ;
				else
				qry = " update "+ tblname+ " set "+ newField + " where " + where ;
				al_qry=new ArrayList<>();
				al_qry.add(qry);
				
				UpdateData(null);
			}
		});
	}
	
	private ArrayList<String> updateLabel(String sltqry) {

		TestAdapter mDbHelper = new TestAdapter(this);         
    	mDbHelper.createDatabase();       
    	mDbHelper.open(); 

    	Cursor ti = mDbHelper.getFieldData(sltqry);
    	int count = 0;
    	while(ti.moveToNext())
    	{
    		if(count==0)
    			ti.moveToFirst();
    		spinnerArray.add(ti.getString(0));
    	count++;
    	}
    	return spinnerArray;
    	
	    }
	
	public void UpdateData(View v)
    {
	TestAdapter mDbHelper = new TestAdapter(this);         
	mDbHelper.createDatabase();       
	mDbHelper.open(); 
	if(mDbHelper.SaveDataa(al_qry))
	{
		Utility.ShowMessageBox(this,"Data Updated.", getResources().getDimension(R.dimen.textsize));
		System.out.println("Data Updated.");
		Intent bck = new Intent(EditTableLayout.this, Output.class);
		bck.putStringArrayListExtra("arrTblField", arrTblField);
		bck.putExtra("TblName", tblname);
		bck.putExtra("Query", getqry);
		startActivity(bck);
		EditTableLayout.this.finish();
	}
	else
	{
		Utility.ShowMessageBox(this,"OOPS try again!", getResources().getDimension(R.dimen.textsize));
	}
}

	@Override
	public void onBackPressed() {
		Intent bck = new Intent(EditTableLayout.this, Output.class);
		bck.putStringArrayListExtra("arrTblField", arrTblField);
		bck.putExtra("TblName", tblname);
		bck.putExtra("Query", getqry);
		startActivity(bck);
		EditTableLayout.this.finish();
	}
	
	
}

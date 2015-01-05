package brewpad8.technify.agr.brewpad8;

import java.util.ArrayList;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.TextView;

public class IndexPage extends Activity{

	ArrayList<String> arrTblField = new ArrayList<String>();
	String TblField, qrey;
	Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.indexpage);
		
	}

	public void imgClick(View v)
	{
		/*Intent i = new Intent(IndexPage.this, Display.class);
		startActivity(i); //  brewpadstatus,status,packaging,lineid,tankname,capacity,fermentation,conditioning
		IndexPage.this.finish();*/
		TblField = "brewpadstatus,status,packaging,lineid,tankname,capacity,fermentation,conditioning";
		qrey = "select  brewpadstatus,status,packaging,lineid,tankname,capacity,fermentation,conditioning from am_tanklist";
		strnToken(TblField);
		i = new Intent(this, Output.class);
	    i.putStringArrayListExtra("arrTblField", arrTblField);
	    i.putExtra("Query", qrey);
	    i.putExtra("TblName", "am_tanklist");
	    startActivity(i);
	    IndexPage.this.finish();
		
	}
	
	public void brewStatus(View v)
	{
		TblField = "batchid,batchrefno,finalbatchno,tankname,beerabbrev,contankname,allocatedbeer,batchplannedstartdate,constartdate,contransferdate,colorvalue,status,constatus,concolorvalue";
		qrey = "select  batchid,batchrefno,finalbatchno,tankname,beerabbrev,contankname,allocatedbeer,batchplannedstartdate,constartdate,contransferdate,colorvalue,status,constatus,concolorvalue from produce_conditionheader where status!=5";
		strnToken(TblField);
		i = new Intent(this, BStatus.class);
	    i.putStringArrayListExtra("arrTblField", arrTblField);
	    i.putExtra("Query", qrey);
	    i.putExtra("TblName", "produce_conditionheader");
	    startActivity(i);
	    IndexPage.this.finish();
	}
	
	public void brewview(View v)
	{
		TblField = "batchid,batchrefno,,buttonstatusfinalbatchno,constartdate,beerabbrev,tankname,conplannedvolume";
		qrey = "select batchid,batchrefno,buttonstatus,finalbatchno,constartdate,beerabbrev,tankname,conplannedvolume from produce_conditionheader where constatus=0";
		strnToken(TblField);
		i = new Intent(this, Brew.class);
	    i.putStringArrayListExtra("arrTblField", arrTblField);
	    i.putExtra("Query", qrey);
	    i.putExtra("TblName", "produce_conditionheader");
	    startActivity(i);
	    IndexPage.this.finish();
	}
	
	
	public void ferment(View v)
	{
		TblField = "batchid,buttonstatus,batchrefno,finalbatchno,constartdate,beerabbrev,tankname,conplannedvolume,flowmeter1";
		qrey = "select batchid,buttonstatus,batchrefno,finalbatchno,constartdate,beerabbrev,tankname,conplannedvolume,flowmeter1 from produce_conditionheader where constatus=0";
		strnToken(TblField);
		i = new Intent(this, Output.class);
	    i.putStringArrayListExtra("arrTblField", arrTblField);
	    i.putExtra("Query", qrey);
	    i.putExtra("TblName", "produce_conditionheader");
	    startActivity(i);
	    IndexPage.this.finish();
	}
	
	public void condition(View v)
	{
		TblField = "batchid,buttonstatus,batchrefno,finalbatchno,beerabbrev,allocatedbeer,tankname,contankname,conplannedvolume,constartdate,contransferdate,beerabbrev";
		qrey = "select batchid,buttonstatus,batchrefno,finalbatchno,beerabbrev,allocatedbeer,tankname,contankname,conplannedvolume,constartdate,contransferdate,beerabbrev from produce_conditionheader where constatus=1";
		strnToken(TblField);
		i = new Intent(this, Output.class);
	    i.putStringArrayListExtra("arrTblField", arrTblField);
	    i.putExtra("Query", qrey);
	    i.putExtra("TblName", "produce_conditionheader");
	    startActivity(i);
	    IndexPage.this.finish();
	}
	
	public void transfer(View v)
	{
		TblField = "batchid,buttonstatus,batchrefno,finalbatchno,beerabbrev,allocatedbeer,tankname,contankname,flowmeter2,constartdate,contransferdate";
		qrey = "select batchid,buttonstatus,batchrefno,finalbatchno,beerabbrev,allocatedbeer,tankname,contankname,flowmeter2,constartdate,contransferdate from produce_conditionheader where constatus=2";
		strnToken(TblField);
		i = new Intent(this, Output.class);
	    i.putStringArrayListExtra("arrTblField", arrTblField);
	    i.putExtra("Query", qrey);
	    i.putExtra("TblName", "produce_conditionheader");
	    startActivity(i);
	    IndexPage.this.finish();
	}
	
	public void brewPackage(View v)
	{
		TblField = "batchid,buttonstatus,batchrefno,finalbatchno,packedvolume,tankname,flowmeter2,contransferdate,batchplannedfinshdate,pendingvolume";
		qrey = "select batchid,buttonstatus,batchrefno,finalbatchno,packedvolume,tankname,flowmeter2,contransferdate,batchplannedfinshdate,pendingvolume from produce_conditionheader where constatus=3";
		strnToken(TblField);
		i = new Intent(this, Output.class);
	    i.putStringArrayListExtra("arrTblField", arrTblField);
	    i.putExtra("Query", qrey);
	    i.putExtra("TblName", "produce_conditionheader");
	    startActivity(i);
	    IndexPage.this.finish();
	}
	
	public ArrayList<String> strnToken(String field)
	{
		String Head = field.toUpperCase();
		StringTokenizer st = new StringTokenizer(Head,",");
		arrTblField = new ArrayList<String>();
		for (int i = 0;st.hasMoreTokens();i++)
		{
			arrTblField.add(st.nextToken(","));
		}
		return arrTblField;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		ContextThemeWrapper cw = new ContextThemeWrapper( this, R.style.AlertDialogTheme );
		AlertDialog.Builder adb = new AlertDialog.Builder(cw);
		TextView titleTv = new TextView(IndexPage.this);
		TextView msg = new TextView(IndexPage.this);
		titleTv.setText("   Exit");
		titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.headtextzize));
		titleTv.setTextColor(Color.RED);
		adb.setCustomTitle(titleTv);
		msg.setText(" Do you want Exit");
		msg.setTextColor(Color.GREEN);
		msg.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.headtextzize));
		adb.setView(msg);
		adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				IndexPage.super.onBackPressed();
				
			}
		});
		adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		adb.show();
	}
	
}

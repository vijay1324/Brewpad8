package brewpad8.technify.agr.brewpad8;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Output extends Activity{

    TableLayout tl;
    SQLiteDatabase db;
    String sql, tblname;
    //Intent i;
    ArrayList<String> arrTblField = new ArrayList<String>();
    ArrayList<String> arrRowField = new ArrayList<String>();
    int talrwlnt = 0;
    AlertDialog.Builder adb;
    Intent nxt;
    TextView label;
    int indexTag;
    //boolean mIsLargeLayout;


    //float txtsize = getResources().getDimension(R.dimen.textsize);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.output);
        //mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);
        tl = (TableLayout) findViewById(R.id.tabview);
		/*ScrollView sv = (ScrollView) findViewById(R.id.ScrollView1);
		sv.scrollBy(2, sv.getBottom());*/
        Intent i = getIntent();
        arrTblField = i.getStringArrayListExtra("arrTblField");
        sql = i.getStringExtra("Query");
        tblname = i.getStringExtra("TblName");
        System.out.println("arrTblField===>"+arrTblField);
        System.out.println("sql===>"+sql);

        //System.out.println("Income Adapter======>"+arrTblField);

        Iterator ir = arrTblField.iterator();
        int count = 0;

        TableRow tr_head = new TableRow(this);
        tr_head.setEnabled(false);
        tr_head.setId(10);
        tr_head.setBackgroundColor(Color.BLACK);
        tr_head.setLayoutParams(new LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));




        int color = 0;
        while (ir.hasNext())
        {
            String s = (String) ir.next();

            label = new TextView(this);
            label.setBackgroundColor(Color.TRANSPARENT);
            label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
            label.setId(20 + count);
            label.setTag(10+indexTag);
            ++indexTag;
            label.setText(s);
            if(count == 0)
                label.setVisibility(View.GONE);
            label.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
		        /*if(color % 2 == 0)
		        label.setTextColor(Color.argb(250, 130, 20, 113));
		        else*/
            label.setTextColor(Color.WHITE);
            label.setPadding(10, 5, 5, 5);
            tr_head.addView(label);// add the column to the table row here
            count++;
            color++;
        }


        tl.addView(tr_head, new TableLayout.LayoutParams(
                LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT));



        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        //String sql = exeQry ;
        //String sql = "Select * from "+item;
        Cursor ti = mDbHelper.getFieldData(sql);
        //Cursor ti = db.rawQuery(sql, null);

        //System.out.println("Cursor String=======>"+ti.toString());
        //TableRow tr = null;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.my_spinner, arrTblField);
        final int btnstat = adapter.getPosition("BUTTONSTATUS");
        final int conColorValue = adapter.getPosition("CONCOLORVALUE");
        final int colorvalue = adapter.getPosition("COLORVALUE");
        int idcount = 0 ;
        int rowcolor = 0;
        while (ti.moveToNext())
        {

            if(idcount == 0)
                ti.moveToFirst();
            TableRow tr = new TableRow(this);
             for (int sdata = 0 ; sdata < arrTblField.size(); sdata++)
            {
                //System.out.println("Size=======>"+arrTblField.size());
                String data = ti.getString(sdata);
                if (data == null)
                {
                    data = "null";
                }
                if(rowcolor%2!=0)
                {
                    //System.out.println("setBackgroundColor"+rowcolor);
                    tr.setBackgroundColor(Color.rgb(208, 216, 217));
                }else
                    tr.setBackgroundColor(Color.WHITE);
                tr.setId(100+idcount);
                tr.setLayoutParams(new LayoutParams(
                        LayoutParams.FILL_PARENT,
                        LayoutParams.WRAP_CONTENT));
                if(sdata == btnstat)
                {
                    //TextView border = border();
                    System.out.println("Button Status == " + data);
                    ImageButton tblBtn = new ImageButton(this);
                    tblBtn.setId(200 + count);
                    tblBtn.setImageResource(R.drawable.greenbtn);
                    tblBtn.setScaleType(ScaleType.FIT_CENTER);
                    tblBtn.setBackgroundColor(Color.TRANSPARENT);
                    System.out.println("Test222");
                    //tr.addView(border);
                    tr.addView(tblBtn);
                }else if(sdata == conColorValue || sdata == colorvalue){

                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200+count);
                    label.setTag(10+indexTag);
                    ++indexTag;
                    System.out.println("{{{{{{{{{{{{{{{{}}}}}}}}}"+data);
                    try {
                        //label.setBackgroundColor();
                        String colorr = data.replaceFirst("0x","#ff");
                        label.setBackgroundColor(Color.parseColor(colorr));
                        label.setText(data);
                        label.setTextColor(Color.parseColor(colorr));

                    } catch (IllegalArgumentException e) {
                        label.setText(data);
                        Log.d("UnKnown Color Value :: ", ""+e);
                        System.out.println("UnKnown Color Value :: "+ data + e);
                    }
                    if(sdata == 0)
                        label.setVisibility(View.GONE);
                    label.setPadding(10, 5, 5, 5);
	    		    /*if (sdata%2==0)
	    		        label.setTextColor(Color.argb(250, 130, 20, 113));
	    		    else*/
                    //label.setTextColor(Color.BLACK);
                    tr.addView(label);

                }else{
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200+count);
                    label.setTag(10+indexTag);
                    ++indexTag;
                    label.setText(data);
                    if(sdata == 0)
                        label.setVisibility(View.GONE);
	    		    label.setPadding(10, 5, 5, 5);
	    		    /*if (sdata%2==0)
	    		        label.setTextColor(Color.argb(250, 130, 20, 113));
	    		    else*/
                    label.setTextColor(Color.BLACK);
                    tr.addView(label);
                    //System.out.println(data);

                }

                talrwlnt++;
                idcount++;
            }
            tl.setClickable(true);
            tl.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            rowcolor++;

            //idcount++;
        }
        ContextThemeWrapper cw = new ContextThemeWrapper( this, R.style.AlertDialogTheme );
        adb = new AlertDialog.Builder(cw);
        for(int c = 0; c < tl.getChildCount(); c++)
        {
            final TableRow showRow = (TableRow) tl.getChildAt(c);


            for (int r = 0; r<showRow.getChildCount(); r++)
            {
                if(btnstat != r)
                {
                    TextView editTv = (TextView) showRow.getChildAt(r);
                    System.out.println("Column Index" + r);
                }
            }



            showRow.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(final View v) {
                    // TODO Auto-generated method stub
                    TextView msg = new TextView(Output.this);
                    msg.setText(" Do you want Edit");
                    msg.setTextColor(Color.GREEN);
                    msg.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    final TextView titleTv = new TextView(getApplicationContext());
                    titleTv.setText("   Edit");
                    titleTv.setTextColor(Color.RED);
                    titleTv.setTextAppearance(getApplicationContext(), Typeface.BOLD_ITALIC);
                    titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.headtextzize));
                    adb.setCustomTitle(titleTv);
                    adb.setView(msg);
                    //adb.setMessage("Do you want Edit");

                    adb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub

                            //showRow.setBackgroundResource(drawable.selector_row);
                            TableRow t = (TableRow) v;
                            System.out.println("OnClick Row");
                            arrRowField.clear();
                            for (int i = 0; i< showRow.getChildCount(); i++)
                            {
                                if(btnstat != i)
                                {
                                    TextView tv = (TextView) t.getChildAt(i);
                                    System.out.println("TV==========>"+tv.getText().toString());
                                    arrRowField.add(tv.getText().toString());

                                }else
                                {
										/*TextView tv = (TextView) t.getChildAt(i);
										System.out.println("TV==========>"+tv.getText().toString());*/
                                    arrRowField.add("0");
                                }
                            }
                            System.out.println("arrRowField=====> "+ arrRowField);

                            nxt = new Intent(Output.this, EditTableLayout.class);
                            nxt.putStringArrayListExtra("arrTblField", arrTblField);
                            nxt.putStringArrayListExtra("arrRowField", arrRowField);
                            nxt.putExtra("TblName", tblname);
                            nxt.putExtra("Query", sql);
                            startActivity(nxt);
                            Output.this.finish();

                        }
                    });



                    adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub

                        }
                    });



                    //return alert;

                    adb.show();

                    return false;
                }
            });

            showRow.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    TextView tv = new TextView(Output.this);

//					showRow.setBackgroundResource(drawable.selector_row);
					Utility.ShowMessageBox(Output.this,"Long press to Edit.", getResources().getDimension(R.dimen.textsize));

                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        Intent back = new Intent(Output.this, IndexPage.class);
        startActivity(back);
        Output.this.finish();
    }

}

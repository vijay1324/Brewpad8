package brewpad8.technify.agr.brewpad8;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;


public class BStatus extends ActionBarActivity {

    ArrayList<String> arrTblField = new ArrayList<String>();
    String sql, tblname;
    TextView label;
    int constatus1, status1;
    String ferment, conditioning, transferred, packaging, colorvalue1, concolorvalue1, ferm, cond, tran,pack ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bstatus);
        TableLayout tl = (TableLayout) findViewById(R.id.tbllo);
        Intent i = getIntent();
        arrTblField = i.getStringArrayListExtra("arrTblField");
        sql = i.getStringExtra("Query");
        tblname = i.getStringExtra("TblName");

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(3, 0, 0, 0);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.my_spinner, arrTblField);
        /*final int finalbatchno = adapter.getPosition("FINALBATCHNO");
        final int tankname = adapter.getPosition("TANKNAME");
        final int beerabbrev = adapter.getPosition("BEERABBREV");
        final int contankname = adapter.getPosition("CONTANKNAME");*/
        final int colorvalue = adapter.getPosition("COLORVALUE");
        final int status = adapter.getPosition("STATUS");
        final int constatus = adapter.getPosition("CONSTATUS");
        final int concolorvalue = adapter.getPosition("CONCOLORVALUE");

        System.out.println("colorvalue Pos = "+ colorvalue);
        System.out.println("status pos = "+ status);
        System.out.println("constatus Pos = "+constatus);
        System.out.println("concolorvalue Pos = "+concolorvalue);

        TableRow tr= new TableRow(this);
        tr.setId(152425);
        tr.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        tr.setBackgroundColor(Color.WHITE);
        String[] head = {"BatchID","BatchrefNum","Batch#","F/Tank","Input Beer", "C/Tank", "Output Beer", "Brew Dt", "Condition Dt", "Transfer Dt", "B/Tank", "Ferment", "Conditioning", "Transferred", "Packaging" };
        for(int h=0; h<15 ;h++) {
            label = new TextView(this);
            if(h ==0 || h == 1)
                label.setVisibility(View.GONE);
            label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
            label.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            label.setBackgroundColor(Color.BLACK);
            label.setSingleLine();
            label.setLayoutParams(params);
            label.setPadding(10,0,10,0);
            label.setTextColor(Color.WHITE);
            label.setText(head[h]);
            tr.addView(label);
        }

        tl.addView(tr);

        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor ti = mDbHelper.getFieldData(sql);

        int count = 0;
        int idcount = 0 ;
        int rowcolor = 0;
        while (ti.moveToNext()) {

            if (idcount == 0)
                ti.moveToFirst();
            tr = new TableRow(this);
            tr.setId(100+idcount);
            tr.setBackgroundColor(Color.BLACK);
            for (int sdata = 0; sdata < 14; sdata++) {
                String data = ti.getString(sdata);
                if (data == null)
                    data = "null";
                if(sdata == 0)
                {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    label.setText(data);
                    label.setVisibility(View.GONE);
                    label.setPadding(8,0,8,0);
                    label.setTextColor(Color.BLACK);
                    tr.addView(label);
                }else if(sdata == 1)
                {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    label.setText(data);
                    label.setVisibility(View.GONE);
                    label.setPadding(8,0,8,0);
                    label.setTextColor(Color.BLACK);
                    tr.addView(label);
                }else if(sdata == 2)
                {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if(rowcolor%2!=0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8,0,8,0);
                    tr.addView(label);

                }else if(sdata == 3)
                {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if(rowcolor%2!=0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8,0,8,0);
                    tr.addView(label);

                }else if(sdata == 4)
                {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200+count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if(rowcolor%2!=0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8,0,8,0);
                    tr.addView(label);

                }else if(sdata == 5)
                {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200+count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if(rowcolor%2!=0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8,0,8,0);
                    tr.addView(label);

                }else if(sdata == 6)
                {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200+count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if(rowcolor%2!=0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8,0,8,0);
                    tr.addView(label);

                }else if(sdata == 7)
                {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200+count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if(rowcolor%2!=0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8,0,8,0);
                    tr.addView(label);

                }else if(sdata == 8)
                {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200+count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if(rowcolor%2!=0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8,0,8,0);
                    tr.addView(label);

                }else if(sdata == 9)
                {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200+count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if(rowcolor%2!=0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8,0,8,0);
                    tr.addView(label);

                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200+count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if(rowcolor%2!=0) {
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                        label.setTextColor(Color.rgb(208, 216, 217));
                    }
                    else {
                        label.setBackgroundColor(Color.WHITE);
                        label.setTextColor(Color.WHITE);
                    }
                    label.setText(data);
                    label.setPadding(8,0,8,0);
                    tr.addView(label);

                }else if(sdata == constatus)
                {
                    constatus1 = Integer.parseInt(data);
                    System.out.println("else if(sdata == constatus)" +constatus1);
                }else if(sdata == status)
                {
                    status1 = Integer.parseInt(data);
                    System.out.println("else if(sdata == status)"+status1);
                }else if(sdata == colorvalue) {
                    colorvalue1 = data;
                    System.out.println("else if(sdata == colorvalue)"+colorvalue1);
                }else if(sdata == concolorvalue){
                    concolorvalue1 = data;
                    System.out.println("else if(sdata == concolorvalue)"+concolorvalue1);

                    if (constatus1 == 1 && status1 == 3 ) // 1, 3
                    {
                        System.out.println("if ((constatus1==1)&&(status1==3))");

                    }
                    else if (constatus1 == 2 && status1 == 3 ) // 2, 3
                    {
                        System.out.println("else if ((constatus1==2)&&(status1==3))");
                        ferment = colorvalue1;
                        ferm = ferment.replace("0x","#ff");
                        // conditioning = concolorvalue;
                    }
                    else if ((constatus1 == 3 && status1 == 4 ) || status1 == 4 ) // 3, 4 ,4
                    {
                        System.out.println("else if (((constatus1==3)&&(status1==4))||(status1==4))");
                        ferment = colorvalue1;
                        ferm = ferment.replace("0x","#ff");
                        conditioning = concolorvalue1;
                        cond = conditioning.replace("0x","#ff");
                        //   transferred = concolorvalue;
                    }
                    else if (constatus1 == 4 && status1 == 5) // 4, 5 // 0, 1
                    {
                        System.out.println("else if ((constatus1==4)&&(status1==5))");
                        ferment = colorvalue1;
                        ferm = ferment.replace("0x","#ff");
                        conditioning = concolorvalue1;
                        cond = conditioning.replace("0x","#ff");
                        transferred = concolorvalue1;
                        tran = transferred.replace("0x","#ff");
                        //  packaging = concolorvalue;
                    }
                    else if (status1 == 6) // 6
                    {
                        System.out.println("else if (status1==6)");
                        ferment = colorvalue1;
                        ferm = ferment.replace("0x","#ff");
                        conditioning = concolorvalue1;
                        cond = conditioning.replace("0x","#ff");
                        transferred = concolorvalue1;
                        tran = transferred.replace("0x","#ff");
                        packaging = concolorvalue1;
                        pack = packaging.replace("0x","#ff");
                    }

                    System.out.println("ferment = "+ferment +" : conditioning = "+conditioning +" : transferred = "+transferred);
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    System.out.println("ferm :: "+ ferm);
                    try {
                        label.setBackgroundColor(Color.parseColor(ferm));
                    }catch (Exception e)
                    {
                        if(rowcolor%2!=0)
                            label.setBackgroundColor(Color.rgb(208, 216, 217));
                        else
                            label.setBackgroundColor(Color.WHITE);
                    }
                    label.setPadding(8,0,8,0);
                    System.out.println("12 th Column");
                    tr.addView(label);

                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setPadding(8,0,8,0);
                    label.setSingleLine();
                    try {
                        label.setBackgroundColor(Color.parseColor(cond));
                    }catch (Exception e)
                    {
                        if(rowcolor%2!=0)
                            label.setBackgroundColor(Color.rgb(208, 216, 217));
                        else
                            label.setBackgroundColor(Color.WHITE);
                    }
                    System.out.println("13 th Column");
                    tr.addView(label);

                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setPadding(8,0,8,0);
                    label.setSingleLine();

                    try {
                        label.setBackgroundColor(Color.parseColor(tran));
                    }catch (Exception e)
                    {
                        if(rowcolor%2!=0)
                            label.setBackgroundColor(Color.rgb(208, 216, 217));
                        else
                            label.setBackgroundColor(Color.WHITE);
                    }
                    tr.addView(label);

                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setPadding(8,0,8,0);
                    label.setSingleLine();

                    try {
                        label.setBackgroundColor(Color.parseColor(pack));
                    }catch (Exception e)
                    {
                        if(rowcolor%2!=0)
                            label.setBackgroundColor(Color.rgb(208, 216, 217));
                        else
                            label.setBackgroundColor(Color.WHITE);
                    }
                    tr.addView(label);
                }

                idcount++;
                count++;

            }

            rowcolor++;
            tl.addView(tr);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bstatus, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(BStatus.this, IndexPage.class);
        startActivity(i);
        BStatus.this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

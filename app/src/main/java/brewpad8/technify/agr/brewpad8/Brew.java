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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Brew extends ActionBarActivity implements View.OnClickListener {

    ArrayList<String> arrTblField = new ArrayList<String>();
    ArrayList<String> arrRowField = new ArrayList<String>();
    String sql, tblname;
    TextView label;
    TableRow tr;
    ImageButton tblBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brew);
        TableLayout tl = (TableLayout) findViewById(R.id.tbllo);
        Intent i = getIntent();
        //arrTblField = i.getStringArrayListExtra("arrTblField");
        sql = i.getStringExtra("Query");
        tblname = i.getStringExtra("TblName");

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.MATCH_PARENT);
        params.setMargins(3, 0, 0, 0);

        tr = new TableRow(this);
        tr.setId(152425);
        tr.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        tr.setBackgroundColor(Color.WHITE);
        String[] head = {"BatchID", "BatchrefNum", "Process", "Batch#", "Start Date", "Input", "F/Tank", "Planned Vol (BBL)"};
        for (int h = 0; h < 8; h++) {
            label = new TextView(this);
            if (h == 0 || h == 1)
                label.setVisibility(View.GONE);
            label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
            label.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            label.setBackgroundColor(Color.BLACK);
            label.setSingleLine();
            label.setLayoutParams(params);
            label.setPadding(10, 0, 10, 0);
            label.setTextColor(Color.WHITE);
            label.setText(head[h]);
            tr.addView(label);
            arrTblField.add(head[h]);
        }

        tl.addView(tr);

        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor ti = mDbHelper.getFieldData(sql);

        int count = 0;
        int idcount = 0;
        int rowcolor = 0;
        while (ti.moveToNext()) {

            if (idcount == 0)
                ti.moveToFirst();
            tr = new TableRow(this);
            tr.setId(100 + idcount);
            tr.setBackgroundColor(Color.BLACK);
            for (int sdata = 0; sdata < 8; sdata++) {
                String data = ti.getString(sdata);
                if (data == null)
                    data = "null";
                if (sdata == 0) {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    label.setText(data);
                    label.setVisibility(View.GONE);
                    label.setPadding(8, 0, 8, 0);
                    label.setTextColor(Color.BLACK);
                    tr.addView(label);
                } else if (sdata == 1) {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    label.setText(data);
                    label.setVisibility(View.GONE);
                    label.setPadding(8, 0, 8, 0);
                    label.setTextColor(Color.BLACK);
                    tr.addView(label);
                } else if (sdata == 2) {
                    tblBtn = new ImageButton(this);
                    tblBtn.setId(200 + count);
                    tblBtn.setImageResource(R.drawable.greenbtn);
                    tblBtn.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    if (rowcolor % 2 != 0)
                        tblBtn.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        tblBtn.setBackgroundColor(Color.WHITE);
                    tr.addView(tblBtn);
                    tblBtn.setOnClickListener(this);

                } else if (sdata == 3) {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if (rowcolor % 2 != 0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8, 0, 8, 0);
                    tr.addView(label);

                } else if (sdata == 4) {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if (rowcolor % 2 != 0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8, 0, 8, 0);
                    tr.addView(label);

                } else if (sdata == 5) {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if (rowcolor % 2 != 0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8, 0, 8, 0);
                    tr.addView(label);

                } else if (sdata == 6) {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if (rowcolor % 2 != 0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8, 0, 8, 0);
                    tr.addView(label);

                } else {
                    label = new TextView(this);
                    label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
                    label.setId(200 + count);
                    label.setLayoutParams(params);
                    label.setSingleLine();
                    if (rowcolor % 2 != 0)
                        label.setBackgroundColor(Color.rgb(208, 216, 217));
                    else
                        label.setBackgroundColor(Color.WHITE);
                    label.setText(data);
                    label.setTextColor(Color.BLACK);
                    label.setPadding(8, 0, 8, 0);
                    tr.addView(label);

                }

                idcount++;
                count++;

            }

            rowcolor++;
            tl.addView(tr);


        }
    }

    public void onClick(View v) {
        int btnid = v.getId(); //will give give respective Id which is set.
        Toast.makeText(getApplicationContext(), "Img Btn ID = "+v.getId(), Toast.LENGTH_LONG).show();
        tblBtn = (ImageButton) findViewById(btnid);
        TableRow t = (TableRow) tblBtn.getParent();
        for (int i = 0; i< t.getChildCount(); i++)
        {
            if(2 != i)
            {
                TextView tv = (TextView) t.getChildAt(i);
                arrRowField.add(tv.getText().toString());

            }else
            {
                 arrRowField.add("0");
            }
        }

        Intent nxt = new Intent(Brew.this, BrewIt.class);
        System.out.println("Array Row Fields === "+arrRowField);
        //nxt.putStringArrayListExtra("arrTblField", arrTblField);
        nxt.putStringArrayListExtra("arrRowField", arrRowField);
        nxt.putExtra("TblName", tblname);
        nxt.putExtra("Query", sql);
        startActivity(nxt);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_brew, menu);
        return true;
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

    @Override
    public void onBackPressed() {
        Intent back = new Intent(this, IndexPage.class);
        startActivity(back);
        Brew.this.finish();
    }
}

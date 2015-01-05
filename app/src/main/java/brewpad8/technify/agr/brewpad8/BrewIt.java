package brewpad8.technify.agr.brewpad8;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;


public class BrewIt extends ActionBarActivity implements View.OnClickListener{

    ArrayList<String> arrTblField = new ArrayList<String>();
    ArrayList<String> arrRowField = new ArrayList<String>();
    ArrayList<String> spinnerArray = new ArrayList<>();
    String sql, tblname;
    TextView label;
    TableRow tr;
    ImageButton tblBtn;
    Calendar myCalendar = Calendar.getInstance();
    TimePicker time;
    Calendar calendar;
    String format = "";
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brew_it);
        TableLayout tl = (TableLayout) findViewById(R.id.tbllo);
        Intent i = getIntent();
        arrRowField = i.getStringArrayListExtra("arrRowField");
        sql = i.getStringExtra("Query");
        tblname = i.getStringExtra("TblName");


        System.out.println("Array Row Fields === "+arrRowField+"  ::  query  ::  "+sql +"  ::  tblname  ::  "+tblname);

        TableRow.LayoutParams params = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(3, 0, 0, 0);

        tr = new TableRow(this);
        tr.setId(152425);
        tr.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        tr.setBackgroundColor(Color.WHITE);
        String[] head = {"BatchID", "BatchrefNum", "Process", "Batch#", "Start Date", "Time", "Input", "F/Tank", "Planned Vol (BBL)"};
        for (int h = 0; h < 9; h++) {
            label = new TextView(this);
            if (h == 0 || h == 1 || h == 2)
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

        System.out.println("arrRowField>>>>>>><<<<<<< "+arrRowField);
        Iterator ir = arrRowField.iterator();


        tr = new TableRow(this);
        tr.setId(100);
        tr.setBackgroundColor(Color.BLACK);
        String data;
        for(int sdata = 0; sdata < 9; sdata++)
        {
            if(sdata == 5) {
                data = "";
            } else {
                data = (String) ir.next();
            }

            label = new TextView(this);
            label.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
            label.setId(sdata);
            label.setLayoutParams(params);
            label.setSingleLine();
            label.setText(data);
            if(sdata == 0 || sdata == 1 || sdata == 2)
            label.setVisibility(View.GONE);
            label.setPadding(8, 0, 8, 0);
            label.setTextColor(Color.BLACK);
            label.setBackgroundColor(Color.WHITE);
            tr.addView(label);
            if(sdata == 4 || sdata == 5 || sdata == 7 || sdata == 8)
                label.setOnClickListener(this);
        }
             tl.addView(tr);

    }

    @Override
    public void onBackPressed() {
        Intent back = new Intent(this, Brew.class);
        back.putExtra("TblName", tblname);
        back.putExtra("Query", sql);
        startActivity(back);
        BrewIt.this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_brew_it, menu);
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
    public void onClick(View v) {
        int lblid = v.getId();
        label = (TextView) findViewById(lblid);


        String sltqry = "select tankname from am_tanklist";
        updateLabel(sltqry);
        Spinner spinner = new Spinner(this);
        spinner.showContextMenu();
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,R.layout.my_spinner, spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);
        int pos = spinnerArrayAdapter.getPosition(label.getText().toString());
        spinner.setSelection(pos);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0,
                                       View arg1, int arg2, long arg3) {
                label.setText(arg0.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });



        switch (v.getId()) {
            case 4:
                Toast.makeText(getApplicationContext(), "Date",Toast.LENGTH_LONG).show();

                final Calendar myCalendar = Calendar.getInstance();

                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }

                    private void updateLabel() {
                        String myFormat = "MM/dd/yyyy"; //In which you need put here
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        label.setText(sdf.format(myCalendar.getTime()));
                    }

                };

                label.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        new DatePickerDialog(BrewIt.this, date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });


                break;

            case 5:
                Toast.makeText(getApplicationContext(), "Time",Toast.LENGTH_LONG).show();
                showDialog(5);
                break;
            case 7:
                Toast.makeText(getApplicationContext(), "F/Tank",Toast.LENGTH_LONG).show();

                label.setVisibility(View.INVISIBLE);
                tr.addView(spinner);

                break;
            case 8:
                Toast.makeText(getApplicationContext(), "Planned Volume",Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 5:

                // set time picker as current time
                return new TimePickerDialog(this, timePickerListener, hour, minute,
                        false);

        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
            // TODO Auto-generated method stub
            hour   = hourOfDay;
            minute = minutes;

            showTime(hour, minute);

        }

    };

    private static String utilTime(int value) {

        if (value < 10)
            return "0" + String.valueOf(value);
        else
            return String.valueOf(value);
    }


    public void setTime(View v) {
        int hour = time.getCurrentHour();
        int min = time.getCurrentMinute();
        showTime(hour, min);
    }

    private void showTime(int hour, int min) {{
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        label.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
    }
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
}

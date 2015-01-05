package brewpad8.technify.agr.brewpad8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.LayoutDirection;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridLayout.Spec;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;


public class PapulateData extends Activity {

    ArrayList<String> arrTblField = new ArrayList<String>();
    ArrayList<String> arrRowField = new ArrayList<String>();
    SQLiteDatabase db;
    String sql, tblname;
    TextView label;
    ImageButton tblBtn;
    int rowSpec = 0;
    int columnSpec = 0;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridtable);
       GridView gv = (GridView) findViewById(R.id.gv);
        GridView gvc = (GridView) findViewById(R.id.gvc);

        Intent i = getIntent();
        arrTblField = i.getStringArrayListExtra("arrTblField");
        sql = i.getStringExtra("Query");
        tblname = i.getStringExtra("TblName");
        //System.out.println("Array Table Size :: " + arrTblField.size());
        gv.setNumColumns(arrTblField.size());
        gvc.setNumColumns(arrTblField.size());
        gv.setPadding(2, 2, 2, 2);
        gvc.setPadding(1, 1, 1, 1);

        Iterator ir = arrTblField.iterator();
        int height = 0;
        int width = 0;
        int big = 0;
        while (ir.hasNext()) {
            String s = (String) ir.next();

            label = new TextView(this);
            label.setText(s);

            Rect bounds = new Rect();
            Paint textPaint = label.getPaint();
            textPaint.getTextBounds(s, 0, s.length(), bounds);
            height = height + bounds.height();
            width = bounds.width();
            if (big < width)
                big = width;
            //System.out.println("LinearLayout Lenth :::" + width + "::: LinearLayout Height :::" + height);
        }

        LinearLayout ll = (LinearLayout) findViewById(R.id.linlaygv);
        int minWidth = (2 * big) + (big * arrTblField.size());
        //System.out.println("minWidth ::: " + minWidth);
        ll.setMinimumWidth(minWidth);

        gv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrTblField) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);
                text.setSingleLine();
                text.setBackgroundColor(Color.WHITE);
                text.setTypeface(Typeface.DEFAULT_BOLD);
                text.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                //System.out.println("GridView Position  =  "+position);
               if (text.getText().toString().equalsIgnoreCase("BATCHID"))
                    System.out.println("If Position for Batch Id = "+ position);
                if(convertView != null) {
                    if (position == 0)
                        convertView.setVisibility(View.VISIBLE);
                    else
                        convertView.setVisibility(View.INVISIBLE);
                }
                return view;
            }

        });


        TestAdapter mDbHelper = new TestAdapter(this);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor ti = mDbHelper.getFieldData(sql);

        int idcount = 0;
        while (ti.moveToNext()) {

            if (idcount == 0)
                ti.moveToFirst();
            for (int sdata = 0; sdata < arrTblField.size(); sdata++) {
                String data = ti.getString(sdata);
                if (data == null)
                    arrRowField.add("null");
                else
                    arrRowField.add(data);
                idcount++;
            }
        }
        System.out.println("arrRowField?????????"+arrRowField);
        gvc.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrRowField) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                 TextView text = (TextView) view.findViewById(android.R.id.text1);
                if(convertView == null) {
                    text.setTextColor(Color.BLACK);
                    text.setSingleLine();
                    text.setBackgroundColor(Color.WHITE);
                    /*if(position == 0)
                        convertView.setVisibility(View.GONE);*/
                }else {
                    text = (TextView) convertView;

                }


                     /*if (position == 1) {
                        convertView.setVisibility(View.INVISIBLE);
                        System.out.println("position == 16" + position);
                    }*/

                /*if(convertView == null)
                    if (position == 16) {
                        //convertView.setVisibility(View.INVISIBLE);
                        System.out.println("Position % 8 == " + position);
                        System.out.println("convertView" + convertView);
                    }*/
               /* if(position % 3 != 0 & convertView != null) {
                    System.out.println("Position % 8 == "+position);
                    convertView.setVisibility(View.VISIBLE);
                }else if (convertView != null)
                convertView.setVisibility(View.INVISIBLE);*/

                return view;
            }
        });

        gvc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Position :: " + id);

            }
        });
    }
}
package com.ghost.com.ghost_projects;

import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TableLayout table_layout;
    protected Map<View, Rect> cells = new HashMap<View, Rect>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner_year = (Spinner)findViewById(R.id.year_spin);
        ArrayAdapter<ArrayList> my_year = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,getYear());
        //my_year= this.getYear();
        spinner_year.setAdapter(my_year);
        table_layout = (TableLayout) findViewById(R.id.tableLayout1);

        table_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                View view = null;
                if (event.getAction() == MotionEvent.ACTION_DOWN
                        || event.getAction() == MotionEvent.ACTION_MOVE) {

                    int x = (int) event.getRawX();
                    int y = (int) event.getRawY();

                    for (final java.util.Map.Entry<View, Rect> entry : cells.entrySet()) {

                        view = entry.getKey();
                        final Rect rect = entry.getValue();
                        if (rect.contains(x, y)) {
                              view.setBackgroundColor(Color.BLUE);
                        } else {
                        //     view.setBackgroundColor(Color.BLUE);
                               // view.setBackgroundColor(Color.RED);
                            //tv.setText("");
                        }
                        //log("text view ");
                    }
                    //log(x+" "+y);
                    return true;
                }

                return false;
            }

        });
    }
    private ArrayList<String> getYear(){
        ArrayList<String> tmp = new ArrayList<>();
        for(int year_start = 1990;year_start<2020;year_start++){
            tmp.add(""+year_start);
        }
        return  tmp;
    }
    /*
    * click from month button
    * */
    public void monthclick(View v){
        int month_id;
        int year = 2016;
        switch (v.getId()){
            case R.id.jan:
                month_id = 0;
            break;
            case R.id.feb:
                month_id = 1;
                break;
            case R.id.mar:
                month_id = 2;
                break;
            case R.id.apr:
                month_id = 3;
                break;
            case R.id.may:
                month_id = 4;
                break;
            case R.id.jun:
                month_id = 5;
                break;
            case R.id.jul:
                month_id = 6;
                break;
            case R.id.aug:
                month_id = 7;
            break;
            case R.id.sep:
                month_id = 8;
            break;
            case R.id.oct:
                month_id = 9;
            break;
            case R.id.nov:
                month_id = 10;
            break;
            case R.id.dec:
                month_id = 11;
            break;
            default:
                month_id =0;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(year, month_id, 1);
        //this.log("total year "+cal.getTime());
        int to = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
       // this.log("total days "+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
       // this.log("day  "+cal.get(Calendar.DAY_OF_WEEK));
        BuildTable(1,to);
    }
    public void log(String l){
        Log.i("praveen",l);

    }
    private void BuildTable(int rows, int cols) {
        table_layout.removeAllViews();
        // outer for loop
        for (int i = 1; i <= rows; i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
            // inner for loop
            for (int j = 1; j <= cols; j++) {
                TextView tv = new TextView(this);
                tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                tv.setPadding(5, 5, 5, 5);
                tv.setText("C" + j);
                row.addView(tv);

            }
            table_layout.addView(row);
            for(int c_start = 0; c_start<row.getChildCount();c_start++){
                log("gettign textview cords");
                try{
                    TextView text_view = (TextView) row.getChildAt(c_start);
                    if(text_view instanceof TextView){
                        Rect rect = getRawCoordinatesRect(text_view);
                        cells.put(text_view, rect);
                    }
                }catch (Exception e ){
                    log("Error "+e.getMessage());
                }

            }
        }
    }
    private Rect getRawCoordinatesRect(final View view) {
        int[] coords = new int[2];
        view.getLocationOnScreen(coords);
        Rect rect = new Rect();
        rect.left = view.getLeft();
        rect.top = view.getTop();
        log("cord "+view.getLeft()+" "+view.getTop());
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        return rect;
    }

}

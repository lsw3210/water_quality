package kr.myroot.lsw3210.water_quality;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLEncoder;
import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2, text3, text4;
    TextView select_date;

    Button b1, b2;
    String sujcode;
    String stDt,edDt;
    String data, data2;
    String temp;

    String[] fcltyMngNm = new String[1000];
    String[] fcltyAddr= new String[1000];
    String[] occrrncDt= new String[1000];
    String[] liIndDivName= new String[1000];
    String[] cl_Val= new String[1000];
    String[] phVal= new String[1000];
    String[] tbVal= new String[1000];

    int n=0; //배열 순번

    int y=0, m=0, d=0;
    final Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text4= (TextView)findViewById(R.id.result4);

        final Spinner spinner1= (Spinner)findViewById(R.id.spinner_field1);
        final Spinner spinner2= (Spinner)findViewById(R.id.spinner_field2);



        final ArrayAdapter sujAdapter1 = ArrayAdapter.createFromResource(this, R.array.region, android.R.layout.simple_spinner_dropdown_item); //R.field 가 아니다
        //R.array.test는 저희가 정의해놓은 1월~12월 / android.R.layout.simple_spinner_dropdown_item은 기본으로 제공해주는 형식입니다.
        sujAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(sujAdapter1); //어댑터에 연결해줍니다.

        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        final ArrayAdapter sujAdapter2 = ArrayAdapter.createFromResource(MainActivity.this, R.array.gyeongi, android.R.layout.simple_spinner_dropdown_item); //R.field 가 아니다
                        sujAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(sujAdapter2); //어댑터에 연결해줍니다.

                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        sujcode = "311";
                                        break;
                                    case 1:
                                        sujcode = "312";
                                        break;
                                    case 2:
                                        sujcode = "313";
                                        break;
                                    case 3:
                                        sujcode = "314";
                                        break;
                                    case 4:
                                        sujcode = "316";
                                        break;
                                    case 5:
                                        sujcode = "317";
                                        break;
                                    case 6:
                                        sujcode = "319";
                                        break;
                                    case 7:
                                        sujcode = "388";
                                        break;
                                    case 8:
                                        sujcode = "315";
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                            }

                        });
                        break;
                    case 1:
                        final ArrayAdapter sujAdapter3 = ArrayAdapter.createFromResource(MainActivity.this, R.array.gangwon, android.R.layout.simple_spinner_dropdown_item); //R.field 가 아니다
                        sujAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(sujAdapter3); //어댑터에 연결해줍니다.

                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        sujcode = "371";
                                        break;
                                    case 1:
                                        sujcode = "356";
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                            }

                        });
                        break;

                    case 2:
                        final ArrayAdapter sujAdapter4 = ArrayAdapter.createFromResource(MainActivity.this, R.array.chungbuk, android.R.layout.simple_spinner_dropdown_item); //R.field 가 아니다
                        sujAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(sujAdapter4); //어댑터에 연결해줍니다.

                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        sujcode = "380";
                                        break;
                                    case 1:
                                        sujcode = "353";
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                            }

                        });
                        break;

                    case 3:
                        final ArrayAdapter sujAdapter5 = ArrayAdapter.createFromResource(MainActivity.this, R.array.chungnam, android.R.layout.simple_spinner_dropdown_item); //R.field 가 아니다
                        sujAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(sujAdapter5); //어댑터에 연결해줍니다.

                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        sujcode = "355";
                                        break;
                                    case 1:
                                        sujcode = "351";
                                        break;
                                    case 2:
                                        sujcode = "359";
                                        break;
                                    case 3:
                                        sujcode = "357";
                                        break;
                                    case 4:
                                        sujcode = "372";
                                        break;
                                    case 5:
                                        sujcode = "354";
                                        break;                               }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                            }

                        });
                        break;

                    case 4:
                        final ArrayAdapter sujAdapter6 = ArrayAdapter.createFromResource(MainActivity.this, R.array.chunbuk, android.R.layout.simple_spinner_dropdown_item); //R.field 가 아니다
                        sujAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(sujAdapter6); //어댑터에 연결해줍니다.

                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        sujcode = "385";
                                        break;
                                    case 1:
                                        sujcode = "365";
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                            }

                        });
                        break;

                    case 5:
                        final ArrayAdapter sujAdapter7 = ArrayAdapter.createFromResource(MainActivity.this, R.array.chunnam, android.R.layout.simple_spinner_dropdown_item); //R.field 가 아니다
                        sujAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(sujAdapter7); //어댑터에 연결해줍니다.

                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        sujcode = "364";
                                        break;
                                    case 1:
                                        sujcode = "386";
                                        break;
                                    case 2:
                                        sujcode = "389";
                                        break;
                                    case 3:
                                        sujcode = "368";
                                        break;
                                    case 4:
                                        sujcode = "366";
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                            }

                        });
                        break;

                    case 6:
                        final ArrayAdapter sujAdapter8 = ArrayAdapter.createFromResource(MainActivity.this, R.array.gyeongbuk, android.R.layout.simple_spinner_dropdown_item); //R.field 가 아니다
                        sujAdapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(sujAdapter8); //어댑터에 연결해줍니다.

                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        sujcode = "337";
                                        break;
                                    case 1:
                                        sujcode = "378";
                                        break;
                                    case 2:
                                        sujcode = "339";
                                        break;
                                    case 3:
                                        sujcode = "340";
                                        break;
                                    case 4:
                                        sujcode = "387";
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                            }

                        });
                        break;

                    case 7:
                        final ArrayAdapter sujAdapter9 = ArrayAdapter.createFromResource(MainActivity.this, R.array.gyeongnam, android.R.layout.simple_spinner_dropdown_item); //R.field 가 아니다
                        sujAdapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(sujAdapter9); //어댑터에 연결해줍니다.

                        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                    case 0:
                                        sujcode = "381";
                                        break;
                                    case 1:
                                        sujcode = "382";
                                        break;
                                    case 2:
                                        sujcode = "333";
                                        break;
                                    case 3:
                                        sujcode = "336";
                                        break;
                                    case 4:
                                        sujcode = "335";
                                        break;
                                    case 5:
                                        sujcode = "331";
                                        break;
                                    case 6:
                                        sujcode = "332";
                                        break;
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                            }

                        });
                        break;
                        


                }
            } //이 오버라이드 메소드에서 position은 몇번째 값이 클릭됬는지 알 수 있습니다.
            //getItemAtPosition(position)를 통해서 해당 값을 받아올수있습니다.

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate1();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate2();
            }
        });
    }

    void showDate1() {
       DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y = year;
                m = month+1;
                d = dayOfMonth;
                stDt= (String.format("%d-%02d-%02d", y, m , d));


                b1.setText(stDt);
            }
        },cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션
        datePickerDialog.show();
    }

    void showDate2() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y = year;
                m = month+1;
                d = dayOfMonth;
                edDt= (String.format("%d-%02d-%02d", y, m , d));


                b2.setText(edDt);
            }
        },cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션
        datePickerDialog.show();

    }

    //Button을 클릭했을 때 자동으로 호출되는 callback method....
    public void mOnClick(View view) {
        switch (view.getId()) {
            case R.id.button:

                new Thread(new Runnable() {
                    @Override
                    public  void run() {
                        data = getXmlData();
                        data2="";
                        n = 0;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //text.setText(data);
/*                                text1.setText(fcltyMngNm[1]);
                                text2.setText(fcltyAddr[1]);
                                text3.setText(liIndDivName[1]);*/

/*                                TableRow.LayoutParams rowLayout = new TableRow.LayoutParams(layoutFull, layoutMatch);

                                public void tableGrid (int n, int 4) {

                                    TableLayout table = new TableLayout(this); // 테이블 생성
                                    TableRow row[] = new TableRow[trCt];     // 테이블 ROW 생성
                                    TextView text[][] = new TextView[trCt][tdCt]; // 데이터

                                    for (int tr = 0; tr < trCt; tr++) {                  // for문을 이용한 줄수 (TR)
                                        row[tr] = new TableRow(this);

                                        for (int td = 0; td < tdCt; td++) {              // for문을 이용한 칸수 (TD)
                                            text[tr][td] = new TextView(this);
                                            text[tr][td].setText("데이터");                   // 데이터삽입
                                            text[tr][td].setTextSize(15);                     // 폰트사이즈
                                            text[tr][td].setTextColor(Color.BLACK);     // 폰트컬러
                                            text[tr][td].setGravity(Gravity.CENTER);    // 폰트정렬
                                            row[tr].addView(text[tr][td]);
                                        } // td for end
                                        table.addView(row[tr], rowLayout);
                                    } // tr for end
                                }*/

/*                                data2="            일  자                 염 소         PH          탁도\n";
                                for (int i=1;i<n;i++) {


                                    data2 = data2 + occrrncDt[i].substring(0,4) + "/" + occrrncDt[i].substring(4,6)  + "/" + occrrncDt[i].substring(6,8) + " " + occrrncDt[i].substring(8,10)
                                            + "시    " + ("0"+cl_Val[i]+"0").substring(0,6) + "    " + (phVal[i]+"0").substring(0,6) + "    0" + tbVal[i] + "\n";

                                    text4.setText(data2);
                                }*/
                                text4.setText(data);
                            }

                        });
                    }
                }).start();
                break;
        }
    }



    //XmlPullParser를 이용하여 Naver 에서 제공하는 OpenAPI XML 파일 파싱하기(parsing)
    String getXmlData(){

        StringBuffer buffer=new StringBuffer();

/*        String str= edit.getText().toString();//EditText에 작성된 Text얻어오기
        String location = null;//한글의 경우 인식이 안되기에 utf-8 방식으로 encoding..
        try {
            location = URLEncoder.encode(str,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        String queryUrl="http://apis.data.go.kr/B500001/rwis/waterQuality/list?"
                + "stDt="+ stDt + "&stTm=00&edDt=" + edDt +"&edTm=24&sujCode=" + sujcode
                + "&liIndDiv=1&numOfRows=100&pageNo=1&ServiceKey="
                + "R2DQ7bRdwI0Eet9NTN825ucrDez%2FWkBqCG2CpqZFvQRxRZGYGFmBNftY%2Bvb56uQ%2Bqs%2BehP31AHkzr7YMMcVR3g%3D%3D";

        // &fcltyMngNo=4824012333 없어도 됨

        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.

            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");

                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//테그 이름 얻어오기

                        if(tag.equals("item")) ;// 첫번째 검색결과
                        else if(tag.equals("fcltyMngNm")){
                            buffer.append("시설명 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");//줄바꿈 문자 추가
                            fcltyMngNm[n]=xpp.getText();
                        }
                        else if(tag.equals("fcltyAddr")){

                            buffer.append("시설주소 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("  ,  "); //줄바꿈 문자 추가
                            fcltyAddr[n]=xpp.getText();
                        }
                        else if(tag.equals("liIndDivName")){
                            buffer.append("용수구분 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n"); //줄바꿈 문자 추가
                            liIndDivName[n]=xpp.getText();
                        }
                        else if(tag.equals("occrrncDt")){

                            buffer.append("측정일시 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");//줄바꿈 문자 추가
                            occrrncDt[n]=xpp.getText();
                        }
                        else if(tag.equals("clVal")){
                            n=n+1;  //n++;가 안 먹는다
                            buffer.append("잔류염소 : ");
                            xpp.next();
                            temp=xpp.getText();
                            buffer.append(temp);
                            buffer.append("\n");//줄바꿈 문자 추가
                            cl_Val[n]=temp;
                        }
                        else if(tag.equals("phVal")){
                            buffer.append("PH : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n"); //줄바꿈 문자 추가
                            phVal[n]=xpp.getText();
                        }
                        else if(tag.equals("tbVal")){
                            buffer.append("탁도 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n"); //줄바꿈 문자 추가
                            tbVal[n]=xpp.getText();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("item")) buffer.append("\n");// 첫번째 검색결과종료..줄바꿈
                        break;
                }

                eventType= xpp.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO Auto-generated catch blocke.printStackTrace();
        }

        buffer.append("끝\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환

    }//getXmlData method....

}
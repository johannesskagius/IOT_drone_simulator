
//josk3261@student.su.se
package com.example.drone_simulator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {       //Instatiate variables
    private static final String FIN_POSITION_X = "position_X";
    private static final String FIN_POSITION_Y = "position_Y";
    private static final String CALL_POSITION_X = "startPos x";
    private static final String CALL_POSITION_Y = "startPos y";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference drone1Listener = database.getReference("drone1");
    private final DatabaseReference drone2Listener = database.getReference("drone2");
    private final DatabaseReference request = database.getReference("request");
    private TextView activeTextViewDrone, activeTextViewRequest, activeTextViewFin, activeTextViewDrone2;
    private Drone drone1 = null;
    private Drone drone2 = null;
    private String requestIsActive = "n";
    private String fin_X_pos = "";
    private String fin_Y_pos = "";
    private String call_X_pos = "";
    private String call_Y_pos = "";
    private final Map<String, TextView> textViewMap = new HashMap();
    private final Map<String, String> usedPosition = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTextViews();
        addDrone();
        getdrone2Position();
        getRequestPosition();
    }

    private void getRequestPosition() {     //This class is a listener to the server. It collects the request made by the UI and stores them in correct variables to be used
        request.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                call_X_pos = snapshot.child("startPos x").getValue().toString();
                call_Y_pos = snapshot.child("startPos y").getValue().toString();
                requestIsActive = snapshot.child("active").getValue().toString();
                fin_X_pos = snapshot.child("Finish x").getValue().toString();
                fin_Y_pos = snapshot.child("Finish y").getValue().toString();

                showobjectposition(call_X_pos + call_Y_pos, "req");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void addTextViews() {   //This method adds textviews to a Hash map. The key to the values is the coordinates which you can view in the project report.
        String ONE = "1";
        String TWO = "2";
        String THREE = "3";
        String FOUR = "4";
        String FIVE = "5";
        String SIX = "6";
        String SEVEN = "7";
        String EIGHT = "8";
        String NINE = "9";
        String ZERO = "0";

        textViewMap.put(ONE + ONE + FOUR, (TextView) findViewById(R.id.img1));      //FOOBAR
        textViewMap.put(TWO + ONE + FOUR, (TextView) findViewById(R.id.img2));      //WC1
        textViewMap.put(THREE + ONE + FOUR, (TextView) findViewById(R.id.img3));    //ENTRY
        textViewMap.put(FOUR + ONE + FOUR, (TextView) findViewById(R.id.img4));     //SEMINARIUM
        textViewMap.put(ONE + ONE + THREE, (TextView) findViewById(R.id.img6));     //LUNCHRUM
        textViewMap.put(TWO + ONE + THREE, (TextView) findViewById(R.id.img7));     //ENTRY
        textViewMap.put(THREE + ONE + THREE, (TextView) findViewById(R.id.img8));

        textViewMap.put(TWO + ONE + TWO, (TextView) findViewById(R.id.img12));
        textViewMap.put(THREE + ONE + TWO, (TextView) findViewById(R.id.img13));
        textViewMap.put(TWO + ONE + ONE, (TextView) findViewById(R.id.img17));
        textViewMap.put(THREE + ONE + ONE, (TextView) findViewById(R.id.img18));

        textViewMap.put(TWO + ONE + ZERO, (TextView) findViewById(R.id.img22));
        textViewMap.put(THREE + ONE + ZERO, (TextView) findViewById(R.id.img23));

        textViewMap.put(TWO + ZERO + NINE, (TextView) findViewById(R.id.img27));
        textViewMap.put(THREE + ZERO + NINE, (TextView) findViewById(R.id.img28));

        textViewMap.put(TWO + ZERO + EIGHT, (TextView) findViewById(R.id.img32));
        textViewMap.put(THREE + ZERO + EIGHT, (TextView) findViewById(R.id.img33));
        textViewMap.put(TWO + ZERO + SEVEN, (TextView) findViewById(R.id.img37));
        textViewMap.put(THREE + ZERO + SEVEN, (TextView) findViewById(R.id.img38));

        textViewMap.put(TWO + ZERO + SIX, (TextView) findViewById(R.id.img42));
        textViewMap.put(THREE + ZERO + SIX, (TextView) findViewById(R.id.img43));
        textViewMap.put(TWO + ZERO + FIVE, (TextView) findViewById(R.id.img47));
        textViewMap.put(THREE + ZERO + FIVE, (TextView) findViewById(R.id.img48));
        textViewMap.put(FOUR + ZERO + FIVE, (TextView) findViewById(R.id.img49));       //MEDIALABB

        textViewMap.put(ONE + ZERO + FOUR, (TextView) findViewById(R.id.img51));        // L50
        textViewMap.put(TWO + ZERO + FOUR, (TextView) findViewById(R.id.img52));
        textViewMap.put(THREE + ZERO + FOUR, (TextView) findViewById(R.id.img53));
        textViewMap.put(FOUR + ZERO + FOUR, (TextView) findViewById(R.id.img54));
        textViewMap.put(FIVE + ZERO + FOUR, (TextView) findViewById(R.id.img55));
        textViewMap.put(SIX + ZERO + FOUR, (TextView) findViewById(R.id.img56));
        textViewMap.put(SEVEN + ZERO + FOUR, (TextView) findViewById(R.id.img57));
        textViewMap.put(EIGHT + ZERO + FOUR, (TextView) findViewById(R.id.img58));      //L70

        textViewMap.put(TWO + ZERO + THREE, (TextView) findViewById(R.id.img61));
        textViewMap.put(THREE + ZERO + THREE, (TextView) findViewById(R.id.img62));
        textViewMap.put(FOUR + ZERO + THREE, (TextView) findViewById(R.id.img63));
        textViewMap.put(FIVE + ZERO + THREE, (TextView) findViewById(R.id.img64));       // Lilla hörsalen
        textViewMap.put(SIX + ZERO + THREE, (TextView) findViewById(R.id.img65));
        textViewMap.put(SEVEN + ZERO + THREE, (TextView) findViewById(R.id.img66));        // WC 3
        textViewMap.put(EIGHT + ZERO + THREE, (TextView) findViewById(R.id.img67));

        textViewMap.put(TWO + ZERO + TWO, (TextView) findViewById(R.id.img70));
        textViewMap.put(THREE + ZERO + TWO, (TextView) findViewById(R.id.img71));
        textViewMap.put(FOUR + ZERO + TWO, (TextView) findViewById(R.id.img72));        //Hiss
        textViewMap.put(FIVE + ZERO + TWO, (TextView) findViewById(R.id.img73));        //Lilla görsalen
        textViewMap.put(SIX + ZERO + TWO, (TextView) findViewById(R.id.img74));
        textViewMap.put(SEVEN + ZERO + TWO, (TextView) findViewById(R.id.img75));       //HISS / WC 3
        textViewMap.put(EIGHT + ZERO + TWO, (TextView) findViewById(R.id.img76));

        textViewMap.put(THREE + ZERO + ONE, (TextView) findViewById(R.id.img80));
    }


    private void addDrone() {   //This method adds to drones. Their values will be overwritten when new values are collected from the server.
        drone1 = new Drone(1, 1);
        drone2 = new Drone(1, 1);
    }


    public void showobjectposition(String position, String identifier) {            //Switch case to show drone an objects position. It uses switch to identify what color should be used.
                                                                                             //The colors are used to let the viewer easily identify where the objects are.
        int color;
        String value = "d";
        switch (identifier) {
            case "req":
                color = Color.MAGENTA;
                activeTextViewRequest = textViewMap.get(position);
                break;
            case "fin":
                color = Color.CYAN;
                activeTextViewFin = textViewMap.get(identifier);
                break;
            case "drone1":
                color = Color.RED;
                activeTextViewDrone = textViewMap.get(identifier);
                break;
            case "drone2":
                color = Color.RED;
                activeTextViewDrone2 = textViewMap.get(position);
                break;
            default:
                color = Color.WHITE;
        }
        try {
            textViewMap.get(position).setTextColor(color);
            usedPosition.put(identifier, position + value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getdrone2Position() {    //This method is a listener to the server. It collects the drone whereabouts and stores them in correct variables to be used. When a value is changed on the server, this method is called.
        drone2Listener.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String xValue = snapshot.child(FIN_POSITION_X).getValue().toString();
                String yValue = snapshot.child(FIN_POSITION_Y).getValue().toString();
                if (yValue.length() == 1) {
                    yValue = "0" + yValue;
                }
                //Check if drone is at any of the targets
                checkDroneProgress(xValue, yValue);

                showobjectposition(xValue + yValue, "drone2");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void checkDroneProgress(String xValue, String yValue) {
        if(xValue.equals(call_X_pos) && yValue.equals(call_Y_pos)) resetColors();
        if(xValue.equals(fin_X_pos) && yValue.equals(call_Y_pos)) resetColors();
    }

    public void resetColors() {
        recreate();
    }
    public void resetColors(View view) {
        recreate();
    }
}
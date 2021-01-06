package com.example.drone_simulator;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String POSITION_X = "position_X";
    private static final String POSITION_Y = "position_Y";
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference drone1Listener = database.getReference("drone1");
    private final DatabaseReference drone2Listener = database.getReference("drone2");
    private final DatabaseReference request = database.getReference("request");
    private TextView activeTextViewDrone, activeTextViewRequest, activeTextViewFin, activeTextViewDrone2;
    private Drone drone1 = null;
    private Drone drone2 = null;
    private String requestIsActive = "n";
    private String xFin = "";
    private String yFin = "";
    private final Map<String, TextView> TextViewArray = new HashMap();
    private final Map<String, String> usedPosition = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTextViews();
        addDrone();
        getdrone2Position();
        //getdrone1Position();
        getRequestPosition();
        //getFinishPosition();
    }

    private void getdrone1Position() {
        drone1Listener.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String xValue = snapshot.child(POSITION_X).getValue().toString();
                String yValue = snapshot.child(POSITION_Y).getValue().toString();
                drone1.setxPosition(Integer.parseInt(xValue));
                drone1.setyPosition(Integer.parseInt(yValue));
                showObjectPosition2(xValue + yValue, "drone1");                //Shows drone position via image resource
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void getRequestPosition() {
        request.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String startPosX = snapshot.child("startPos x").getValue().toString();
                String startPosY = snapshot.child("startPos y").getValue().toString();
                requestIsActive = snapshot.child("active").getValue().toString();
                xFin = snapshot.child("Finish x").getValue().toString();
                yFin = snapshot.child("Finish y").getValue().toString();
                showObjectPosition2(startPosX + startPosY, "req");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getFinishPosition() {
        request.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String startPosX = snapshot.child("Finish x").getValue().toString();
                String startPosY = snapshot.child("Finish y").getValue().toString();
                showObjectPosition2(startPosX + startPosY, "fin");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addTextViews() {
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

        TextViewArray.put(ONE + ONE + FOUR, (TextView) findViewById(R.id.img1));      //FOOBAR
        TextViewArray.put(TWO + ONE + FOUR, (TextView) findViewById(R.id.img2));      //WC1
        TextViewArray.put(THREE + ONE + FOUR, (TextView) findViewById(R.id.img3));    //ENTRY
        TextViewArray.put(FOUR + ONE + FOUR, (TextView) findViewById(R.id.img4));     //SEMINARIUM
        TextViewArray.put(ONE + ONE + THREE, (TextView) findViewById(R.id.img6));     //LUNCHRUM
        TextViewArray.put(TWO + ONE + THREE, (TextView) findViewById(R.id.img7));     //ENTRY
        TextViewArray.put(FOUR + ONE + THREE, (TextView) findViewById(R.id.img8));

        TextViewArray.put(TWO + ONE + TWO, (TextView) findViewById(R.id.img12));
        TextViewArray.put(THREE + ONE + TWO, (TextView) findViewById(R.id.img13));
        TextViewArray.put(TWO + ONE + ONE, (TextView) findViewById(R.id.img17));
        TextViewArray.put(THREE + ONE + ONE, (TextView) findViewById(R.id.img18));

        TextViewArray.put(TWO + ONE + ZERO, (TextView) findViewById(R.id.img22));
        TextViewArray.put(THREE + ONE + ZERO, (TextView) findViewById(R.id.img23));

        TextViewArray.put(TWO + ZERO + NINE, (TextView) findViewById(R.id.img27));
        TextViewArray.put(THREE + ZERO + NINE, (TextView) findViewById(R.id.img28));

        TextViewArray.put(TWO + ZERO + EIGHT, (TextView) findViewById(R.id.img32));
        TextViewArray.put(THREE + ZERO + EIGHT, (TextView) findViewById(R.id.img33));
        TextViewArray.put(TWO + ZERO + SEVEN, (TextView) findViewById(R.id.img37));
        TextViewArray.put(THREE + ZERO + SEVEN, (TextView) findViewById(R.id.img38));

        TextViewArray.put(TWO + ZERO + SIX, (TextView) findViewById(R.id.img42));
        TextViewArray.put(THREE + ZERO + SIX, (TextView) findViewById(R.id.img43));
        TextViewArray.put(TWO + ZERO + FIVE, (TextView) findViewById(R.id.img47));
        TextViewArray.put(THREE + ZERO + FIVE, (TextView) findViewById(R.id.img48));
        TextViewArray.put(FOUR + ZERO + FIVE, (TextView) findViewById(R.id.img49));       //MEDIALABB

        TextViewArray.put(ONE + ZERO + FOUR, (TextView) findViewById(R.id.img51));        // L50
        TextViewArray.put(TWO + ZERO + FOUR, (TextView) findViewById(R.id.img52));
        TextViewArray.put(THREE + ZERO + FOUR, (TextView) findViewById(R.id.img53));
        TextViewArray.put(FOUR + ZERO + FOUR, (TextView) findViewById(R.id.img54));
        TextViewArray.put(FIVE + ZERO + FOUR, (TextView) findViewById(R.id.img55));
        TextViewArray.put(SIX + ZERO + FOUR, (TextView) findViewById(R.id.img56));
        TextViewArray.put(SEVEN + ZERO + FOUR, (TextView) findViewById(R.id.img57));
        TextViewArray.put(EIGHT + ZERO + FOUR, (TextView) findViewById(R.id.img58));      //L70

        TextViewArray.put(TWO + ZERO + TWO, (TextView) findViewById(R.id.img70));

        TextViewArray.put(THREE + ZERO + TWO, (TextView) findViewById(R.id.img71));
        TextViewArray.put(FOUR + ZERO + TWO, (TextView) findViewById(R.id.img72));        //Hiss
        TextViewArray.put(FIVE + ZERO + TWO, (TextView) findViewById(R.id.img73));        //Lilla görsalen
        TextViewArray.put(SIX + ZERO + TWO, (TextView) findViewById(R.id.img74));
        TextViewArray.put(SEVEN + ZERO + TWO, (TextView) findViewById(R.id.img75));       //HISS / WC 3
        TextViewArray.put(EIGHT + ZERO + TWO, (TextView) findViewById(R.id.img76));
        TextViewArray.put(THREE + ZERO + ONE, (TextView) findViewById(R.id.img80));
    }


    private void addDrone() {
        drone1 = new Drone(1, 1);
        drone2 = new Drone(1, 1);
    }


    public void showObjectPosition2(String position, String identifier) {            //Switch case to show drone position via text color change resource
        int color;
        String value = "d";
        switch (identifier) {
            case "req":
                color = Color.MAGENTA;
                //checkTextColor(activeTextViewRequest);
                activeTextViewRequest = TextViewArray.get(position);
                value = activeTextViewRequest.getText().toString();
                break;
            case "fin":
                color = Color.CYAN;
                //checkTextColor(activeTextViewFin);
                activeTextViewFin = TextViewArray.get(identifier);
                value = activeTextViewFin.getText().toString();
                break;
            case "drone1":
                color = Color.RED;
                //checkTextColor(activeTextViewDrone);
                activeTextViewDrone = TextViewArray.get(identifier);
                value = activeTextViewDrone.getText().toString();
                break;
            case "drone2":
                color = Color.RED;
                //checkTextColor(activeTextViewDrone2);
                activeTextViewDrone2 = TextViewArray.get(position);
                //value = activeTextViewDrone2.getText().toString();
                break;
            default:
                color = Color.WHITE;
        }
        try {
            TextViewArray.get(position).setTextColor(color);
            usedPosition.put(identifier, position + value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void getdrone2Position() {
        drone2Listener.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String xValue = snapshot.child(POSITION_X).getValue().toString();
                String yValue = snapshot.child(POSITION_Y).getValue().toString();
                if (yValue.length() == 1) {
                    yValue = "0" + yValue;
                }
                showObjectPosition2(xValue + yValue, "drone2");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
package com.example.drone_simulator;

import android.os.Bundle;
import android.widget.ImageView;


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

    private static final String Y = "position_Y";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference drone1Listener = database.getReference("drone1");
    private DatabaseReference drone2Listener = database.getReference("drone2");
    private DatabaseReference request = database.getReference("request");
    private ImageView activeImageViewDrone;
    private ImageView activeImageViewRequest;
    private ImageView activeImageViewFin;
    private ImageView activeImageViewDrone2;
    private Drone drone1 = null;
    private Drone drone2 = null;
    private Map<String, ImageView> imageViewArray = new HashMap();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addImageViews();
        addDrone();
        getAndSetDronePosition();
        getRequest();
    }



    private void getRequest() {
        request.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String startPosX = snapshot.child("startPos x").getValue().toString();
                String startPosY = snapshot.child("startPos y").getValue().toString();
                String finishPosx = snapshot.child("Finish x").getValue().toString();
                String finishPosY = snapshot.child("Finish y").getValue().toString();
                showObjectPosition(startPosX+startPosY,"req");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void addImageViews() {
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

        imageViewArray.put(ONE + ONE + FOUR, (ImageView) findViewById(R.id.img1));      //FOOBAR
        imageViewArray.put(TWO + ONE + FOUR, (ImageView) findViewById(R.id.img2));      //WC1
        imageViewArray.put(THREE + ONE + FOUR, (ImageView) findViewById(R.id.img3));    //ENTRY
        imageViewArray.put(FOUR + ONE + FOUR, (ImageView) findViewById(R.id.img4));     //SEMINARIUM
        imageViewArray.put(ONE + ONE + THREE, (ImageView) findViewById(R.id.img6));     //LUNCHRUM
        imageViewArray.put(TWO + ONE + THREE, (ImageView) findViewById(R.id.img7));     //ENTRY
        imageViewArray.put(FOUR + ONE + THREE, (ImageView) findViewById(R.id.img8));

        imageViewArray.put(TWO + ONE + TWO, (ImageView) findViewById(R.id.img12));
        imageViewArray.put(THREE + ONE + TWO, (ImageView) findViewById(R.id.img13));
        imageViewArray.put(TWO + ONE + ONE, (ImageView) findViewById(R.id.img17));
        imageViewArray.put(THREE + ONE + ONE, (ImageView) findViewById(R.id.img18));

        imageViewArray.put(TWO + ONE + ZERO, (ImageView) findViewById(R.id.img22));
        imageViewArray.put(THREE + ONE + ZERO, (ImageView) findViewById(R.id.img23));

        imageViewArray.put(TWO + ZERO + NINE, (ImageView) findViewById(R.id.img27));
        imageViewArray.put(THREE + ZERO + NINE, (ImageView) findViewById(R.id.img28));

        imageViewArray.put(TWO + ZERO + EIGHT, (ImageView) findViewById(R.id.img32));
        imageViewArray.put(THREE + ZERO + EIGHT, (ImageView) findViewById(R.id.img33));
        imageViewArray.put(TWO + ZERO + SEVEN, (ImageView) findViewById(R.id.img37));
        imageViewArray.put(THREE + ZERO + SEVEN, (ImageView) findViewById(R.id.img38));

        imageViewArray.put(TWO + ZERO + SIX, (ImageView) findViewById(R.id.img42));
        imageViewArray.put(THREE + ZERO + SIX, (ImageView) findViewById(R.id.img43));
        imageViewArray.put(TWO + ZERO + FIVE, (ImageView) findViewById(R.id.img47));
        imageViewArray.put(THREE + ZERO + FIVE, (ImageView) findViewById(R.id.img48));
        imageViewArray.put(FOUR + ZERO + FIVE, (ImageView) findViewById(R.id.img49));       //MEDIALABB

//        imageViewArray.put(ONE + ZERO + FOUR, (ImageView) findViewById(R.id.img51));        // L50
        imageViewArray.put(TWO + ZERO + FOUR, (ImageView) findViewById(R.id.img52));
        imageViewArray.put(THREE + ZERO + FOUR, (ImageView) findViewById(R.id.img53));
        imageViewArray.put(FOUR + ZERO + FOUR, (ImageView) findViewById(R.id.img54));
        imageViewArray.put(FIVE + ZERO + FOUR, (ImageView) findViewById(R.id.img55));
        imageViewArray.put(SIX + ZERO + FOUR, (ImageView) findViewById(R.id.img56));
        imageViewArray.put(SEVEN + ZERO + FOUR, (ImageView) findViewById(R.id.img57));
        imageViewArray.put(EIGHT + ZERO + FOUR, (ImageView) findViewById(R.id.img58));      //L70

        imageViewArray.put(TWO + ZERO + TWO, (ImageView) findViewById(R.id.img70));

        imageViewArray.put(THREE + ZERO + TWO, (ImageView) findViewById(R.id.img71));
        imageViewArray.put(FOUR + ZERO + TWO, (ImageView) findViewById(R.id.img72));        //Hiss
        imageViewArray.put(FIVE + ZERO + TWO, (ImageView) findViewById(R.id.img73));        //Lilla görsalen
        imageViewArray.put(SIX + ZERO + TWO, (ImageView) findViewById(R.id.img74));
        imageViewArray.put(SEVEN + ZERO + TWO, (ImageView) findViewById(R.id.img75));       //HISS / WC 3
        imageViewArray.put(EIGHT + ZERO + TWO, (ImageView) findViewById(R.id.img76));
        imageViewArray.put(THREE + ZERO + ONE, (ImageView) findViewById(R.id.img80));
    }


    private void addDrone() {
        drone1 = new Drone(1, 1, 1);
        drone2 = new Drone(1, 1, 1);
    }

    public void showObjectPosition(String position, String identifier) {
        try {
            int imageResource = 0;
            switch(identifier){
                case "req":
                    resetImageView(activeImageViewRequest);
                    imageResource = R.drawable.person;
                    activeImageViewRequest = imageViewArray.get(position);
                    break;
                case "fin":
                    resetImageView(activeImageViewFin);
                    imageResource = R.drawable.target;
                    activeImageViewFin = imageViewArray.get(position);
                    break;
                case "drone1":
                    resetImageView(activeImageViewDrone);
                    imageResource = R.drawable.drone1;
                    activeImageViewDrone = imageViewArray.get(position);
                    break;
                case "drone2":
                    resetImageView(activeImageViewDrone2);
                    imageResource = R.drawable.drone2;
                    activeImageViewDrone2 = imageViewArray.get(position);
                    break;
                default:
                    imageResource = R.drawable.empty;
            }
            imageViewArray.get(position).setImageResource(imageResource);
            //imageViewArray.get(position).set
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetImageView(ImageView activeImageViewRequest) {
        if (activeImageViewRequest != null) {
            activeImageViewRequest.setImageResource(R.drawable.empty);
        }
    }

    private void getAndSetDronePosition() {
        try {
            drone2Listener.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String xValue = snapshot.child(POSITION_X).getValue().toString();
                    String yValue = snapshot.child(POSITION_Y).getValue().toString();

                    if (yValue.length() == 1) {
                        yValue = "0" + yValue;
                    }

                    showObjectPosition(xValue + yValue, "drone2");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            drone1Listener.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String xValue = snapshot.child(POSITION_X).getValue().toString();
                    String yValue = snapshot.child(POSITION_Y).getValue().toString();
                    drone2.setxPosition(Integer.parseInt(xValue));
                    drone2.setyPosition(Integer.parseInt(yValue));
                    showObjectPosition(xValue + yValue, "drone1");

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package com.example.himanshu.imageview;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener,GestureDetector.OnGestureListener ,GestureDetector.OnDoubleTapListener {

    GestureDetector detector;
    View screen;
    TextView text;
    TextView inf;
    ImageView image;
    int counter = 0;


    private static final String TAG = "MyFirebaseIIDService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = findViewById(R.id.screen_response);
        screen.setOnTouchListener(this);
        detector = new GestureDetector(this, this);

        Log.d(TAG, "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());



        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]

        Button subscribeButton = (Button) findViewById(R.id.btn2);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // [START subscribe_topics]
                FirebaseMessaging.getInstance().subscribeToTopic("news");
                Log.d(TAG, "Subscribed to news topic");
                // [END subscribe_topics]
            }
        });

        Button logTokenButton = (Button) findViewById(R.id.btn3);
        logTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "InstanceID token: " + FirebaseInstanceId.getInstance().getToken());
            }
        });



    image = (ImageView) findViewById(R.id.image_View);

        Fragment fragment1 = new Fragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment1);
        transaction.commit();

    }

    @Override
    public boolean onDown(MotionEvent e) {
        Toast.makeText(this, "down detected", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Toast.makeText(this, "single tap detected", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Toast.makeText(this, "single tap detected", Toast.LENGTH_SHORT).show();

        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {


    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        text = (TextView) findViewById(R.id.hello_view);
        text.setVisibility(View.VISIBLE);

        Toast.makeText(this, "on fling detection", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        //image.setImageResource(R.drawable.img2);
        counter++;
        if(counter%4==0){
            image.setImageResource(R.drawable.img2);
        }
       else if(counter%4==1){
            image.setImageResource(R.drawable.img3);
        }
       else if(counter%4==2){
            image.setImageResource(R.drawable.img4);
        }
        else if(counter%4==3){
            image.setImageResource(R.drawable.img5);
        }

        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }


}

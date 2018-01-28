package com.sunil.user.truthordare;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView bottle;
    Random rand = new Random();
    int lastAngle = -1;
    SQLiteDatabase testDB;
    Cursor c;
    LinearLayout linearLayout;

    public void truthPressed(View view) {

        long count = DatabaseUtils.queryNumEntries(testDB, "truth");
        Random ran = new Random();
        int n = ran.nextInt((int) count);
        c = testDB.rawQuery("SELECT * FROM truth", null);
        c.moveToPosition(n);

        Toast.makeText(this, c.getString(c.getColumnIndex("stat")), Toast.LENGTH_SHORT).show();
        c.close();
        linearLayout.setVisibility(View.INVISIBLE);
}

    public void darePressed(View view) {

        long count = DatabaseUtils.queryNumEntries(testDB, "dares");
        Random ran = new Random();
        int n = ran.nextInt((int) count);
        c = testDB.rawQuery("SELECT * FROM dares", null);
        c.moveToPosition(n);

        Toast.makeText(this, c.getString(c.getColumnIndex("stat")), Toast.LENGTH_SHORT).show();
        c.close();
        linearLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout)findViewById(R.id.linerLayout);

        testDB = this.openOrCreateDatabase("TruthsDB", MODE_PRIVATE, null);
        try {
            testDB.execSQL("CREATE TABLE IF NOT EXISTS truth (stat VARCHAR PRIMARY KEY)");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String truths = "What would you do if your parents left the house to you for a whole week?\n" +
                "What is the funniest youtube video you have ever seen?\n" +
                "What he craziest thing that you have ever done without your parents knowing?\n" +
                "Have you ever had a crush on someone that your best friend has dated?\n" +
                "Who is the worst teacher you have ever had, why?\n" +
                "What celebrity are you obsessed with?\n" +
                "Who is your crush at school?\n" +
                "Have you ever cheated on any test?\n" +
                "Can you see yourself being married to the creepiest kid a your school someday?\n" +
                "Have you ever watched an adult film without your parents knowing?\n" +
                "Who is your best looking teacher that you have ever had?\n" +
                "Would you ever get on a dating website?\n" +
                "Have you ever blamed something that you have done on one of your siblings?\n" +
                "Do you have a job? If so, what is your favourite thing about it?\n" +
                "What is the longest time that you think you could go without your cell phone?\n" +
                "If you were a billionaire, what would you spend your time doing?\n" +
                "Are you still a virgin?\n" +
                "Who is the most annoying person you know?\n" +
                "What is the most expensive thing you own?\n" +
                "Have you ever lied to your parents about what you were doing after school?\n" +
                "What is your biggest pet peeve?\n" +
                "Have you ever pulled a prank on your teacher?\n" +
                "What college do you plan on going to?\n" +
                "What is the most annoying thing that one of your siblings has done?\n" +
                "Have you ever been kissed yet? If so, who was your best kiss?\n" +
                "Could you go two months without talking to your friends?\n" +
                "What is the longest time you have ever been grounded?\n" +
                "Have you ever told one of your best friend’s secrets, even if you said you wouldn’t?\n" +
                "If you had the choice to live on your own right now, would you do it?\n" +
                "What is your favorite song that is out right now?\n" +
                "What is your favorite sports team?\n" +
                "What is the best vacation you’ve ever been on?\n" +
                "What is your favorite kind of clothing?\n" +
                "How many boyfriends (or girlfriends) have you had?\n" +
                "If you could own your own business one day, what would it be?";
        String truthArray [] = truths.split("\n");

        for(int i=0; i< truthArray.length; i++) {
            try {
                testDB.execSQL("INSERT INTO truth (stat) VALUES (\"" + truthArray[i] + "\")");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            testDB.execSQL("CREATE TABLE IF NOT EXISTS dares (stat VARCHAR PRIMARY KEY)");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String dares = "Put makeup on with no mirror\n" +
                "Go outside and sing your national anthem as loud as you can\n" +
                "Do your best belly dance\n" +
                "Smell pepper and try not to sneeze\n" +
                "Send an email to one of your teachers, telling them about how your day is going\n" +
                "Eat three spoonfuls of mustard\n" +
                "Do a handstand on the wall for the next two rounds\n" +
                "Run in place or the next three rounds\n" +
                "Share a shirt with the person to your right for the next 3 rounds\n" +
                "Do 20 cartwheels\n" +
                "Prank call a local restaurant and order one of everything on the menu\n" +
                "Do your best chicken dance outside on the lawn\n" +
                "Make up a three minute song about the first thing that comes to your mind\n" +
                "Fold yourself into a pretzel\n" +
                "Give person to your left a kiss on the forehead\n" +
                "Call and break up with your girlfriend or boyfriend\n" +
                "Hold your shoe up to your nose until it is your turn again\n" +
                "Eat as much hot sauce as you can\n" +
                "Ask your crush out on a date\n" +
                "Do an impression of your most annoying teacher\n" +
                "With powder in your hand, smack the person to you left on the cheek\n" +
                "Eat half a stick of butter\n" +
                "Put the socks of the person to your right in your mouth\n" +
                "Act like a pig until it is your turn again\n" +
                "Do a cannonball onto the couch\n" +
                "Go rinse your mouth out with soap or a minute, then blow soap bubbles from your mouth until the soap runs out\n" +
                "Take a selfie with a broom and post it on all of your social media\n" +
                "Blindfolded, spin around 10 times and walk down the street and back\n" +
                "Text your crush and tell them how much you like them\n" +
                "Call and yell at one of your parents and then tell them they are grounded for a week\n" +
                "Using only your elbows, make a Facebook status and post it\n" +
                "Try to tickle yourself\n" +
                "Kiss a pillow until it is your turn again\n" +
                "Take a selfie with your finger up your nose and post it to all your social media\n" +
                "Walk on your hands from one side of the room to the other";

        String dareArray [] = dares.split("\n");

        for(int i=0; i< dareArray.length; i++) {
            try {
                testDB.execSQL("INSERT INTO dares (stat) VALUES (\"" + dareArray[i] + "\")");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        bottle = findViewById(R.id.bottle);
        bottle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                spinBottle();
                return false;
            }
        });

    }

    private void spinBottle() {

        int angle = rand.nextInt(3600-360) + 360;
        float pivotX = bottle.getWidth()/2;
        float pivotY = bottle.getHeight()/2;

        final Animation animRotate = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, angle, pivotX, pivotY);
        lastAngle = angle;
        animRotate.setDuration(2500);
        animRotate.setFillAfter(true);

        bottle.startAnimation(animRotate);

        linearLayout.setVisibility(View.VISIBLE);

    }


}

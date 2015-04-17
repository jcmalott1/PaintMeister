package edu.wcu.PaintMeister;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Loading screen for application.
 *
 * @author Johnathon Malott, Kevin James
 * @version 4-16-2015
 */
public class SplashActivity extends Activity {

    /**
     * Sets the splash screen, waits for 5 seconds and displays Paint Meister
     * main screen.
     *
     * @param savedInstanceState Most recently supplied data on screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1_splah);

        new android.os.Handler().postDelayed(new Runnable() {
            /**
             * Starts the main screen after 5 seconds.
             */
            @Override
            public void run() {
                Intent menu = new Intent(SplashActivity.this, TabScreen.class);
                startActivity(menu);

                // Close activity
                finish();
            }
            // Delay for 5 seconds
        }, AppContext.PAUSE);
    }
}
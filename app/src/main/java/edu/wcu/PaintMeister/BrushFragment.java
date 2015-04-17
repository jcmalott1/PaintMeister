package edu.wcu.PaintMeister;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * 
 * @author andrewscott
 * Version v1.0 
 * 
 * Displays a calculatior UI.
 *
 */

public class BrushFragment extends Fragment{
    /**Displays the results**/
    EditText results;
    /**The string to display**/
    StringBuilder str;


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.activity_2paint, container, false);

        return rootView;
    }

}

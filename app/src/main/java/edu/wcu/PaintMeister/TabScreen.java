package edu.wcu.PaintMeister;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
/**
 * Screen that allows user to draw when touching the screen.
 *
 * @author Johnathon Malott, Kevin James
 * @version 4-16-2015
 */
public class TabScreen extends ActionBarActivity {
    // Declare Tab Variable
    ActionBar.Tab Tab1, Tab2;
    Fragment fragmentTab1 = new BrushFragment();
    Fragment fragmentTab2 = new LoadFragment();
    Boolean isbrush = true;

    /**
     * Called when the view is created
     *
     * @param savedInstanceState Most recently supplied data on screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_screen);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab1 = actionBar.newTab().setIcon(R.drawable.ic_paint_brush);
        Tab2 = actionBar.newTab().setIcon(R.drawable.ic_load);
        Tab1.setTabListener(new TabListener(fragmentTab1));
        Tab2.setTabListener(new TabListener(fragmentTab2));

        actionBar.addTab(Tab1);
        actionBar.addTab(Tab2);
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu)
    {
        //Group id, item id, order, title
        MenuItem mnu1 = menu.add(0, 0, 0, "Color Selector");
        {
            mnu1.setIcon(R.drawable.ic_select);
            mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        MenuItem mnu2 = menu.add(0, 1, 1, "Brush Width");
        {
            mnu2.setIcon(R.drawable.ic_brush);
            mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        MenuItem mnu3 = menu.add(0, 2, 2, "Save");
        {
            mnu3.setIcon(R.drawable.ic_save);
            mnu3.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case 0:
                getSelectDialog("Select Brush Color",R.array.colors).show();
                isbrush = true;
                return true;
            case 1:
                getSelectDialog("Select Brush Width",R.array.width).show();
                isbrush = false;
                return true;
            case 2:
                getSaveDialog().show();
                return true;
        }
        return false;
    }

    public Dialog getSelectDialog(String message, int array){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(message)
                .setItems(array,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(isbrush)
                          setBrushColor(getResources().getStringArray(R.array.colors)[which]);
                        else
                          setBrushWidth(getResources().getStringArray(R.array.width)[which]);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public Dialog getSaveDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Would you like to save?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        return builder.create();
    }

    /**
     * Applies given color to brush.
     *
     * @param color Color to be applied.
     */
    public void setBrushColor(String color){
        int brushColor = 0;
        switch(color){
            case "Black":
                brushColor = 0;
                break;
            case "Blue":
                brushColor = 1;
                break;
            case "Red":
                brushColor = 2;
                break;
            case "Magenta":
                brushColor = 3;
                break;
            case "Green":
                brushColor = 4;
                break;
            case "Cyan":
                brushColor = 5;
                break;
            case "Yellow":
                brushColor = 6;
                break;
            case "Dark Grey":
                brushColor = 7;
                break;
        }
        AppContext.BRUSH_COLOR = brushColor;
    }

    /**
     * Applies given color to brush.
     *
     * @param color Color to be applied.
     */
    public void setBrushWidth(String color){
        int brushWidth = 0;
        switch(color){
            case "Small":
                brushWidth = 4;
                break;
            case "Medium":
                brushWidth = 8;
                break;
            case "Large":
                brushWidth = 16;
               break;
        }
        AppContext.DEFAULT_WIDTH = brushWidth;
    }
}

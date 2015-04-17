package edu.wcu.PaintMeister;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Maintains global access to application state.
 *
 * @author Johnathon Malott, Kevin James
 * @version 4-16-2015
 */
public class TabListener implements ActionBar.TabListener {

    Fragment fragment;

    public TabListener(Fragment fragment) {
        // TODO Auto-generated constructor stub
        this.fragment = fragment;
    }

    /**
     * Called when the tab is selected.
     *
     * @param tab Tab that was selected.
     * @param ft A fragment to be displayed.
     */
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        ft.replace(R.id.fragment_container, fragment);
    }

    /**
     * Called when the tab is no longer selected.
     *
     * @param tab Tab that was selected.
     * @param ft A fragment to be displayed.
     */
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        ft.remove(fragment);
    }

    /**
     * Called when screen that is selected is called again.
     *
     * @param tab Tab that was selected.
     * @param ft A fragment to be displayed.
     */
    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }
}
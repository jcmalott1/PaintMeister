package edu.wcu.PaintMeister;

import android.app.Application;
import android.graphics.Color;

/**
 * Maintains global access to application state.
 *
 * @author Johnathon Malott, Kevin James
 * @version 4-16-2015
 */
public class AppContext extends Application {
    //Five second delay
    public static final int PAUSE = 5000;
    //Default width for brush
    public static final int MAX_POINTS = 20;
    public static final float DEFAULT_POINT = -1.0f;
    //Color for the brush
    public static int BRUSH_COLOR = 0;
    public static int DEFAULT_WIDTH = 4;
    /**Different colors to select from*/
    public static int[] COLORS = {Color.BLACK, Color.BLUE, Color.RED, Color.MAGENTA,
            Color.GREEN, Color.CYAN, Color.YELLOW, Color.DKGRAY };
    /**A static reference to this object**/
    private static AppContext app_context;


    /**
     * Called when first created and stores application state information.
     */
    @Override
    public void onCreate()
    {
        app_context = this;
    }

    /**
     * @return Application holding state information.
     */
    public static AppContext getAppContext(){
        return app_context;
    }
}

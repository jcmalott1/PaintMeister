package edu.wcu.PaintMeister;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Handles all the drawling and actions to the screen.
 *
 * @author Johnathon Malott, Kevin James
 * @version 4-16-2015
 */
public class CustomView extends View implements View.OnTouchListener {
    /**Stores x y coordinates*/
    private ArrayList<Float> mX, mY;
    /**Stores line that is drawn on screen.**/
    private ArrayList<Line> lines;
    //
    int counter = 0;
    private Map<String, ArrayList<Line>> drawnLines = new HashMap<>();
    /**Holds current color and style of brush.**/
    Paint paint;

    /**
     * Called when the custom view is first started.
     *
     * @param context Application that is being drawn to.
     * @param attrs Attributes in XML.
     */
    public CustomView(Context context, AttributeSet attrs) {
        super(context,attrs);
        //Handle user interaction to screen.
        this.setOnTouchListener(this);
        //Style for brush on screen.
        paint = new Paint();
        //Default color for brush.
        paint.setColor(Color.BLUE);
        //Default brush width.
        paint.setStrokeWidth(AppContext.DEFAULT_WIDTH);

        lines = new ArrayList<>();
        mX = new ArrayList<>(AppContext.MAX_POINTS);
        mY = new ArrayList<>(AppContext.MAX_POINTS);

        for(int i = 0; i < AppContext.MAX_POINTS;i++)
        {
            mX.add(AppContext.DEFAULT_POINT);
            mY.add(AppContext.DEFAULT_POINT);
        }
    }


    /**
     * Draws the brush to the view.
     */
    protected void onDraw(Canvas canvas) {
        if(lines != null){
            drawnLines(lines, canvas);
        }

    }

    private void drawnLines(ArrayList<Line> draw, Canvas canvas){
        for(Line line : draw)
        {
            //setting color for each brush
            paint.setColor(AppContext.COLORS[AppContext.BRUSH_COLOR]);
            paint.setStrokeWidth(AppContext.DEFAULT_WIDTH);
            //Make brush stroke smooth
            paint.setFlags(Paint.ANTI_ALIAS_FLAG);
            canvas.drawLine(line.x1, line.y1, line.x2, line.y2, paint);
            counter++;
        }
    }

    /**
     * Record x and y coordinate where user touches.
     *
     * @param x The x coordinate
     * @param y The y coordinate.
     * @param v View being touched.
     * @param index Location of coordinate in list.
     *
    public void touchDown(float x, float y,View v,int index){
    mX.set(index, x);
    mY.set(index, y);
    }


    /**
     * When the users finger is dragged.
     *
     * @param x The x coordinate
     * @param y The y coordinate.
     * @param v The view touched.
     * @param index Location of coordinate in list.
     *
    public void touchMove(float x, float y, View v,int index){
    Line line = new Line(x,y,mX.get(index),mY.get(index),index);
    lines.add(line);
    mX.set(index, x);
    mY.set(index, y);
    }

    /**
     * When the users finger is lifted.
     *
     * @param x The x coordinate
     * @param y The y coordinate.
     * @param v The view touched
     * @param index Location of coordinate in list.
     *
    public void touchUp(float x, float y,View v,int index){


    }*/

    /**
     * Called when the screen is touched.
     *
     * @param v View where touch occurred.
     * @param event Contains information about touch event.
     * @return True successful completion.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Amount of touches on screen
        int p_count = event.getPointerCount();
        //Record pointer information on up or down
        int index1 = event.getActionIndex();
        //Record actual pointer
        int index = event.getPointerId(index1);

        //Find action that is being performed
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            {
                int i = index%event.getPointerCount();
                float x = event.getX(i);
                float y = event.getY(i);
                mX.set(i, x);
                mY.set(i, y);
            }
            break;
            case MotionEvent.ACTION_MOVE:
                for (int size = event.getPointerCount() , i = 0; i < size; i++)
                {
                    float x = event.getX(i);
                    float y = event.getY(i);
                    //touchMove(x, y,v, pointerID);
                    {
                        Line line = new Line(x,y,mX.get(i),mY.get(i),i);
                        lines.add(line);
                    }
                    mX.set(i, x);
                    mY.set(i, y);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                // touchUp(x, y, v, pointerID);
                break;
        }
        invalidate();
        return true;
    }

    /**
     * Store information of line that is being drawn.
     */
    public class Line{
        public float x1,y1,x2,y2;
        int pointerID;

        /**
         * Line being drawn on view.
         *
         * @param x1 First x coordinate.
         * @param y1 First y coordinate.
         * @param x2 Second x coordinate.
         * @param y2 Second y coordinate
         * @param pointerID Location of coordinates in list.
         */
        public Line (float x1, float y1, float x2, float y2,int pointerID){
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.pointerID = pointerID;
        }
    }
}

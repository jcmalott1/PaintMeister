package edu.wcu.PaintMeister;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;



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

    /**
     * This method draws the recorded lines.
     *
     * @param draw The list of lines that have been drawn
     * @param canvas The given canvas on which to draw lines
     */
    private void drawnLines(ArrayList<Line> draw, Canvas canvas){
        Paint linepaint = new Paint();
        for(Line line : draw)
        {
            Log.e("COLOR_ID", "" + line.getColorID());
            //Setting color for each brush
            if (line.getColorID() == AppContext.BRUSH_COLOR)
                linepaint.setColor(AppContext.COLORS[AppContext.BRUSH_COLOR]);
            else
                linepaint.setColor(AppContext.COLORS[line.getColorID()]);
            //Setting the width for each brush
            if (line.getWidth() == AppContext.DEFAULT_WIDTH)
                linepaint.setStrokeWidth(AppContext.DEFAULT_WIDTH);
            else
                linepaint.setStrokeWidth(line.getWidth());
            //Make brush stroke smooth
            linepaint.setFlags(Paint.ANTI_ALIAS_FLAG);
            canvas.drawLine(line.x1, line.y1, line.x2, line.y2, linepaint);
        }
    }

    /**
     * Called when the screen is touched.
     *
     * @param v View where touch occurred.
     * @param event Contains information about touch event.
     * @return True successful completion.
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
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
                        Line line = new Line(x,y,mX.get(i),mY.get(i),i, AppContext.BRUSH_COLOR,
                                            AppContext.DEFAULT_WIDTH);
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
        /** The x and x coordinates **/
        public float x1,y1,x2,y2;
        /** The pointer ID **/
        int pointerID;
        /** The color of the line **/
        int color;
        /** The width of the line **/
        int width;

        /**
         * Line being drawn on view.
         *
         * @param x1 First x coordinate.
         * @param y1 First y coordinate.
         * @param x2 Second x coordinate.
         * @param y2 Second y coordinate
         * @param pointerID Location of coordinates in list.
         * @param color The number for the color of the line
         * @param width The width of the line
         */
        public Line (float x1, float y1, float x2, float y2, int pointerID, int color, int width){
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.pointerID = pointerID;
            this.color = color;
            this.width = width;
        }

        /**
         * This is a simple getter method for the color of the line.
         *
         * @return the color of the line
         */
        public int getColorID() {
            return this.color;
        }

        /**
         * This is a simple getter method for the width of the line
         *
         * @return the width of the line
         */
        public int getWidth() {
            return this.width;
        }
    }
}

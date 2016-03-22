package amelia.ameliadoodler;

/**
 * Created by dmcguest on 3/18/16.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by ameliamlu12 on 3/8/2016.
 */
public class DoodleView extends View {

    private Paint paintDoodle = new Paint();
    private Paint canvasPaint = new Paint(Paint.DITHER_FLAG);
    private Path path = new Path();
    private Canvas canvas;
    private Bitmap bitmap;
    private int currRed=0, currGreen=0, currBlue=255;
    private int currAlpha=255;
    private int currBrush = 10;
    private int currWidth, currHeight;
    private boolean rectDraw = false;

    public DoodleView(Context context) {
        super(context);
        init(null,0);
    }

    public DoodleView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle){
        paintDoodle.setColor(Color.BLUE);
        paintDoodle.setAntiAlias(true);
        paintDoodle.setStyle(Paint.Style.STROKE);
        paintDoodle.setStrokeWidth(10);
        //These two attributes make the drawing look smoother
        //http://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-touch-interaction--mobile-19202
        paintDoodle.setStrokeJoin(Paint.Join.ROUND);
        paintDoodle.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight){
        currWidth = width;
        currHeight = height;
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, 0, 0, canvasPaint);
        canvas.drawPath(path, paintDoodle);
        canvas.drawPath(path, paintDoodle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        //special feature, draws in rectangles instead of lines
        if(rectDraw){
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_MOVE:
                    //do not draw the path of motion, only the point of release
                    break;
                case MotionEvent.ACTION_UP:
                    canvas.drawRect(touchX - 5, touchY - 5, touchX + 5, touchY + 5, paintDoodle);
                    path.reset();
                    break;
            }
        } else { //regular mode
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(touchX,touchY);
                    break;
                case MotionEvent.ACTION_UP:
                    canvas.drawPath(path, paintDoodle);
                    path.reset();
                    break;
            }
        }

        invalidate();
        return true;
    }

    public void setColor(int alpha, int red, int green, int blue){
        invalidate();
        currRed=red;
        currGreen=green;
        currBlue=blue;
        paintDoodle.setColor(Color.argb(alpha, red, green, blue));
    }

    public void setBrushSize(int size){
        invalidate();
        currBrush = size;
        paintDoodle.setStrokeWidth(size);
    }

    //Make an entirely new bitmap and canvas to draw on, set current configuration
    public void clear()
    {
        bitmap = Bitmap.createBitmap(currWidth, currHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        path = new Path();
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        paintDoodle = new Paint();
        paintDoodle.setAntiAlias(true);
        paintDoodle.setDither(true);
        paintDoodle.setColor(Color.argb(currAlpha, currRed, currGreen, currBlue));
        paintDoodle.setStyle(Paint.Style.STROKE);
        paintDoodle.setStrokeJoin(Paint.Join.ROUND);
        paintDoodle.setStrokeCap(Paint.Cap.ROUND);
        paintDoodle.setStrokeWidth(currBrush);
        invalidate();
    }

    public void toggleMode(){
        rectDraw = !rectDraw;
    }
}


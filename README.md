# AndroidDoodler
A small android application for drawing, with a few simple features
Created for CMSC434: Human Computer Interaction, University of Maryland, Spring 2016

activity_doodler.xml: provides the GridLayout of the entire app. A basic overview is that there is a drawing canvas on the entire top of the screen, then a grid layout for each row of options. RGB, opacity, and line thickness are each controlled by a SeekBar, while clear is a button and changing modes (to rectangle draw) is a toggle. 

Doodler.java: the main driver of the app. Sets the content view, tracks each seekBar and button, including onClick and onChange listeners for each. The onButtonClick method handles both the clear button and the mode toggler. ColorListener changes color and opacity in real-time when any of the corresponding seek bars are updated. The line thickness onChange listener is set in the onCreate function. 

DoodleView.java: contains the drawing logic of the app. Utilizes a combination of paint, path, canvas, and bitmap to do all of the drawing. The init function sets up the blank canvas and the default thickness (10) and color (blue), and sets anti-aliasing, StrokeJoin: Round, and StrokeCap: Round to make the lines smoother. The onTouchEvent method does the drawing itself. In the case of regular mode, it simply moves to the cursor, draws a line when dragged, and draws a path upon lifting. In the case of rectanle mode, no line is drawn when in motion, and only upon lifting does a rectangle get drawn, centered around the cursor. The setColor, setBrushSize, and toggleMode functions each set the corresponding variables when called from listeners in the Doodler class. The clear function creates an entirely new bitmap and canvas, sets the variables to the most recent values, and resets the smoothing effects.

References

Jon's <a href="https://www.youtube.com/watch?v=ktbYUrlN_Ws">Custom Views in Android</a> video

Jon's <a href="https://youtu.be/2-mmH_nOE9Q">calculator</a> video

<a href="http://www.101apps.co.za/index.php/articles/android-s-seekbar-a-tutorial.html">Android SeekBar Tutorial</a>

A similar <a href="http://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-touch-interaction--mobile-19202">Doodle App</a>, which turned out to be less useful than Jon's videos

A number of stackoverflow pages, including these: <a href="http://stackoverflow.com/questions/7344497/android-canvas-draw-rectangle">Drawing Rectangles</a>, <a href="http://stackoverflow.com/questions/11320044/how-to-set-a-textviews-background-color-to-be-holo-green-light-via-xml">Setting Background Color</a>

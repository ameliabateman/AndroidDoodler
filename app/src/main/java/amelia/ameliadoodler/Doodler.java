package amelia.ameliadoodler;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Doodler extends AppCompatActivity {

    private SeekBar seekR, seekG, seekB, seekA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodler);

        GridLayout grid = (GridLayout) findViewById(R.id.grid);

        /*
        Get seekBar (for line thickness!) reference and set the progress to 10 to reflect initial brush size.
        Attach OnSeekBarChangeListener to call setBrushSize when progress is changed
         */
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                DoodleView doodleView = (DoodleView) findViewById(R.id.doodler);
                doodleView.setBrushSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Get each color seekbar and set change listeners
        seekR = (SeekBar) findViewById(R.id.seekR);
        seekG = (SeekBar) findViewById(R.id.seekG);
        seekB = (SeekBar) findViewById(R.id.seekB);
        seekA = (SeekBar) findViewById(R.id.seekA);

        seekG.setOnSeekBarChangeListener(ColorListener);
        seekB.setOnSeekBarChangeListener(ColorListener);
        seekB.setProgress(100); //Initial color is Color.BLUE
        seekR.setOnSeekBarChangeListener(ColorListener);
        seekA.setOnSeekBarChangeListener(ColorListener);
        seekA.setProgress(100); //initial opacity 100%
    }

    //Listener for the RGB seekbars to change
    //Each is on a range of 0-100, so convert that to 0-255, then call doodleView.setColor
    SeekBar.OnSeekBarChangeListener ColorListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            DoodleView doodleView = (DoodleView) findViewById(R.id.doodler);
            int red = (int)(seekR.getProgress()*2.55);
            int green = (int)(seekG.getProgress()*2.55);
            int blue = (int)(seekB.getProgress()*2.55);
            int alpha = (int)(seekA.getProgress()*2.55);
            doodleView.setColor(alpha,red,green,blue);

            TextView colorSwatch = (TextView) findViewById(R.id.colorSwatch);
            colorSwatch.setBackgroundColor(Color.argb(alpha, red, green, blue));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    //
    public void onButtonClick (View v){
        Button b = (Button)v;
        DoodleView doodleView = (DoodleView) findViewById(R.id.doodler);
        System.out.println(b.getId());
        switch(b.getId()) {
            //Clear
            case 2131492957:
                doodleView.clear();
                break;
            case 2131492958:
                doodleView.toggleMode();
                break;
        }
    }


}

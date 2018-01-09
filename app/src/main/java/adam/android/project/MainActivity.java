package adam.android.project;

import android.content.res.Configuration;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*private GridLayout mainBoardLayout;
    private Button btn = new Button(this);
    private Button btn1;*/

    private TableLayout mainBoardLayout;
    private ConstraintLayout con;
    private Display display;

    private ImageButton square11;
    private ImageButton square12;
    private ImageButton square13;
    private ImageButton square14;
    private ImageButton square15;
    private ImageButton square16;
    private ImageButton square17;
    private ImageButton square18;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBoardLayout = (TableLayout) findViewById(R.id.boardLayout);
        con = (ConstraintLayout) findViewById(R.id.con);

        display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int screenWidth = size.x - (size.x%8);
        int screenHeight = size.y - (size.y%8);

        List<ImageButton> board = new ArrayList<ImageButton>();

        for (int i = 0; i < 8; i++)
        {
            TableRow tableRow = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(lp);
            for (int j = 0; j < 8; j++)
            {
                ImageButton imgButton = new ImageButton(this);
                imgButton.setLayoutParams(new TableRow.LayoutParams(screenWidth/8, screenWidth/8));

                if ((i+j)%2 == 0)
                    imgButton.setBackgroundResource(R.drawable.white_square);
                else
                    imgButton.setBackgroundResource(R.drawable.dark_square);

                tableRow.addView(imgButton);
            }
            mainBoardLayout.addView(tableRow, i);
        }


        /*square11 = (ImageButton) findViewById(R.id.imageButton1);
        square11.setImageResource(R.drawable.white_square);
        square12 = (ImageButton) findViewById(R.id.imageButton2);
        square12.setImageResource(R.drawable.dark_square);
        square13 = (ImageButton) findViewById(R.id.imageButton3);
        square13.setImageResource(R.drawable.white_square);
        square14 = (ImageButton) findViewById(R.id.imageButton4);
        square14.setImageResource(R.drawable.dark_square);
        square15 = (ImageButton) findViewById(R.id.imageButton5);
        square15.setImageResource(R.drawable.white_square);
        square16 = (ImageButton) findViewById(R.id.imageButton6);
        square16.setImageResource(R.drawable.dark_square);
        square17 = (ImageButton) findViewById(R.id.imageButton7);
        square17.setImageResource(R.drawable.white_square);
        square18 = (ImageButton) findViewById(R.id.imageButton8);
        square18.setImageResource(R.drawable.dark_square);

        square11.setAdjustViewBounds(true);
        square12.setAdjustViewBounds(true);
        square13.setAdjustViewBounds(true);
        square14.setAdjustViewBounds(true);
        square15.setAdjustViewBounds(true);
        square16.setAdjustViewBounds(true);
        square17.setAdjustViewBounds(true);
        square18.setAdjustViewBounds(true);

        /*Configuration configuration = getResources().getConfiguration();
        int screenWidth = configuration.screenWidthDp - (configuration.screenWidthDp%8);
        int screenHeight = configuration.screenHeightDp - (configuration.screenHeightDp%8);




        /*mainBoardLayout.setMinimumWidth(screenWidth);
        mainBoardLayout.setMinimumHeight(screenHeight);

        square11.setMaxWidth(screenWidth/8);
        square11.setMaxHeight(screenHeight/8);
        square12.setMaxWidth(screenWidth/8);
        square12.setMaxHeight(screenHeight/8);
        square13.setMaxWidth(screenWidth/8);
        square13.setMaxHeight(screenHeight/8);
        square14.setMaxWidth(screenWidth/8);
        square14.setMaxHeight(screenHeight/8);
        square15.setMaxWidth(screenWidth/8);
        square15.setMaxHeight(screenHeight/8);
        square16.setMaxWidth(screenWidth/8);
        square16.setMaxHeight(screenHeight/8);
        square17.setMaxWidth(screenWidth/8);
        square17.setMaxHeight(screenHeight/8);
        square18.setMaxWidth(screenWidth/8);
        square18.setMaxHeight(screenHeight/8);*/

        /*Log.d("Console", "Hello");*/

    }

}

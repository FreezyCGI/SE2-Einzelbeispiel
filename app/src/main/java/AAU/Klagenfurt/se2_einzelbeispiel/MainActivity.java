package AAU.Klagenfurt.se2_einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final Button btnAbschicken = findViewById("btnAbschicken");
        //btnAbschicken.setOnClickListener(onBtnAbschickenClicked);
    }

    public void onBtnAbschickenClicked(View view)
    {
        Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
    }
}
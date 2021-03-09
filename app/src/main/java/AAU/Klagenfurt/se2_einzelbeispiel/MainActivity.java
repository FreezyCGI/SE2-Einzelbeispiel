package AAU.Klagenfurt.se2_einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final Button btnAbschicken = findViewById("btnAbschicken");
        //btnAbschicken.setOnClickListener(onBtnAbschickenClicked);

    }

    public void onBtnAbschickenClicked(View view) throws InterruptedException
    {
        EditText txtInput = findViewById(R.id.txtInput);
        String input = txtInput.getText().toString();

        TestThread t = new TestThread(input);
        t.start();
        t.join();
        Toast.makeText(this, t.GetResult(), Toast.LENGTH_SHORT).show();
    }
}

class TestThread extends Thread
{
    String output;
    String fromServer;

    public String GetResult()
    {
        return fromServer;
    }

    public TestThread(String output)
    {
        this.output = output;
    }

    public void run()
    {
        try
        {
            Socket clientSocket = new Socket("se2-isys.aau.at", 53212);

            DataOutputStream outToServer = new DataOutputStream((clientSocket.getOutputStream()));
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            outToServer.writeBytes(output + '\n');

            fromServer = inFromServer.readLine();

            clientSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
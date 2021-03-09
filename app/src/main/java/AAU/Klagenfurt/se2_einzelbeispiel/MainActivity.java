package AAU.Klagenfurt.se2_einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity
{
    EditText txtInput;
    TextView txtOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtInput = findViewById(R.id.txtInput);
        txtOutput = findViewById(R.id.txtOutput);
    }

    public void onBtnAbschickenClicked(View view) throws InterruptedException
    {
        String input = txtInput.getText().toString();

        TestThread t = new TestThread(input);
        t.start();
        t.join();

        txtOutput.setText(t.GetResult());
    }

    public void onBtnBerechnenClicked(View view)
    {
        char[] textArray = txtInput.getText().toString().toCharArray();
        Integer quersumme = 0;
        for (int i = 0; i < textArray.length; i++)
        {
            if (i % 2 == 0)
            {
                quersumme += (int) textArray[i];
            }
            else
            {
                quersumme -= (int) textArray[i];
            }
        }
        String stringGerade;
        if (quersumme % 2 == 0)
        {
            stringGerade = "Ja";
        }
        else
        {
            stringGerade = "Nein";
        }
        txtOutput.setText("Quersumme: " + quersumme.toString() + "\n Gerade: " + stringGerade);
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
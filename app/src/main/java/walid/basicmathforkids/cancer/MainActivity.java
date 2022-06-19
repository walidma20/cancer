package walid.basicmathforkids.cancer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button join_us;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        join_us = findViewById(R.id.join_us);
        login = findViewById(R.id.login);

}

    public void join(View view){
        Intent i1 =new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(i1);
    }

    public void log(View view) {
        Intent i2 =new Intent(getApplicationContext(), Main3Activity.class);
        startActivity(i2);
    }

}



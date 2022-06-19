package walid.basicmathforkids.cancer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ViewProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_profile);



    }

    public void changeProfile(View view) {
        startActivity(new Intent(getApplicationContext(),ModifieProfile.class));
    }

    public void resetPass(View view) {
    }

    public void logout(View view) {
    }
}

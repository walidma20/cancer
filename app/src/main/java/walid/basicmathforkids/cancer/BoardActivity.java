package walid.basicmathforkids.cancer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BoardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

}

    public void viewProfile(View view) {
        startActivity(new Intent(getApplicationContext(),ViewProfile.class));
        finish();
    }

    public void call(View view) {

    }

    public void effectSide(View view) {
        startActivity(new Intent(getApplicationContext(),Effect.class));
        finish();
    }
}

package walid.basicmathforkids.cancer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://cancerapp-2b11f-default-rtdb.firebaseio.com/");
    TextView back;
    EditText nmbr,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        back = findViewById(R.id.back);
        nmbr = findViewById(R.id.nmbr);
        password= findViewById(R.id.pss);
        login=findViewById(R.id.log);

        back.setOnClickListener(view -> finish());
    }

    public void loglog(View view) {
        final String nmbrtxt =nmbr.getText().toString();
        final String psstxt =password.getText().toString();
        if (nmbrtxt.isEmpty()||psstxt.isEmpty()){
            Toast.makeText(getApplicationContext(),"please fill all infos",Toast.LENGTH_SHORT).show();}
        else{
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(nmbrtxt)){
                        final String getPassword = snapshot.child(nmbrtxt).child("password").getValue(String.class);
                        if (getPassword.equals(psstxt)){
                            Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),BoardActivity.class));
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(),"wrong password",Toast.LENGTH_SHORT).show();

                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"wrong password",Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }



    }
}



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


public class Main2Activity extends AppCompatActivity {
    //private FirebaseAuth mAuth;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://cancerapp-2b11f-default-rtdb.firebaseio.com/");
    TextView back;
    Button btn;
    EditText name,email,number,pass1,pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        back = findViewById(R.id.back);
        btn= findViewById(R.id.signup);
        name= findViewById(R.id.name);
        email= findViewById(R.id.Email);
        number= findViewById(R.id.number);
        pass1= findViewById(R.id.pass1);
        pass2= findViewById(R.id.pass2);

        back.setOnClickListener(view -> finish());
    }


    public void signUp(View view) {
        final String nametxt =name.getText().toString();
        final String emailtxt = email.getText().toString();
        final String numbertxt = number.getText().toString();
        final String pass1txt = pass1.getText().toString();
        final String pass2txt = pass2.getText().toString();
        if(emailtxt.isEmpty()||numbertxt.isEmpty()||pass1txt.isEmpty()||pass2txt.isEmpty()||nametxt.isEmpty()){
            Toast.makeText(getApplicationContext(),"please fill all informations",Toast.LENGTH_SHORT).show();}
        if(pass1txt.equals(pass2txt)){
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(numbertxt)) {
                        Toast.makeText(getApplicationContext(), "phone all ready registred", Toast.LENGTH_SHORT).show();
                    }else {
                    databaseReference.child("users").child(numbertxt).child("full name").setValue(nametxt);
                    databaseReference.child("users").child(numbertxt).child("Email").setValue(emailtxt);
                    databaseReference.child("users").child(numbertxt).child("password").setValue(pass1txt);
                    startActivity(new Intent(Main2Activity.this,BoardActivity.class));
                    finish();
                }}

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else{
            Toast.makeText(getApplicationContext(),"password not much",Toast.LENGTH_SHORT).show();
        }
    }
}



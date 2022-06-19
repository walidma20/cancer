package walid.basicmathforkids.cancer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class ModifieProfile extends AppCompatActivity {
    private static ImageView imageview;
    private final int currentimage = 1000;
    int image = (R.mipmap.ic_launcher_round);
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifie_profile);
        imageview = findViewById(R.id.imageView);
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

    }

    public void imageChange(View view) {

        Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
        openGalleryIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(openGalleryIntent, currentimage);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == currentimage) && (resultCode == RESULT_OK)) {
            imageview.setImageURI(data.getData());
        }
        if ((requestCode == 1) && (resultCode == RESULT_OK)&&(data!=null)&&(data.getData()!=null)) {
            imageUri=data.getData();
            imageview.setImageURI(imageUri);
            uploadPic();
        }
    }

    private void uploadPic() {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Saving INFOS...");
        final String randomKey = UUID.randomUUID().toString();
// Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageReference.child("images/" + randomKey);
        mountainImagesRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Snackbar.make(findViewById(android.R.id.content), "image saved", Snackbar.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "upload failed", Toast.LENGTH_SHORT).show();

            }});}


    public void Save(View view) {
        choosePicture();

    }

    private void choosePicture() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, 1);
    }
}

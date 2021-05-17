package com.example.eMarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AdminActivity extends AppCompatActivity {

    //Add ours buttons , view
    Button ch,up;
    ImageView img;
    StorageReference mStoregeRef;
    //Universal Resource Identifier need import
    public Uri  imguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        // Reference to Firebase Storage
        mStoregeRef = FirebaseStorage.getInstance().getReference("Images");
        //  XML ID
        ch=(Button)findViewById(R.id.btnchoose);
        up=(Button)findViewById(R.id.btnupload);
        img=(ImageView)findViewById(R.id.imageView2);

        // Event Listener when click in btn for CHOOSE
        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // method
                Filechooser();
            }
        });
        // Event Listener when click in btn for UPLOAD
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Fileuploader();
            }
        });
    }

    private String getExtension(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    // method for upload
    private void Fileuploader(){
        StorageReference Ref =mStoregeRef.child(System.currentTimeMillis()+"."+getExtension(imguri));

        Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //Get a URL to UPLOAD content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl()
                        Toast.makeText(AdminActivity.this, "Image Uploaded correct",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle unsuccessful uploads
                        //..
                    }
                });

    }

    // method for choose
    private void Filechooser(){
        Intent intent = new Intent();
        // select type
        intent.setType("Image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1 );
    }

    /*  onActivityResult
    Denotes that a parameter, field or method return value can be null.
    When decorating a method call parameter, this denotes that the parameter can legitimately be null and the method will gracefully deal with it. Typically used on optional parameters.
    When decorating a method, this denotes the method might legitimately return null.
    This is a marker annotation and it has no specific attributes.
    */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            // Images with  (Universal Resource Identifier ) this imguri
            imguri=data.getData();
            img.setImageURI(imguri);
        }
    }

}
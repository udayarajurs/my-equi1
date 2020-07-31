package com.udayarajurs.equi.LoginActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.udayarajurs.equi.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileFirstPage extends AppCompatActivity implements View.OnClickListener {

    private TextView gallery;
    private TextView camera;
    private TextView cancel;

    private ImageView AddProfile;
    private AlertDialog dialog;
    private AlertDialog.Builder builder;

    private static final int SELECT_PHOTO = 1;
    private static final int CAPTURE_PHOTO= 2; // CAPTURE_PHOTO
    Bitmap thumbnail;

    private ProgressDialog progressBar;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_first_page);

        AddProfile = findViewById(R.id.Add_Profile);

       final AutoCompleteTextView Eduction_autoTextView = findViewById(R.id.Eduction_autoCompleteTextView);
       final AutoCompleteTextView Aspiration_autoTextView = findViewById(R.id.Aspiration_autoCompleteTextView);
       final AutoCompleteTextView Hobbies_autoTextView  = findViewById(R.id.Hobbies_autoCompleteTextView);

        ArrayAdapter<String> adapter_Eduction = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Eduction);
        Eduction_autoTextView.setAdapter(adapter_Eduction);
        Eduction_autoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eduction_autoTextView.showDropDown();
            }
        });
        ArrayAdapter<String> adapter_Aspiration = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Aspiration);
        Aspiration_autoTextView.setAdapter(adapter_Aspiration);
        Aspiration_autoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aspiration_autoTextView.showDropDown();
            }
        });
        ArrayAdapter<String> adapter_Hobbies = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,Hobbies);
        Hobbies_autoTextView.setAdapter(adapter_Hobbies);
        Hobbies_autoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hobbies_autoTextView.showDropDown();
            }
        });

        AddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cretePopup();

            }
        });
    }
    private static final String[] Eduction = new String[] {"Agriculture Science (BScAg,MscAg)" , "Arts (BA, MA)" , "Business Management (BBM, MBA)",
    "Basic Science (BSc, MSc)" , "Commerce (BCom, MCom)" , "Computers (BCA, MCA)" , "Aerospace Engineering " , "Biotechnology/Chemical Engineering ",
    "Civil/Architectural Engineering" , "Computer Science / Information Science " , "Electrical  Engineering (EEE, IT)" , "Electronics / Telecommunication Engineering ",
    "Mechanical Engineering (ME, IP)" , "MBBS/MD" , "Dental Science " , "Veterinary Science"};
    private static final String[] Aspiration = new String[] {"Civil Service Exam - IAS,IPS","CAT /  MBA Entrance Exam" , "GATE / GRE / IES" ,
    "NET Exam" , "Placements" , "Startup's / Entrepreneurship"};
    private static final  String[] Hobbies = new String[] {"Reading", "Going to Movies" , "Computer" , "Listening to Music" , "Shopping" , "Traveling" ,
    "Playing Music" , "Cooking" , "Motorcycling" , "Painting" , "Dancing" , "Other"};

    private void cretePopup() {
        builder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.askingconfirmtoaccesimage, null);

        gallery = view.findViewById(R.id.gallery_access);
        camera = view.findViewById(R.id.camera_access);
        cancel = view.findViewById(R.id.cancel_dialog);

        builder.setView(view);
        dialog = builder.create();
        dialog.show();

        gallery.setOnClickListener(this);
        camera.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gallery_access: // access the gallery
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                dialog.dismiss();
                break;
            case R.id.camera_access: // access the camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAPTURE_PHOTO);
                dialog.dismiss();
                break;
            case R.id.cancel_dialog:
                dialog.dismiss();
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                AddProfile.setEnabled(true);
            }
        }
    }

    public void setProgressBar(){
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Please wait...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        progressBarStatus = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBarStatus < 100){
                    progressBarStatus += 30;

                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progressBarbHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                    });
                }
                if (progressBarStatus >= 100) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.dismiss();
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // requestCode :   ನೀವು ಯಾವ ಉದ್ದೇಶದಿಂದ ಹಿಂತಿರುಗಿದ್ದೀರಿ ಎಂಬುದನ್ನು ಗುರುತಿಸಲು ವಿನಂತಿಯ ಕೋಡ್ ನಿಮಗೆ ಸಹಾಯ ಮಾಡುತ್ತದೆ. ಉದಾಹರಣೆಗೆ
        // ನಿಮ್ಮ ಚಟುವಟಿಕೆ ಎ (ಮುಖ್ಯ ಚಟುವಟಿಕೆ) ಚಟುವಟಿಕೆ ಬಿ (ಕ್ಯಾಮೆರಾ ವಿನಂತಿ), ಚಟುವಟಿಕೆ ಸಿ (ಆಡಿಯೋ ರೆಕಾರ್ಡಿಂಗ್), ಚಟುವಟಿಕೆ ಡಿ (ಸಂಪರ್ಕವನ್ನು ಆರಿಸಿ) ಎಂದು ಕರೆಯಬಹುದು ಎಂದು imagine ಹಿಸಿ.
        if(requestCode == SELECT_PHOTO){
            if(resultCode == RESULT_OK) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri); // getContentResolver(ವಿಷಯ ಪರಿಹಾರಕವನ್ನು ಪಡೆಯಿರಿ)
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    //set Progress Bar
                    setProgressBar();
                    //set profile picture form gallery
                    AddProfile.setImageBitmap(selectedImage);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }else if(requestCode == CAPTURE_PHOTO){
            if(resultCode == RESULT_OK) {
                onCaptureImageResult(data);
            }
        }
    }

    private void onCaptureImageResult(Intent data) {
        thumbnail = (Bitmap) data.getExtras().get("data");

        //set Progress Bar
        setProgressBar();
        //set profile picture form camera
        AddProfile.setMaxWidth(200);
        AddProfile.setMaxHeight(400);
        AddProfile.setImageBitmap(thumbnail);

    }


}
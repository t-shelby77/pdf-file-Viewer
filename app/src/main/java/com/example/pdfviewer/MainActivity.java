package com.example.pdfviewer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

import java.util.List;


import java.io.Console;
import java.io.IOException;
import java.io.InputStream;


import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;


public class MainActivity extends AppCompatActivity {

    private Button btn_upload,btn_select;
    private TextView text_view;

    private int REQ_PDF = 21;
    private String encodedPDF;

    private static final int PICK_PDF_CODE = 1000;
    Button btn_open_storage;
    //Button btn_open_asst,btn_open_storage,btn_open_from_internet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //REQUEST STORAGE PERMISSION

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new BaseMultiplePermissionsListener(){
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        super.onPermissionsChecked(multiplePermissionsReport);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        super.onPermissionRationaleShouldBeShown(list, permissionToken);
                    }
                }).check();


        //btn_open_asst = (Button)findViewById(R.id.btn_open_asst);
        btn_open_storage = (Button)findViewById(R.id.btn_open_storage);
        //btn_open_from_internet = (Button)findViewById(R.id.btn_open_from_internet);
        /*
        btn_open_asst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewActivity.class);
                intent.putExtra("ViewType","assests");
                startActivity(intent);
            }
        });
        */
        btn_open_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browse_pdf = new Intent(Intent.ACTION_GET_CONTENT);
                browse_pdf.setType("application/pdf");
                browse_pdf.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(browse_pdf,"SELECT PDF"),PICK_PDF_CODE);

                uploadDocument();
            }
        });
        /*
        btn_open_from_internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ViewActivity.class);
                intent.putExtra("ViewType","internet");
                startActivity(intent);
            }
        });
        */
    }


    private void uploadDocument() {


        //UploadDocument in available in the Api.java
        Call<ResponsePOJO> call = RetroFitClient.getInstance().getAPI().UploadDocument(encodedPDF);

        call.enqueue(new Callback<ResponsePOJO>() {

            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {


                Toast.makeText(MainActivity.this, response.body().getRemarks(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                t.printStackTrace();

                //Toast.makeText(MainActivity.this, "Network Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //generate override method

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data !=null){
            Uri selectedPDF = data.getData();
            Intent intent = new Intent(MainActivity.this,ViewActivity.class);
            intent.putExtra("ViewType", "Storage");
            intent.putExtra("FileUri",selectedPDF.toString());
            startActivity(intent);
        }
    }
}

package com.example.webapp.ui.fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.example.webapp.R;
import com.example.webapp.data.Config;
import com.example.webapp.data.api.Client;
import com.example.webapp.data.api.Iclient;
import com.example.webapp.data.model.retrofit.Model_login;
import com.example.webapp.data.sharedpreferences.SharedpreferencesUser;
import com.example.webapp.ui.viewModel.Post;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomDialog_add_data extends DialogFragment {

    private Bitmap bitmap;
//    private ImageView imageView;
    private EditText enterTitle, enterNote;
    private static String username;
    private Button btnSubmit;

    SharedpreferencesUser sharedpreferencesUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_data, container, false);
        

        enterTitle = view.findViewById(R.id.enter_title);
        enterNote = view.findViewById(R.id.enter_note);
        btnSubmit = view.findViewById(R.id.btn_add_data);

        sharedpreferencesUser = new SharedpreferencesUser(getContext());


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openImageChooser();
//            }
//        });

        return view;
    }

    private void openImageChooser() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, Config.REQUEST_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            Intent chooser = Intent.createChooser(intent, "انتخاب عکس");
            chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{cameraIntent});

            if (chooser.resolveActivity(requireContext().getPackageManager()) != null) {
                startActivityForResult(chooser, Config.REQUEST_IMAGE_Camera);
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == Config.REQUEST_IMAGE_Camera) {
                Uri imageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
//                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == Config.REQUEST_IMAGE_GALLERY) {
                Uri imageUri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                    Toast.makeText(requireContext(), bitmap + "", Toast.LENGTH_SHORT).show();
//                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(requireContext(), "nulll", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void insertData() {
        if (validateInputs()) {

            //getUsername
            username = sharedpreferencesUser.getUser().getUsername();

            //InsertData
            Iclient api = Client.getClient().create(Iclient.class);

            Call<Model_login> call = api.Insert_data(enterTitle.getText().toString(),enterNote.getText().toString(),username);

            call.enqueue(new Callback<Model_login>() {
                @Override
                public void onResponse(Call<Model_login> call, Response<Model_login> response) {
                }

                @Override
                public void onFailure(Call<Model_login> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });

            dismiss();
        }
    }

    private boolean validateInputs() {
        boolean isValid = true;

        if (enterTitle.getText().toString().trim().isEmpty() && enterNote.getText().toString().trim().isEmpty()) {

            Toast.makeText(getContext(), "مقادیر وارد شده نامعتبر", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
//        if (bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0) {
//            Toast.makeText(getContext(), "مقادیر وارد شده نامعتبر", Toast.LENGTH_SHORT).show();
//            isValid = false;
//        }

        return isValid;
    }


}

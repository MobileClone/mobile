package com.example.mobileapp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class DbConnection{

    public void dbConnection(){
        ClassLoader classLoader = MobileAppApplication.class.getClassLoader();

        try {
            File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());

            FileInputStream serviceAccount =
                    new FileInputStream(file.getAbsolutePath());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://mobile-dafb8-default-rtdb.europe-west1.firebasedatabase.app")
                    .build();

            FirebaseApp.initializeApp(options);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}

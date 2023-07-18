package com.example.mobileapp.user;

import com.example.mobileapp.listing.Listing;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    public static final String COL_NAME="Users";

    public String addUser() throws InterruptedException, ExecutionException {
        User user = new User(1L,"usernamedemo","passworddemo","emaildemo", "phonenumberdemo");
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getId().toString()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    public List<User> getAll(){
        User user1 = new User(1L,
                "username1",
                "password1",
                "user1@gmail.com",
                "088321312");
        User user2 = new User(2L,
                "username2",
                "password2",
                "user2@gmail.com",
                "088324482");
        List<User> listUsers = new ArrayList<>();
        listUsers.add(user1);
        listUsers.add(user2);
        return listUsers;
    }

}

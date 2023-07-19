package com.example.mobileapp.user;

import com.example.mobileapp.listing.Listing;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import io.grpc.netty.shaded.io.netty.channel.unix.Errors;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
@Transactional
public class UserService {

    public static final String COL_NAME="Users";

    public List<User> getAll() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference usersCollection = dbFirestore.collection(COL_NAME);
        ApiFuture<QuerySnapshot> future = usersCollection.get();

        QuerySnapshot querySnapshot = future.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        List<User> userList = new ArrayList<User>();

        for (QueryDocumentSnapshot document : documents) {
            User user = document.toObject(User.class);
            userList.add(user);
        }
        Collections.sort(userList, Comparator.comparing(User::getId));
        return userList;
    }

    public String addUser(User user) throws InterruptedException, ExecutionException {
        List<User> userList = getAll();
        Collections.reverse(userList);
        User user1 = userList.get(0);
        long currentId = user1.getId();
        user.setId(currentId+1);
        user.setRole("User");
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getId().toString()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String getUserRole(Long id) throws ExecutionException, InterruptedException {
        List<User> userList = getAll();
        String role = "";
        for(User user : userList){
            if(id == user.getId()){
                role = user.getRole();
            }
        }
        System.out.println(role);
        return role;
    }

    public boolean isValid(String username, String password) throws ExecutionException, InterruptedException {
        List<User> userList = getAll();
        for (User user : userList){
            if(username.equals(user.getUsername()) || password.equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

}

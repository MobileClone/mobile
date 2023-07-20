package com.example.mobileapp.user;

import com.example.mobileapp.listing.Listing;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.common.hash.Hashing;
import com.google.firebase.cloud.FirestoreClient;
import io.grpc.netty.shaded.io.netty.channel.unix.Errors;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public String getUserToken(String username) throws ExecutionException, InterruptedException {
        List<User> userList = getAll();
        String token = "";
        for(User user : userList){
            if(username.equals(user.getUsername())){
                token = user.getToken();
            }
        }
        return token;
    }

    public Long getUserId(String token) throws ExecutionException, InterruptedException {
        List<User> userList = getAll();
        Long id = 0L;
        for(User user : userList){
            if(token.equals(user.getToken())){
               id = user.getId();
            }
        }
        return id;
    }

    public boolean isValidToken(String token) throws ExecutionException, InterruptedException {
        List<User> userList = getAll();
        boolean check = false;
        for (User user : userList){
            if(token.equals(user.getToken())){
                return true;
            }
        }
        return false;
    }
    public boolean isValid(String username, String password) throws ExecutionException, InterruptedException {
        List<User> userList = getAll();
        boolean check = false;
        for (User user : userList){
            if(username.equals(user.getUsername())){
                check = checkCryptedInformation(user.getPassword(),password);
                if(check){
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public String cryptInformation(String input){
        String pw_hash = BCrypt.hashpw(input, BCrypt.gensalt());
        return pw_hash;
    }

    public boolean checkCryptedInformation(String crypted,String current){
        if (BCrypt.checkpw(current, crypted))
            return true;
        return false;
    }
    public String createSHAHash(String input) throws NoSuchAlgorithmException {

        String hashtext = null;
        String sha256hex = Hashing.sha256()
                .hashString(input, StandardCharsets.UTF_8)
                .toString();

        hashtext = convertToHex(sha256hex);
        return hashtext;
    }
    private String convertToHex(String messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest.getBytes());
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }
    public boolean validUsername(String username){
        Pattern pattern;
        Matcher matcher;
        String USERNAME_PATTERN = "^[A-Za-z0-9]{5,20}$";
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public boolean validPassword(String password){
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = "^(?=.*[A-Za-z].*) + (?=.*[0-9].*)[A-Za-z0-9]{5,20}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean validEmail(String email){
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-z]{2,30}$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String deleteUser(Long id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id.toString()).delete();
        return "Document with Listing ID "+ id +" has been deleted";
    }

    public String updateUser(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getId().toString()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

}

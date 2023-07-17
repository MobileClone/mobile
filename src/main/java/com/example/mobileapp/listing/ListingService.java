package com.example.mobileapp.listing;//package com.example.mobileapp.listing;
//
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ListingService {
//
//    public List<Listing> getListings(){
//
//        return List.of(new Listing(1L, "Audi A8", 8000, "3L engine", "Good condition"));
//    }
//
//    public void addNewListing(Listing listing){
//
//    }
//}

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class ListingService {

    public static final String COL_NAME="users";

    public String addListing() throws InterruptedException, ExecutionException {
        Listing listing = new Listing(4L,"demo",10,"demo","demo");
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(listing.getId().toString()).set(listing);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

}
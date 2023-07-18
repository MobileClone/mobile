package com.example.mobileapp.listing;//package com.example.mobileapp.listing;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//CRUD operations
@Service
public class ListingService {


    public static final String COL_NAME="Listing";


    public String addListing() throws InterruptedException, ExecutionException {
        Listing listing = new Listing("BMW M5", 15000, "3L engine", "Perfect condition");
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(listing.getId().toString()).set(listing);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String saveListing(Listing listing) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(listing.getId().toString()).set(listing);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Listing getListing(Long id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(String.valueOf(id));
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Listing listing = null;

        if(document.exists()) {
            listing = document.toObject(Listing.class);
            return listing;
        }else {
            return null;
        }
    }

    public String updateListing(Listing listing) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(listing.getId().toString()).set(listing);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteListing(Long id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(id.toString()).delete();
        return "Document with Listing ID "+ id +" has been deleted";
    }

    public List<Listing> getAllListings(DataSnapshot dataSnapshot){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        List<Listing> list = new ArrayList<>();
        for(DataSnapshot getSnapshot : dataSnapshot.getChildren()){
            Listing listing = dataSnapshot.getValue(Listing.class);
            list.add(listing);
        }
        return list;
    }
}
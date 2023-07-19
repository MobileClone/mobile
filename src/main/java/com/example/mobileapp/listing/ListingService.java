package com.example.mobileapp.listing;//package com.example.mobileapp.listing;
import com.example.mobileapp.user.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//CRUD operations
@Service
public class ListingService {


    public static final String COL_NAME="Listing";

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

    public List<Listing> getAllListings() throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference listingCollection = dbFirestore.collection(COL_NAME);
        ApiFuture<QuerySnapshot> future = listingCollection.get();

        QuerySnapshot querySnapshot = future.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        List<Listing> listingList = new ArrayList<Listing>();

        for (QueryDocumentSnapshot document : documents) {
            Listing listing = document.toObject(Listing.class);
            listingList.add(listing);
        }
        Collections.sort(listingList, Comparator.comparing(Listing::getId));
        return listingList;
    }

    public String createListing(Listing listing) throws InterruptedException, ExecutionException {
        List<Listing> listingList = getAllListings();
        Collections.reverse(listingList);
        Listing listing1 = listingList.get(0);
        long currentId = listing1.getId();
        listing.setId(currentId+1);
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(listing.getId().toString()).set(listing);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public boolean validYear(String year){
        Pattern pattern;
        Matcher matcher;
        String USERNAME_PATTERN = "^[0-9]{4}$";
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(year);
        return matcher.matches();
    }
            public void carbrands(){
            Map<String, List<String>> carBrandsMap = new HashMap<>();

            // Add car brands and their models
            carBrandsMap.put("Toyota", new ArrayList<>(List.of("Camry", "Corolla", "Rav4")));
            carBrandsMap.put("Honda", new ArrayList<>(List.of("Accord", "Civic", "CR-V")));
            carBrandsMap.put("Ford", new ArrayList<>(List.of("Mustang", "Focus", "F-150")));
            carBrandsMap.put("Chevrolet", new ArrayList<>(List.of("Cruze", "Malibu", "Silverado")));
            carBrandsMap.put("BMW", new ArrayList<>(List.of("3 Series", "5 Series", "X5")));

            // Access the map and print the brands and models
            for (Map.Entry<String, List<String>> entry : carBrandsMap.entrySet()) {
                String brand = entry.getKey();
                List<String> models = entry.getValue();

                System.out.println(brand + ": " + models);
            }

        }
    }
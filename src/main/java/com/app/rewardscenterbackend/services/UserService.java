package com.app.rewardscenterbackend.services;

import com.app.rewardscenterbackend.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {
    public static final String COL_NAME = "users";

    public List<User> getAllCustomers() throws ExecutionException, InterruptedException {
        List<User> customerList = new ArrayList<User>();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference customers = dbFirestore.collection(COL_NAME);
        ApiFuture<QuerySnapshot> querySnapshot = customers.get();
        User user;
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            try {
                if (doc.exists()) {
                    user = doc.toObject(User.class);
                    assert user != null;
                    user.setId(doc.getId());
                    customerList.add(user);
                } else {
                    return null;
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return customerList;
    }

    public User getCustomerDetails(String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docReference = dbFirestore.collection(COL_NAME).document(id);
        User user = null;
        if (docReference.getId().equals(id)) {
            ApiFuture<DocumentSnapshot> docSnapshot = docReference.get();
            DocumentSnapshot documentSnapshot = docSnapshot.get();
            user = documentSnapshot.toObject(User.class);
            return user;
        } else {
            return null;
        }
    }


    public String saveCustomerDetails(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getName()).set(user);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String updateCustomerDetails(User customers) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document().set(customers);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteCustomer(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
        return "Document with Customer ID " + name + " has been deleted";
    }

}

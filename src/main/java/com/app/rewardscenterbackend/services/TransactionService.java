package com.app.rewardscenterbackend.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.app.rewardscenterbackend.entity.Transactions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

    @Service
    public class TransactionService {
        public static final String COL_NAME = "users";
        public static final String SUB_COL_NAME = "transactions";

        public List<Transactions> getAllTransactions(String id) throws ExecutionException, InterruptedException {
            List<Transactions> transactionsList = new ArrayList<Transactions>();
            Firestore dbFirestore = FirestoreClient.getFirestore();
            CollectionReference transactionRef = dbFirestore.collection(COL_NAME).document(id).collection(SUB_COL_NAME);
            ApiFuture<QuerySnapshot> querySnapshot = transactionRef.get();
            Transactions transactions = null;
            for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
                try {
                    if (doc.exists()) {
                        transactions = doc.toObject(Transactions.class);
                        assert transactions != null;
                        transactions.setId(doc.getId());
                        transactionsList.add(transactions);
                    } else {
                        return null;
                    }
                } catch (NullPointerException e) {
                }
            }
            return transactionsList;
        }

}

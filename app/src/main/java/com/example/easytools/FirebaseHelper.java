package com.example.easytools;


import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * The purpose of this class is to hold ALL the code to communicate with Firebase.  This class
 * will connect with Firebase auth and Firebase firestore.  Each class that needs to verify
 * authentication OR access data from the database will reference a variable of this class and
 * call a method of this class to handle the task.  Essentially this class is like a "gopher" that
 * will go and do whatever the other classes want or need it to do.  This allows us to keep all
 * our other classes clean of the firebase code and also avoid having to update firebase code
 * in many places.  This is MUCH more efficient and less error prone.
 */
public class FirebaseHelper {
    public final String TAG = "Denna";
    private static String uid = null;      // var will be updated for currently signed in user
    private final FirebaseAuth mAuth;
    private final FirebaseFirestore db;
    private final ArrayList<Tool> myTools;
    // we don't need this yet
    // private ArrayList<Memory> myItems = new ArrayList<>();


    public FirebaseHelper() {
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        myTools = new ArrayList<>();

    }


    public FirebaseAuth getmAuth() {
        return mAuth;
    }
    //logs out user

    public void logOutUser() {
        mAuth.signOut();
        uid = null;
    }

    //
    public void updateUid(String uid) {
        FirebaseHelper.uid = uid;
    }
    public void attachReadDataToUser() {
        // This is necessary to avoid the issues we ran into with data displaying before it
        // returned from the asynch method calls
        if (mAuth.getCurrentUser() != null) {
            uid = mAuth.getUid();
            readData(new FirestoreCallback() {
                @Override
                public void onCallback(ArrayList<Tool> memoryList) {
                    Log.d(TAG, "Inside attachReadDataToUser, onCallback " + memoryList.toString());
                }
            });
        }
        else {
            Log.d(TAG, "No one logged in");
        }
    }

    //creating a user object so we can add it to firestore
    public void addUserToFirestore(String name, String newUID) {
        // Create a new user with their name
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        // Add a new document with a docID = to the authenticated user's UID
        db.collection("users").document(newUID)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, name + "'s user account added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding user account", e);
                    }
                });
    }

    public void addData(Tool t) {
        // add Memory m to the database
        // this method is overloaded and incorporates the interface to handle the asynch calls
        addData(t, new FirestoreCallback() {
            @Override
            public void onCallback(ArrayList<Tool> myList) {
                Log.i(TAG, "Inside addData, onCallback :" + myTools.toString());
            }
        });
    }


    private void addData(Tool t, FirestoreCallback firestoreCallback) {
        db.collection("allTools")
                .add(t)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // This will set the docID key for the Memory that was just added.
                        db.collection("allTools")
                                .document(documentReference.getId()).update("docID", documentReference.getId());
                        Log.i(TAG, "just added " + t.getName());
                        readData(firestoreCallback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error adding document", e);
                    }
                });
    }


    public ArrayList<Tool> getToolArrayList() {
        return myTools;
    }



/* https://www.youtube.com/watch?v=0ofkvm97i0s
This video is good!!!   Basically he talks about what it means for tasks to be asynchronous
and how you can create an interface and then using that interface pass an object of the interface
type from a callback method and access it after the callback method.  It also allows you to delay
certain things from occurring until after the onSuccess is finished.
*/

    private void readData(FirestoreCallback firestoreCallback) {
        myTools.clear();        // empties the AL so that it can get a fresh copy of data
        db.collection("users").document(uid).collection("allTools")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot doc: task.getResult()) {
                                Tool tool = doc.toObject(Tool.class);
                                Log.i(TAG, "the document is : "+ doc);

                                myTools.add(tool);
                            }

                            Log.i(TAG, "Success reading data: "+ myTools.toString());
                            firestoreCallback.onCallback(myTools);
                        }
                        else {
                            Log.d(TAG, "Error getting documents: " + task.getException());
                        }
                    }
                });
    }


    //https://stackoverflow.com/questions/48499310/how-to-return-a-documentsnapshot-as-a-result-of-a-method/48500679#48500679
    public interface FirestoreCallback {
        void onCallback(ArrayList<Tool> myList);
    }

    public void editData(Tool m) {
        // edit Memory m to the database
        // this method is overloaded and incorporates the interface to handle the asynch calls
        editData(m, new FirestoreCallback() {
            @Override
            public void onCallback(ArrayList<Tool> myList) {
                Log.i(TAG, "Inside editData, onCallback " + myList.toString());
            }
        });
    }

    private void editData(Tool m, FirestoreCallback firestoreCallback) {
        String docId = m.getDocID();
        db.collection("users").document(uid).collection("allTools")
                .document(docId)
                .set(m)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, "Success updating document");
                        readData(firestoreCallback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error updating document", e);
                    }
                });
    }

    public void deleteData(Tool m) {
        // delete item w from database
        // this method is overloaded and incorporates the interface to handle the asynch calls
        deleteData(m, new FirestoreCallback() {
            @Override
            public void onCallback(ArrayList<Tool> myList) {
                Log.i(TAG, "Inside deleteData, onCallBack" + myList.toString());
            }
        });

    }

    private void deleteData(Tool m, FirestoreCallback firestoreCallback) {
        // delete item w from database
        String docId = m.getDocID();
        db.collection("users").document(uid).collection("allTools")
                .document(docId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.i(TAG, m.getName() + " successfully deleted");
                        readData(firestoreCallback);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "Error deleting document", e);
                    }
                });
    }
}



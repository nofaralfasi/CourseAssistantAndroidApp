package com.example.nofar.finalProject;
import android.app.Application;

import com.example.nofar.finalProject.LOGIC.Core.User;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserClient extends Application {

    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

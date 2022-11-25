package com.example.easytools;
import android.os.Parcel;
import android.os.Parcelable;

public class RealUser implements Parcelable {
    private String name;
    private String docID;


    // add boolean for status
    // add user id for checked out

    public RealUser(String name, String docID) {
        this.name = name;
        this.docID = docID;

    }

    public RealUser(String name, String desc, String userID) {
        this.name = name;
        this.docID = "No docID yet";

    }


    // A default constructor is required for the Parceable interface to work
    public RealUser() {
        name = "No name";
        this.docID = "No docID yet";

    }

    /** This is a "constructor" of sorts that is needed with the Parceable interface to
     * tell the intent how to create a Memory object when it is received from the intent
     * basically it is setting each instance variable as a String or Int
     *
     * MAKE SURE THE ORDER OF THESE VARS IS CONSISTENT WITH ALL CONSTRUCTOR TYPE METHODS
     * @param parcel    the parcel that is received from the intent
     */

    public RealUser(Parcel parcel) {
        name = parcel.readString();

        docID = parcel.readString();

    }

    /**
     * This is what is used when we send the Memory object through an intent
     * It is also a method that is part of the Parceable interface and is needed
     * to set up the object that is being sent.  Then, when it is received, the
     * other Memory constructor that accepts a Parcel reference can "unpack it"
     *
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);

        dest.writeString(docID);

    }


    // this code is needed for the Memory class to work with Parcelable
    public static final Parcelable.Creator<RealUser> CREATOR = new
            Parcelable.Creator<RealUser>() {

                @Override
                public RealUser createFromParcel(Parcel parcel) {
                    return new RealUser(parcel);
                }

                @Override
                public RealUser[] newArray(int size) {
                    return new RealUser[0];
                }
            };


    /**
     * This method is required for the Parceable interface.  As of now, this method
     * is in the default state and doesn't really do anything.
     *
     * If your Parcelable class will have child classes, you'll need to
     * take some extra care with the describeContents() method. This would
     * let you identify the specific child class that should be created by
     * the Parcelable.Creator. You can read more about how this works on
     *  Stack Overflow with this link.
     *           https://stackoverflow.com/questions/4778834/purpose-of-describecontents-of-parcelable-interface
     * @return
     */

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public String getDocID() {
        return docID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }
}


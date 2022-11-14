package com.example.easytools;
import android.os.Parcel;
import android.os.Parcelable;

public class Tool implements Parcelable {
    private String name, desc;
    private String docID;
    private String userID;
    // add boolean for status
    // add user id for checked out

    public Tool(int rating, String name, String desc, String docID) {
        this.name = name;
        this.desc = desc;
        this.docID = docID;
        this.userID = userID;
    }

    public Tool(int rating, String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.docID = "No docID yet";
        this.userID = "No userID yet";
    }


    // A default constructor is required for the Parceable interface to work
    public Tool() {
        name = "No name";
        desc = "No desc";
        this.docID = "No docID yet";
        this.userID = "No userID yet";
    }

    /** This is a "constructor" of sorts that is needed with the Parceable interface to
     * tell the intent how to create a Memory object when it is received from the intent
     * basically it is setting each instance variable as a String or Int
     *
     * MAKE SURE THE ORDER OF THESE VARS IS CONSISTENT WITH ALL CONSTRUCTOR TYPE METHODS
     * @param parcel    the parcel that is received from the intent
     */

    public Tool(Parcel parcel) {
        name = parcel.readString();
        desc = parcel.readString();
        docID = parcel.readString();
        userID = parcel.readString();
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
        dest.writeString(desc);
        dest.writeString(docID);
        dest.writeString(userID);

    }


    // this code is needed for the Memory class to work with Parcelable
    public static final Parcelable.Creator<Tool> CREATOR = new
            Parcelable.Creator<Tool>() {

                @Override
                public Tool createFromParcel(Parcel parcel) {
                    return new Tool(parcel);
                }

                @Override
                public Tool[] newArray(int size) {
                    return new Tool[0];
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

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }

    public String getDocID() {
        return docID;
    }
}


package com.bp.footballcommunity;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

/**
 * Created by BP on 2017/5/30.
 */
public class Contact {
    private String mName;
    private boolean mOnline;
    private Integer mId;
    private int mContactType;
//    private static ArrayList<Contact> mContacts;

    public Contact(String name,boolean online,int contactType){
        mName = name;
        mOnline = online;
        mContactType = contactType;
    }

    public String getName() {
        return mName;
    }

    public boolean isOnline() {
        return mOnline;
    }

    public Integer getId() {
        return hashCode();
    }

    public int getContactType() {
        return mContactType;
    }

    private static int lastContactId = 0;
    public static ArrayList<Contact> createContactsList(int numContacts){
        ArrayList<Contact> contacts = new ArrayList<Contact>();

        for(int i = 1; i <= numContacts; i++){
            contacts.add(new Contact("Person"+ ++lastContactId,i<=numContacts/2,lastContactId%2));
        }
        return contacts;
    }

//    public static ArrayList<Contact> addAll(ArrayList<Contact> newcontacts){
//        if(null==mContacts){
//            mContacts = new ArrayList<Contact>();
//        }
//        for(int i = 0;i < newcontacts.size();i++){
//            mContacts.add(newcontacts.get(i));
//        }
//        return mContacts;
//    }
}

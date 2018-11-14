package com.adel.profile.profileproject;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePerManager {
    private SharedPreferences sharedPreferences;

    public SharePerManager (Context context){

        sharedPreferences= context.getSharedPreferences("profile",Context.MODE_PRIVATE);
    }


    public void saveFullName(String firstName, String lastName){

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("first_name",firstName);
        edit.putString("last_name",lastName);
        edit.commit();
    }

    public String getFirstName(){
        return sharedPreferences.getString("first_name",null);
    }

    public String getLastName(){
        return sharedPreferences.getString("last_name",null);
    }
}

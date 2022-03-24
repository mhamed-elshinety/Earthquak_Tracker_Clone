package com.example.earthquaketrackerclone.other;

import android.content.res.Resources;

public abstract class Assistant {

    //Assistant method, helps us to get a string resource without needing to context or activity
    public static String getStringResource (int stringId){
        return App.getContext().getResources().getString(stringId);
    }
}


package com.projeto.clubedowhisky;

import android.app.Application;
import android.text.TextUtils;
import android.util.Patterns;
import java.util.regex.Pattern;

public class Helper extends Application {
    public boolean isValidEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidLogin(String login) {
        if (Pattern.compile("^([a-zA-Z]{4,24})?([a-zA-Z][a-zA-Z0-9_]{4,24})$", 2).matcher(login).matches()) {
            return true;
        }
        return false;
    }

    public boolean isValidSearchQuery(String query) {
        if (Pattern.compile("^([a-zA-Z]{1,24})?([a-zA-Z][a-zA-Z0-9_]{1,24})$", 2).matcher(query).matches()) {
            return true;
        }
        return false;
    }

    public boolean isValidPassword(String password) {
        if (Pattern.compile("^[a-z0-9_]{6,24}$", 2).matcher(password).matches()) {
            return true;
        }
        return false;
    }
}

package com.example.fooddoor;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FavManager {

    private static final String PREF_NAME = "FavPrefs";
    private static final String KEY_FAV_IDS_PREFIX = "fav_ids_";

    // ⭐ saare items ka in-memory cache (name -> FoodItem)
    private static final Map<String, FoodItem> ITEM_CACHE = new HashMap<>();

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // ⭐ current user ke liye unique key (email ke base pe)
    private static String getUserKey(Context context) {
        SharedPreferences userSp =
                context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE);

        String email = userSp.getString("email", null);

        // agar email nahi mila to fallback ek common key
        if (email == null || email.isEmpty()) {
            return KEY_FAV_IDS_PREFIX + "guest";
        } else {
            // eg: fav_ids_user1@gmail.com
            return KEY_FAV_IDS_PREFIX + email;
        }
    }

    // saare favourite IDs (sirf current user ke)
    public static Set<String> getFavIds(Context context) {
        SharedPreferences prefs = getPrefs(context);
        String key = getUserKey(context);

        Set<String> saved = prefs.getStringSet(key, new HashSet<>());
        return new HashSet<>(saved);  // copy
    }

    // kya yeh particular item current user ka favourite hai?
    public static boolean isFavourite(Context context, String id) {
        return getFavIds(context).contains(id);
    }

    // current user ke liye fav add/remove (toggle)
    public static void toggleFavourite(Context context, String id) {
        SharedPreferences prefs = getPrefs(context);
        String key = getUserKey(context);

        Set<String> favs = getFavIds(context);

        if (favs.contains(id)) {
            favs.remove(id);   // unfav
        } else {
            favs.add(id);      // fav
        }

        prefs.edit().putStringSet(key, favs).apply();
    }

    // ⭐ har item ko register karo (adapter se call ho raha hai)
    public static void registerItem(FoodItem item) {
        if (item != null && item.getName() != null) {
            ITEM_CACHE.put(item.getName(), item);
        }
    }

    // ⭐ current user ke liye FoodItem objects ki fav list
    public static List<FoodItem> getFavouriteItems(Context context) {
        Set<String> favIds = getFavIds(context);
        List<FoodItem> result = new ArrayList<>();

        for (String id : favIds) {
            FoodItem fi = ITEM_CACHE.get(id);
            if (fi != null) {
                result.add(fi);
            }
        }
        return result;
    }

    // ⭐ optional: delete account pe current user ke favourites bhi clear karna ho
    public static void clearCurrentUserFavourites(Context context) {
        SharedPreferences prefs = getPrefs(context);
        String key = getUserKey(context);
        prefs.edit().remove(key).apply();
    }
}
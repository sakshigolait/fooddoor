package com.example.fooddoor.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import com.example.fooddoor.LoginActivity;
import com.example.fooddoor.R;
import com.example.fooddoor.edit_profile_fragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    CircleImageView imgProfile;
    TextView tvChangePhoto, tvName, tvMyName, tvPhone, tvEmail, tvAddress;

    DrawerLayout drawerLayout;
    ImageView imgMenu;

    private static final int PICK_IMAGE = 100;
    Uri imageUri;

    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        imgProfile = view.findViewById(R.id.imgProfile);
        tvChangePhoto = view.findViewById(R.id.tvChangePhoto);
        tvName = view.findViewById(R.id.tvName);
        tvMyName = view.findViewById(R.id.tvMyName);
        tvPhone = view.findViewById(R.id.tvPhone);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvAddress = view.findViewById(R.id.tvAddress);

        drawerLayout = view.findViewById(R.id.drawerLayout);
        imgMenu = view.findViewById(R.id.imgmenu);

        sharedPreferences = requireActivity().getSharedPreferences("UserProfile", Activity.MODE_PRIVATE);

        loadUserData();

        tvChangePhoto.setOnClickListener(v -> openGallery());

        return view;
    }

    // ⭐⭐ NEW METHOD ADDED ⭐⭐
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Drawer open/close
        imgMenu.setOnClickListener(v -> {
            if (!drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.openDrawer(GravityCompat.END);
            } else {
                drawerLayout.closeDrawer(GravityCompat.END);
            }
        });

        // ⭐ Edit Profile Click Event
        TextView tvEditProfile = view.findViewById(R.id.tvEditProfile);
        tvEditProfile.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.END);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.homeframelayout, new edit_profile_fragment())
                    .addToBackStack(null)
                    .commit();
        });

        // ⭐ Send Feedback Click Event
        TextView tvSendFeedback = view.findViewById(R.id.tvSendFeedback);
        tvSendFeedback.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.END);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.homeframelayout, new SendFeedbackFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // ⭐ Terms & Conditions Click Event
        TextView tvTerms = view.findViewById(R.id.tvTerms);
        tvTerms.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.END);

            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.homeframelayout, new TermsConditionsFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // ⭐ Logout Click Event
        TextView tvLogout = view.findViewById(R.id.tvLogout);
        tvLogout.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.END);

            new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                    .setTitle("Logout")
                    .setMessage("Do you want to logout?")
                    .setPositiveButton("Logout", (dialog, which) -> {

                        Intent i = new Intent(requireActivity(), LoginActivity.class);
                        // optional: back stack clear karne ke liye
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        requireActivity().finish();
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .show();
        });

        // ⭐ Delete Account Click Event
        TextView tvDeleteAccount = view.findViewById(R.id.tvDeleteAccount);
        tvDeleteAccount.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.END);

            new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                    .setTitle("Delete Account")
                    .setMessage("Are you sure you want to delete your account?")
                    .setPositiveButton("Delete", (dialog, which) -> {

                        //  User profile data clear
                        SharedPreferences userPrefs = requireActivity()
                                .getSharedPreferences("UserProfile", Activity.MODE_PRIVATE);
                        userPrefs.edit().clear().apply();

                        //  Login screen open + back stack clear
                        Intent i = new Intent(requireActivity(), LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        requireActivity().finish();
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                    .show();
        });

        ImageView imgFav = view.findViewById(R.id.imgfav);
        imgFav.setOnClickListener(v -> {
            drawerLayout.closeDrawer(GravityCompat.END); // agar drawer open ho

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.homeframelayout, new FavouriteFragment())
                    .addToBackStack(null)
                    .commit();
        });


    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        gallery.addCategory(Intent.CATEGORY_OPENABLE);
        gallery.setType("image/*");
        gallery.addFlags(
                Intent.FLAG_GRANT_READ_URI_PERMISSION |
                        Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
        );
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {

            imageUri = data.getData();

            if (imageUri != null) {
                try {
                    final int takeFlags = data.getFlags()
                            & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                            | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                    requireActivity()
                            .getContentResolver()
                            .takePersistableUriPermission(imageUri, takeFlags);
                } catch (Exception ignored) {}

                imgProfile.setImageURI(imageUri);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("profile_image", imageUri.toString());
                editor.apply();
            }
        }
    }

    private void loadUserData() {
        String name = sharedPreferences.getString("name", "Jack Sparrow");
        String phone = sharedPreferences.getString("phone", "9657352104");
        String email = sharedPreferences.getString("email", "jacksparrow24@gmail.com");
        String address = sharedPreferences.getString("address", "Amravati");
        String image = sharedPreferences.getString("profile_image", null);

        tvName.setText(name);
        tvMyName.setText(name);
        tvPhone.setText(phone);
        tvEmail.setText(email);
        tvAddress.setText(address);

        if (image != null && !image.isEmpty()) {
            try {
                imgProfile.setImageURI(Uri.parse(image));
            } catch (SecurityException se) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("profile_image");
                editor.apply();
                imgProfile.setImageResource(R.drawable.chockletvenellaicecream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
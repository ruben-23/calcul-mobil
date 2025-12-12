package com.example.laborator3;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageFragment extends Fragment {

    private ImageView imageView;
    private TextView textView;

    public ImageFragment() {

    }

    public static ImageFragment newInstance(int imageResId, String description) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt("imageResId", imageResId);
        args.putString("description", description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        imageView = view.findViewById(R.id.imageView);
        textView = view.findViewById(R.id.textView);

        if (getArguments() != null) {
            int imageResId = getArguments().getInt("imageResId");
            String description = getArguments().getString("description");
            imageView.setImageResource(imageResId);
            textView.setText(description);
        }

        return view;
    }
}

package com.daniil.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class FragmentIntro extends Fragment {

    // Оголосив інтерфейс для слухання подій натискання кнопки "Старт"
    public interface OnStartButtonClickListener {
        void onStartButtonClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        Button startButton = view.findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Повідомив слухача про натискання кнопки "Старт"
                if (getActivity() instanceof OnStartButtonClickListener) {
                    ((OnStartButtonClickListener) getActivity()).onStartButtonClick();
                }
            }
        });

        return view;
    }
}
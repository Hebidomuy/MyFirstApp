package com.daniil.myapplication;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentIntro.OnStartButtonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Показати фрагмент з фото та описом
            showIntroFragment();
        }
    }

    private void showIntroFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new FragmentIntro())
                .commit();
    }

    @Override
    public void onStartButtonClick() {
        // Натискання кнопки "Старт" у фрагменті викликає цей метод
        // Замінити фрагмент на карту
        replaceFragment(new FragmentMap());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

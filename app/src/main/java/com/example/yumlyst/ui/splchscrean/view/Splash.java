package com.example.yumlyst.ui.splchscrean.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yumlyst.MainActivity;
import com.example.yumlyst.R;
import com.example.yumlyst.ui.authentecation.UserCashing;

public class Splash extends Fragment {

    private Handler handler;
    private Runnable navigateRunnable;
    private UserCashing userCashing;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.hideNavigationBottom();
        }

        userCashing = UserCashing.getInstance(requireContext()); // تأكد من أن `Context` غير `null`

        LottieAnimationView lottieView = view.findViewById(R.id.lottieAnimationView);
        lottieView.playAnimation();
        lottieView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                lottieView.pauseAnimation();
            }
        });

        handler = new Handler(Looper.getMainLooper());
        navigateRunnable = () -> {
            if (!isAdded()) return; // تأكد أن `Fragment` ما زال موجودًا قبل التنقل

            NavController navController = Navigation.findNavController(view);
            if (userCashing.isUserLoggedIn()) {  // استبدال الشرط بـ `isUserLoggedIn()`
                navController.navigate(R.id.action_splach_to_home2);
            } else {
                navController.navigate(R.id.action_splach_to_firstScreen);
            }
        };

        handler.postDelayed(navigateRunnable, 3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (handler != null) {
            handler.removeCallbacks(navigateRunnable);
        }
    }
}

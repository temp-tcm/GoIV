package com.kamron.pogoiv.utils.fractions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FractionManager {

    private Context themedContext;
    private ViewGroup containerView;
    private Fraction currentFraction;


    public FractionManager(Context context,
                           @StyleRes int themeResId,
                           @NonNull ViewGroup containerView) {
        themedContext = new ContextThemeWrapper(context, themeResId);
        this.containerView = containerView;
    }

    public void show(Fraction fraction) {
        if (hasFractionViewAttached()) {
            removeFractionView();
        }
        if (currentFraction != null) {
            currentFraction.onDestroy();
        }

        View fractionRootView = addFractionView(fraction);
        fraction.onCreate(fractionRootView);
        currentFraction = fraction;
    }

    private boolean hasFractionViewAttached() {
        return containerView.getChildCount() > 0;
    }

    private void removeFractionView() {
        containerView.removeView(containerView.getChildAt(0));
    }

    private View addFractionView(@NonNull Fraction fraction) {
        return LayoutInflater.from(themedContext)
                .inflate(fraction.getLayoutResId(), containerView);
    }

}

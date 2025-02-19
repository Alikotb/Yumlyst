package com.example.yumlyst.network.authentecation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;

public class NetworkUtils {
    private final Context context;
    private final NetworkChangeListener listener;
    private NetworkCallback networkCallback;

    public NetworkUtils(Context context, NetworkChangeListener listener) {
        this.context = context;
        this.listener = listener;
        registerNetworkCallback();
    }

    private void registerNetworkCallback() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Android 7.0+ - Use NetworkCallback
            networkCallback = new NetworkCallback();
            NetworkRequest networkRequest = new NetworkRequest.Builder().build();
            cm.registerNetworkCallback(networkRequest, networkCallback);
        } else {
            // Older Android versions - Use BroadcastReceiver
            IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            context.registerReceiver(networkReceiver, filter);
        }
    }

    public void unregisterNetworkCallback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null && networkCallback != null) {
                cm.unregisterNetworkCallback(networkCallback);
            }
        } else {
            context.unregisterReceiver(networkReceiver);
        }
    }

    // NetworkCallback for API 24+
    private class NetworkCallback extends ConnectivityManager.NetworkCallback {
        @Override
        public void onAvailable(Network network) {
            super.onAvailable(network);
            listener.onNetworkChanged(true);
        }

        @Override
        public void onLost(Network network) {
            super.onLost(network);
            listener.onNetworkChanged(false);
        }
    }

    // BroadcastReceiver for Android <7.0
    private final BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            listener.onNetworkChanged(isConnected(context));
        }
    };

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network network = cm.getActiveNetwork();
                if (network == null) return false;
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
                return capabilities != null &&
                        (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
            } else {
                android.net.NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                return activeNetwork != null && activeNetwork.isConnected();
            }
        }
        return false;
    }

    // Interface for callback
    public interface NetworkChangeListener {
        void onNetworkChanged(boolean isConnected);
    }
}

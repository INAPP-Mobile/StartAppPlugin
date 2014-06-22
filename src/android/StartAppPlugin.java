package com.startapp.cordova;

import java.util.ArrayList;
import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;

public class StartAppPlugin extends CordovaPlugin {
	public static final String TAG = "StartAppCordova";
	private static final boolean DEBUG = true;

	public static final String ACTION_INIT = "init";
	public static final String ACTION_SHOW = "show";

	private CallbackContext mCallbackCtx;
	private StartAppAd startAppAd;
	
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
	}

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {
		if (DEBUG) {
			Log.d(TAG, "execute | action=" + action);
		}
		try {
			if (ACTION_INIT.equals(action)) {
				JSONObject arg_object = args.getJSONObject(0);
				final String devId = arg_object.getString("devId");
				if (DEBUG) {
					Log.d(TAG, "devId=" + devId);
				}
				final String appId = arg_object.getString("appId");
				if (DEBUG) {
					Log.d(TAG, "appId=" + appId);
				}

				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						StartAppSDK.init(cordova.getActivity(), devId, appId);
						startAppAd = new StartAppAd(cordova.getActivity());
					}
				});
				callbackContext.success();
				return true;
			} else if (ACTION_SHOW.equals(action)) {
				this.mCallbackCtx = callbackContext;
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						startAppAd.onBackPressed();
						this.mCallbackCtx.success();
					}
				});
				return true;
			}
			callbackContext.error("Invalid action");
			return false;
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			callbackContext.error(e.getMessage());
			return false;
		}
	}

	@Override
	public void onResume(boolean keepRunning) {
		super.onResume(keepRunning);
		startAppAd.onResume();
	}
	
	@Override
	public void onPause(boolean keepRunning) {
		super.onPause(keepRunning);
		startAppAd.onPause();
	}
	
}

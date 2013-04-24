/**
 *  PhoneStateChangeListener cordova plugin (Android)
 * 
 * 	@author Stéfano Stypulkows Zanata
 * 	@see http://szanata.com
 *  @reference https://github.com/madeinstefano/PhoneStateChangeListener
 * 	@license MIT <http://szanata.com/MIT.txt>
 * 	@license GNU <http://szanata.com/GNU.txt>
 *  
 * 	Based upon PhoneListener by authored by Tommy-Carlos Williams <https://github.com/devgeeks>
 * 
 */
package com.szanata.cordova.plugins;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * 
 *
 */
public class PhoneStateChangeListener extends CordovaPlugin {

	private static final String TAG = "PhoneStateChangeListener";
	private Context context;
	private CallbackContext callbackContext;
	private BroadcastReceiver receiver = null;
	
	/**
	 * Expecting no parameters from javascript
	 * Expecting only success callback from javascript
	 */
	@Override
	public boolean execute(final String action,final JSONArray args, final CallbackContext callbackContext) throws JSONException {	
		
		this.context = cordova.getActivity().getApplicationContext();
		
		if ("start".equals(action)) {
			this.callbackContext = callbackContext;
			startPhoneListener();
			return true;
			
		}else if ("stop".equals(action)) {
			removePhoneListener();
			this.callbackContext = null;
			return true;
		}
		
		return false;
	}

	/**
	 * creates a new BroadcastReceiver to listen whether the Telephony State changes
	 */
	public void startPhoneListener() {
		
		if (this.receiver == null) {
			this.receiver = new BroadcastReceiver() {

				@Override
				public void onReceive(final Context context, final Intent intent) {	

					if(intent != null && intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
						String state = intent.hasExtra(TelephonyManager.EXTRA_STATE) ? intent.getStringExtra(TelephonyManager.EXTRA_STATE) : "NONE";
			            
			            if (callbackContext != null){
			            	callbackContext.success(state);
			            }
					}
					
				}
			};
			this.context.registerReceiver(this.receiver, new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED));
		}
	}
	
	
	/**
     * removes the Receiver
     */
    private void removePhoneListener() {
        if (this.receiver != null) {
            try {
                this.context.unregisterReceiver(this.receiver);
                this.receiver = null;
            } catch (final Exception e) {
                Log.e(TAG, "Error unregistering phone listener receiver: " + e.getMessage(), e);
            }
        }
    }
	

	public void onDestroy() {
		removePhoneListener();
	}
}

package com.gunghi.tgwing.sausage.service;

/**
 * Created by joyeongje on 2017. 8. 26..
 */

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.gunghi.tgwing.sausage.model.User;


/**
 * Created by joyeongje on 2017. 7. 16..
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        saveTokenIn(refreshedToken);
    }
    // [END refresh_token]

    private void saveTokenIn(final String token) {
        User.staticUserToken = token;

       // SharedPreferences lolockLocalData = getSharedPreferences("SausageLocalData", MODE_PRIVATE);
       // SharedPreferences.Editor editor = lolockLocalData.edit();
       // editor.putString("deviceId", token); //First라는 key값으로 infoFirst 데이터를 저장한다.
       // editor.apply(); //완료한다.

    }
}
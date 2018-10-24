package uizacoresdk.listerner;

import com.google.ads.interactivemedia.v3.api.player.VideoAdPlayer;

import vn.uiza.core.utilities.LLog;

/**
 * Created by loitp on 4/12/2018.
 */

public class VideoAdPlayerListerner implements VideoAdPlayer.VideoAdPlayerCallback {
    //private final String TAG = getClass().getSimpleName();
    private final String TAG = VideoAdPlayerListerner.class.getSimpleName();

    private boolean isPlayingAd;
    private boolean isEnded;

    @Override
    public void onPlay() {
        LLog.d(TAG, "onPlay");
        isPlayingAd = true;
    }

    @Override
    public void onVolumeChanged(int i) {
        LLog.d(TAG, "onVolumeChanged");
    }

    @Override
    public void onPause() {
        LLog.d(TAG, "onPause");
        isPlayingAd = false;
    }

    @Override
    public void onLoaded() {
        LLog.d(TAG, "onLoaded");
    }

    @Override
    public void onResume() {
        LLog.d(TAG, "onResume");
        isPlayingAd = true;
    }

    @Override
    public void onEnded() {
        LLog.d(TAG, "onEnded");
        isPlayingAd = false;
        isEnded = true;
    }

    @Override
    public void onError() {
        LLog.d(TAG, "onError");
        isPlayingAd = false;
    }

    public boolean isPlayingAd() {
        return isPlayingAd;
    }

    public boolean isEnded() {
        return isEnded;
    }
}
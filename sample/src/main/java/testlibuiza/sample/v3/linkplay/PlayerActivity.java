package testlibuiza.sample.v3.linkplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import testlibuiza.R;
import uizacoresdk.interfaces.UZCallback;
import uizacoresdk.interfaces.UZItemClick;
import uizacoresdk.model.UZCustomLinkPlay;
import uizacoresdk.util.UZDataCLP;
import uizacoresdk.util.UZUtil;
import uizacoresdk.view.rl.video.UZVideo;
import vn.uiza.core.base.BaseActivity;
import vn.uiza.core.common.Constants;
import vn.uiza.core.exception.UZException;
import vn.uiza.core.utilities.LLog;
import vn.uiza.core.utilities.LUIUtil;
import vn.uiza.restapi.uiza.model.v3.linkplay.getlinkplay.ResultGetLinkPlay;
import vn.uiza.restapi.uiza.model.v3.metadata.getdetailofmetadata.Data;
import vn.uiza.views.LToast;

/**
 * Created by loitp on 7/16/2018.
 */

public class PlayerActivity extends BaseActivity implements UZCallback, UZItemClick {
    private UZVideo uzVideo;
    private EditText etLinkPlay;
    private Button btPlay;

    @Override
    protected boolean setFullScreen() {
        return false;
    }

    @Override
    protected String setTag() {
        return "TAG" + getClass().getSimpleName();
    }

    @Override
    protected int setLayoutResourceId() {
        return R.layout.player_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        UZUtil.setCurrentPlayerId(R.layout.uz_player_skin_1);
        UZUtil.setCasty(this);
        super.onCreate(savedInstanceState);
        uzVideo = (UZVideo) findViewById(R.id.uiza_video);
        etLinkPlay = (EditText) findViewById(R.id.et_link_play);
        btPlay = (Button) findViewById(R.id.bt_play);
        btPlay.setEnabled(false);
        uzVideo.addUZCallback(this);
        uzVideo.addItemClick(this);

        etLinkPlay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence == null || charSequence.toString() == null || charSequence.toString().isEmpty()) {
                    btPlay.setEnabled(false);
                } else {
                    btPlay.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        final UZCustomLinkPlay uZCustomLinkPlay0 = new UZCustomLinkPlay();
        uZCustomLinkPlay0.setLinkPlay("https://bitmovin-a.akamaihd.net/content/MI201109210084_1/mpds/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.mpd");
        uZCustomLinkPlay0.setLivestream(false);

        final UZCustomLinkPlay uZCustomLinkPlay1 = new UZCustomLinkPlay();
        uZCustomLinkPlay1.setLinkPlay("http://112.78.4.162:8081/live/stream/playlist_dvr.m3u8");
        uZCustomLinkPlay1.setLivestream(true);

        final UZCustomLinkPlay uZCustomLinkPlay2 = new UZCustomLinkPlay();
        uZCustomLinkPlay2.setLinkPlay("http://118.69.82.182:112/this-is-thopp-live-pull-only-live/htv7-hd/playlist_dvr_timeshift-0-1800.m3u8");
        uZCustomLinkPlay2.setLivestream(true);

        final UZCustomLinkPlay uZCustomLinkPlay3 = new UZCustomLinkPlay();
        uZCustomLinkPlay3.setLinkPlay("https://s3-ap-southeast-1.amazonaws.com/cdnetwork-test/drm_sample_byterange/manifest.mpd");
        uZCustomLinkPlay3.setLivestream(false);

        findViewById(R.id.bt_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UZDataCLP.getInstance().setUzCustomLinkPlay(uZCustomLinkPlay0);
                etLinkPlay.setText(UZDataCLP.getInstance().getUzCustomLinkPlay().getLinkPlay());
                LUIUtil.setLastCursorEditText(etLinkPlay);
            }
        });
        findViewById(R.id.bt_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UZDataCLP.getInstance().setUzCustomLinkPlay(uZCustomLinkPlay1);
                etLinkPlay.setText(UZDataCLP.getInstance().getUzCustomLinkPlay().getLinkPlay());
                LUIUtil.setLastCursorEditText(etLinkPlay);
            }
        });
        findViewById(R.id.bt_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UZDataCLP.getInstance().setUzCustomLinkPlay(uZCustomLinkPlay2);
                etLinkPlay.setText(UZDataCLP.getInstance().getUzCustomLinkPlay().getLinkPlay());
                LUIUtil.setLastCursorEditText(etLinkPlay);
            }
        });
        findViewById(R.id.bt_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UZDataCLP.getInstance().setUzCustomLinkPlay(uZCustomLinkPlay3);
                etLinkPlay.setText(UZDataCLP.getInstance().getUzCustomLinkPlay().getLinkPlay());
                LUIUtil.setLastCursorEditText(etLinkPlay);
            }
        });
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInitSuccess = UZUtil.initLinkPlay(activity, uzVideo);
                if (!isInitSuccess) {
                    LToast.show(activity, "Init failed");
                }
            }
        });
        LLog.d(TAG, "getClickedPip " + UZUtil.getClickedPip(activity));
        if (UZUtil.getClickedPip(activity)) {
            btPlay.performClick();
        }
    }

    @Override
    public void isInitResult(boolean isInitSuccess, boolean isGetDataSuccess, ResultGetLinkPlay resultGetLinkPlay, Data data) {
    }

    @Override
    public void onItemClick(View view) {
        switch (view.getId()) {
            case R.id.exo_back_screen:
                if (!uzVideo.isLandscape()) {
                    onBackPressed();
                }
                break;
        }
    }

    @Override
    public void onClickPipVideoInitSuccess(boolean isInitSuccess) {
        if (isInitSuccess) {
            onBackPressed();
        }
    }

    @Override
    public void onSkinChange() {
    }

    @Override
    public void onScreenRotate(boolean isLandscape) {
    }

    @Override
    public void onError(UZException e) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uzVideo.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        uzVideo.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        uzVideo.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        uzVideo.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        uzVideo.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            if (resultCode == Activity.RESULT_OK) {
                uzVideo.initializePiP();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (uzVideo.isLandscape()) {
            uzVideo.toggleFullscreen();
        } else {
            super.onBackPressed();
        }
    }
}

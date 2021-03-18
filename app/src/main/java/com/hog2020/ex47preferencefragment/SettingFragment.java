package com.hog2020.ex47preferencefragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SettingFragment extends PreferenceFragment {
    SharedPreferences pref;

    //일반 프레그먼트와 다르게
    //설정 화면을 직접 설계하는 방식이 아니라
    //설정항목을 만들면 자동으로 그에 맞게 화면이 만들어짐
    //onCreate()에서 작업하지 않음

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //별도의 화면 layout 파일을 사용하지 않고
        //설정 xml 문서를 통해 화면 자동생성
        addPreferencesFromResource(R.xml.setting);

        //ShardPreference 객체를 참조하여 설정상태에 대한 제어
        pref= PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean isVibrate =pref.getBoolean("vibrate",false);
        Toast.makeText(getActivity(), ""+isVibrate, Toast.LENGTH_SHORT).show();
    }

    //이 프레그먼트가 화면에 보여질때 자동 실행 되는메소드

    @Override
    public void onResume() {
        super.onResume();

        //설정 값 변경 리스너 등록
        pref.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();

        pref.unregisterOnSharedPreferenceChangeListener(listener);
    }

    //설정값 변경 리스너 멤버변수
    SharedPreferences.OnSharedPreferenceChangeListener listener =new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("message")){
               boolean b=pref.getBoolean("message",false);
                Toast.makeText(getActivity(), ""+b, Toast.LENGTH_SHORT).show();
            }
        }
    };


}

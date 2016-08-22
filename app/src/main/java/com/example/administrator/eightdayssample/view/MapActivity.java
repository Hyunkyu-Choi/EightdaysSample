package com.example.administrator.eightdayssample.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.eightdayssample.R;
import com.example.administrator.eightdayssample.databinding.ActivityMapBinding;
import com.example.administrator.eightdayssample.presenter.MainPresenter;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

/**
 * Created by Administrator on 2016-08-17.
 */
public class MapActivity extends AppCompatActivity implements MainPresenter.View {

    private MainPresenter mainPresenter;

    private ActivityMapBinding mapBinding;

    private MapView mapView;
    private ImageView btnCurrentLocation;
    private EditText etLocation;
    private ImageView btnSearch;
    private View calloutBalloon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapBinding = DataBindingUtil.setContentView(this, R.layout.activity_map);

        mainPresenter = new MainPresenter(this);
        initMapView();
        initBtnSearch();
        initBtnCurrentLocation();
    }

    private void initMapView() {
        mapView = new MapView(this);
        mapView.setDaumMapApiKey(MainPresenter.API_KEY);
        calloutBalloon = getLayoutInflater().inflate(R.layout.custom_callout_balloon, null);
        mapView.setCalloutBalloonAdapter(new CalloutBalloonAdapter() {
            @Override
            public View getCalloutBalloon(MapPOIItem mapPOIItem) {
                mainPresenter.getCalloutBalloon(mapPOIItem, calloutBalloon);
                return calloutBalloon;
            }

            @Override
            public View getPressedCalloutBalloon(MapPOIItem mapPOIItem) {
                return null;
            }
        });

        ViewGroup mapViewContainer = mapBinding.mapView;
        mapViewContainer.addView(mapView);
    }

    private void initBtnSearch() {
        etLocation = mapBinding.etLocation;
        btnSearch = mapBinding.btnSearch;

        btnSearch.setOnClickListener(view -> {
            String query = etLocation.getText().toString();
            if (query == null || query.length() == 0) {
                Log.e("Error", "검색어 입력해야 함");
                return;
            }
            hideSoftKeyboard();
            mainPresenter.search(mapView, query);
        });
    }

    private void initBtnCurrentLocation() {
        btnCurrentLocation = mapBinding.btnCurrentLocation;
        btnCurrentLocation.setOnClickListener(view -> mainPresenter.getCurrentLocation());
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etLocation.getWindowToken(), 0);
    }

    @Override
    public void updateMapLocation(double latitude, double longitude) {
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true);
    }
}

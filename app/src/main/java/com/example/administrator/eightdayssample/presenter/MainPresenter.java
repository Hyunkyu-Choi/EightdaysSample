package com.example.administrator.eightdayssample.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.eightdayssample.utils.GpsInfo;
import com.example.administrator.eightdayssample.R;
import com.example.administrator.eightdayssample.search.Item;
import com.example.administrator.eightdayssample.search.OnFinishSearchListener;
import com.example.administrator.eightdayssample.search.Searcher;
import com.example.administrator.eightdayssample.view.MapActivity;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016-08-17.
 */

public class MainPresenter {

    private View view;
    private Context context;
    public static final String API_KEY = "05615d1e82049b532eaf2e1e3b4e11fb";
    private HashMap<Integer, Item> tagItemMap;

    public MainPresenter(MapActivity mapActivity) {
        this.view = mapActivity;
        this.context = mapActivity;
        tagItemMap = new HashMap<Integer, Item>();
    }

    public void search(MapView mapView, String query) {
        MapPoint.GeoCoordinate geoCoordinate = mapView.getMapCenterPoint().getMapPointGeoCoord();
        double latitude = geoCoordinate.latitude; // 위도
        double longitude = geoCoordinate.longitude; // 경도
        int radius = 10000; // 중심 좌표부터의 반경거리. 특정 지역을 중심으로 검색하려고 할 경우 사용. meter 단위 (0 ~ 10000)
        int page = 1; // 페이지 번호 (1 ~ 3). 한페이지에 15개

        Searcher searcher = new Searcher();
        searcher.searchKeyword(this.context, query, latitude, longitude, radius, page, API_KEY, new OnFinishSearchListener() {
            @Override
            public void onSuccess(List<Item> itemList) {
                mapView.removeAllPOIItems(); // 기존 검색 결과 삭제
                updatePOIitem(mapView, itemList); // 검색 결과 보여줌
            }

            @Override
            public void onFail() {
                Log.e("Error", "API_KEY의 제한 트래픽이 초과되었습니다.");
            }
        });
    }

    private void updatePOIitem(MapView mapView, List<Item> itemList) {
        MapPointBounds mapPointBounds = new MapPointBounds();

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);

            MapPOIItem poiItem = new MapPOIItem();
            poiItem.setItemName(item.title);
            poiItem.setTag(i);
            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(item.latitude, item.longitude);
            poiItem.setMapPoint(mapPoint);
            mapPointBounds.add(mapPoint);
            poiItem.setMarkerType(MapPOIItem.MarkerType.BluePin);
            poiItem.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin);
            poiItem.setCustomImageAutoscale(false);
            poiItem.setCustomImageAnchor(0.5f, 1.0f);

            mapView.addPOIItem(poiItem);
            tagItemMap.put(poiItem.getTag(), item);
        }

        mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds));

        selectPOIitem(mapView);
    }

    private void selectPOIitem(MapView mapView) {
        MapPOIItem[] poiItems = mapView.getPOIItems();
        if (poiItems.length > 0) {
            mapView.selectPOIItem(poiItems[0], false);
        }
    }

    public void getCalloutBalloon(MapPOIItem mapPOIItem, android.view.View calloutBalloon) {
        if (mapPOIItem != null) {
            Item item = tagItemMap.get(mapPOIItem.getTag());
            if (item != null) {
                TextView textViewTitle = (TextView) calloutBalloon.findViewById(R.id.title);
                textViewTitle.setText(item.title);
                TextView textViewDesc = (TextView) calloutBalloon.findViewById(R.id.desc);
                textViewDesc.setText(item.address);
            }
        }
    }

    public void getCurrentLocation() {
        GpsInfo gpsInfo = new GpsInfo(context);

        if (gpsInfo.isGetLocation()) {
            double latitude = gpsInfo.getLatitude();
            double longitude = gpsInfo.getLongitude();

            view.updateMapLocation(latitude, longitude);
        } else {
            gpsInfo.showSettingsAlert();
        }

        gpsInfo.stopUsingGPS();
    }

    public interface View {
        void updateMapLocation(double latitude, double longitude);
    }
}

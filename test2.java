package com.inwhoop.xbzhjypt.gis.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.inwhoop.xbzhjypt.gis.fragment.HistoryLineFragment;
import com.inwhoop.xbzhjypt.gis.fragment.LocationFragment;
import com.inwhoop.xbzhjypt.main.activity.BaseFragmentActivity;
import com.xbzhjypt.activity.R;
这行是测试滴


public class CampusSafeActivity extends BaseFragmentActivity implements
		OnClickListener {

	private TextView locationTextView, lineTextView;
	
	private LocationFragment locationFragment = null;
	
	private HistoryLineFragment historyLineFragment = null;
	
	private FragmentTransaction mFt;
	
	public String sreialnumber = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.campaus_safe_layout);
		mContext = CampusSafeActivity.this;
		init();
	}

	@Override
	protected void init() {
		super.init();
		settitle("校园安全");
		setHeadleft(R.drawable.back_bg);
		locationTextView = (TextView) findViewById(R.id.location);
		locationTextView.setOnClickListener(this);
		lineTextView = (TextView) findViewById(R.id.line);
		lineTextView.setOnClickListener(this);
		setItem(0);
	}

	private void setItem(int position) {
		mFt = getSupportFragmentManager().beginTransaction();
		if(null!=locationFragment&&null!=locationFragment.mapView){
			locationFragment.mapView.onPause();
			locationFragment.mapView.onDestroy();
		}
		if(null!=historyLineFragment&&null!=historyLineFragment.mapView){
			historyLineFragment.mapView.onPause();
			historyLineFragment.mapView.onDestroy();
		}
//		if(null!=locationFragment){
//			mFt.hide(locationFragment);
//			locationFragment.mapView.onPause();
//			locationFragment.mapView.onDestroy();
//		}
//		if(null!=historyLineFragment){
//			mFt.hide(historyLineFragment);
//			historyLineFragment.mapView.onPause();
//			
//		}
		if(position == 0){
			setHeadrightTextviewyc();
			locationTextView.setTextColor(getResources().getColor(R.color.white));
			locationTextView.setBackgroundResource(R.drawable.item_slect_bg);
			lineTextView.setTextColor(getResources().getColor(R.color.left_menu_textcolor));
			lineTextView.setBackgroundDrawable(null);
//			if(null==locationFragment){
				locationFragment = new LocationFragment();
				mFt.add(R.id.mapframelayout, locationFragment);
//			}else{
//				mFt.show(locationFragment);
//				locationFragment.mapView.onResume();
//			}
		}else{
			setHeadrightTextview("时间选择");
			headrightTextView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					historyLineFragment.showPopupWindow();
				}
			});
			locationTextView.setTextColor(getResources().getColor(R.color.left_menu_textcolor));
			locationTextView.setBackgroundDrawable(null);
			lineTextView.setTextColor(getResources().getColor(R.color.white));
			lineTextView.setBackgroundResource(R.drawable.item_slect_bg);
//			if(null==historyLineFragment){
				historyLineFragment = new HistoryLineFragment();
				mFt.add(R.id.mapframelayout, historyLineFragment);
//			}else{
//				mFt.show(historyLineFragment);
//				historyLineFragment.mapView.onResume();
//			}
		}
		mFt.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		mFt.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.location:
			setItem(0);
			break;

		case R.id.line:
			setItem(1);
			break;

		default:
			break;
		}
	}

}

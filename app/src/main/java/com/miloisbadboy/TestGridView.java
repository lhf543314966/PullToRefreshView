package com.miloisbadboy;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.miloisbadboy.view.PullToRefreshView3;
import com.miloisbadboy.view.PullToRefreshView3.OnFooterRefreshListener;
import com.miloisbadboy.view.PullToRefreshView3.OnHeaderRefreshListener;

public class TestGridView extends Activity implements OnHeaderRefreshListener, OnFooterRefreshListener {
	PullToRefreshView3 mPullToRefreshView;
	GridView mGridView;
	private LayoutInflater inflater;
	private List<Integer> listDrawable = new ArrayList<Integer>();
	private MyAdapter adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_gridview);
		
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		listDrawable.add(R.drawable.pic1);
		listDrawable.add(R.drawable.pic1);
		
		mPullToRefreshView = (PullToRefreshView3) findViewById(R.id.main_pull_refresh_view);
		mGridView = (GridView) findViewById(R.id.gridview);
		adapter = new MyAdapter();
//		mGridView.setAdapter(new DataAdapter(this));
		mGridView.setAdapter(adapter);
		mPullToRefreshView.setOnHeaderRefreshListener(this);
		mPullToRefreshView.setOnFooterRefreshListener(this);

	}

	@Override
	public void onFooterRefresh(PullToRefreshView3 view) {
		mPullToRefreshView.postDelayed(new Runnable() {

			@Override
			public void run() {
				System.out.println("上拉加载");
				listDrawable.add(R.drawable.pic1);
				adapter.notifyDataSetChanged();
				mPullToRefreshView.onFooterRefreshComplete();
			}
		}, 1000);
	}

	@Override
	public void onHeaderRefresh(PullToRefreshView3 view) {
		mPullToRefreshView.postDelayed(new Runnable() {

			@Override
			public void run() {
				// 设置更新时间
				// mPullToRefreshView.onHeaderRefreshComplete("最近更新:01-23 12:01");
				System.out.println("下拉更新");
				listDrawable.add(R.drawable.pic1);
				adapter.notifyDataSetChanged();
				mPullToRefreshView.onHeaderRefreshComplete();
			}
		}, 1000);

	}
	
	
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listDrawable.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return listDrawable.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = inflater.inflate(R.layout.grid_item, null);
			
			return view;
		}
		
	}
}

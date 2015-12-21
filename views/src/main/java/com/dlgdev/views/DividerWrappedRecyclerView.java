package com.dlgdev.views;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.supportv7.widget.decorator.DividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;
import dlgdev.weighttracker.R;

public class DividerWrappedRecyclerView extends RecyclerView {

	public DividerWrappedRecyclerView(Context context) {
		super(context);
		setup();
	}

	public DividerWrappedRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setup();
	}

	public DividerWrappedRecyclerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setup();
	}

	private void setup() {
		addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
		setLayoutManager(new WrappedLayoutManager(getContext()));
	}
}

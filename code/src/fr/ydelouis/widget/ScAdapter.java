/**
 * Copyright 2013 Yoann Delouis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.ydelouis.widget;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.softboy.screenlocker.R;
import com.softboy.screenlocker.acdisplay.components.NotifyWidget;
import com.softboy.screenlocker.acdisplay.components.Widget;
import com.softboy.screenlocker.utils.PendingIntentUtils;
import com.softboy.screenlocker.widgets.NotificationWidget;

public class ScAdapter extends ArrayAdapter<Widget> {

	Context mContext = null;
	LayoutInflater mInflater;

	public ScAdapter(Context context, List<Widget> items) {
		super(context, 0, items);
		mContext = context;
		mInflater = ((Activity) mContext).getLayoutInflater();
	}

 	@Override
	public View getView(int position, View view, ViewGroup parent) {
  		
		NotifyWidget fragment = (NotifyWidget) getItem(position);

		if (fragment.getNotifyWidget() != null)
			return fragment.getNotifyWidget();

		NotificationWidget mNotifyWidget = (NotificationWidget) mInflater
				.inflate(R.layout.widget_notification, null, false);
		mNotifyWidget.setNotification(fragment.getNotification());
		fragment.setNotifyWidget(mNotifyWidget);
		if(parent!=null)
			parent.setClickable(false);
		mNotifyWidget.setClickable(false);
		mNotifyWidget
				.setOnClickListenerSpc(new NotificationWidget.OnClickListener() {

					@Override
					public void onClick(NotificationWidget widget, View v) {

					}

					@Override
					public void onActionButtonClick(NotificationWidget widget,
							View v, final PendingIntent pendingIntent) {
						PendingIntentUtils.sendPendingIntent(pendingIntent);
					}
				});

		return mNotifyWidget;
	}

}

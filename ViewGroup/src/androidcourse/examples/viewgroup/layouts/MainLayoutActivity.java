package androidcourse.examples.viewgroup.layouts;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import androidcourse.example.viewgroup.layouts.R;

public class MainLayoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_layout);
		Toast.makeText(getApplicationContext(),
				getString(R.string.i_am_here_message), Toast.LENGTH_SHORT).show();

	}

	public void onSimpleLinearLayoutClicked(View v) {

		Intent intent = new Intent(MainLayoutActivity.this,
				SimpleLinearLayout.class);
		startActivity(intent);
	}
	
	public void onComplexLinearLayoutClicked(View v) {

		Intent intent = new Intent(MainLayoutActivity.this,
				LinearLayoutActivity.class);
		startActivity(intent);
	}

	public void onRelativeLayoutClicked(View v) {

		Intent intent = new Intent(MainLayoutActivity.this,
				SimpleRelativeLayout.class);
		startActivity(intent);

	}
	
	public void onNVRelativeLayoutClicked(View v) {

		Intent intent = new Intent(MainLayoutActivity.this,
				RelativeLayoutActivity.class);
		startActivity(intent);

	}

	public void onScrollViewLayoutClicked(View v) {

		Intent intent = new Intent(MainLayoutActivity.this,
				ScrollViewLayoutActivity.class);
		startActivity(intent);
	}

	public void onListViewLayoutClicked(View v) {

		Intent intent = new Intent(MainLayoutActivity.this,
				ListViewLayoutActivity.class);
		startActivity(intent);
	}

	public void onDateTimeLayoutClicked(View v) {

		Intent intent = new Intent(MainLayoutActivity.this,
				DateAndTimeActivity.class);
		startActivity(intent);
	}

	public void onSharedPreferencesClicked(View v) {

		Intent intent = new Intent(MainLayoutActivity.this,
				SharedPreferencesActivity.class);
		startActivity(intent);
	}
}

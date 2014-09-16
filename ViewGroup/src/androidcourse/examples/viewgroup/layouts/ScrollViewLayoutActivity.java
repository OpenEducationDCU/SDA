package androidcourse.examples.viewgroup.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidcourse.example.viewgroup.layouts.R;

public class ScrollViewLayoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		 setContentView(R.layout.scrollview_layout);
		 
		 
		 
	}

	public void onGoBackClicked(View v) {

		Intent intent = new Intent(ScrollViewLayoutActivity.this,
				MainLayoutActivity.class);
		startActivity(intent);
	}

	public void onSaveClicked(View v) {

		Toast.makeText(getApplicationContext(),
				getString(R.string.save_message), Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(ScrollViewLayoutActivity.this,
				MainLayoutActivity.class);
		startActivity(intent);
	}

	public void onCancelClicked(View v) {

		Toast.makeText(getApplicationContext(),
				getString(R.string.cancel_message), Toast.LENGTH_SHORT).show();

		Intent intent = new Intent(ScrollViewLayoutActivity.this,
				MainLayoutActivity.class);
		startActivity(intent);
	}

}

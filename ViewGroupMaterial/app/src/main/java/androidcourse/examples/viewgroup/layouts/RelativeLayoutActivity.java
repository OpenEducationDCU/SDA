package androidcourse.examples.viewgroup.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidcourse.example.viewgroup.layouts.R;

public class RelativeLayoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.relative_layout);
	}

	public void onGoBackClicked(View v) {

		Intent intent = new Intent(RelativeLayoutActivity.this,
				MainLayoutActivity.class);
		startActivity(intent);
	}

	public void onClickDcu(View v) {

		Toast.makeText(this, getString(R.string.ui_dcu_message),
				Toast.LENGTH_LONG).show();

		}

}

package androidcourse.examples.viewgroup.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidcourse.example.viewgroup.layouts.R;

public class LinearLayoutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Toast.makeText(getApplicationContext(),
				getString(R.string.i_am_here_message), Toast.LENGTH_SHORT).show();

		setContentView(R.layout.linear_layout);
	}

	public void onGoBackClicked(View v) {

		Intent intent = new Intent(LinearLayoutActivity.this,
				MainLayoutActivity.class);
		startActivity(intent);
	}

}

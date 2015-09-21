package androidcourse.examples.viewgroup.layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidcourse.example.viewgroup.layouts.R;

public class SimpleRelativeLayout extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.simplerelative);
	}

	public void onGoBackClicked(View v) {

		Intent intent = new Intent(SimpleRelativeLayout.this,
				MainLayoutActivity.class);
		startActivity(intent);
	}

	

}
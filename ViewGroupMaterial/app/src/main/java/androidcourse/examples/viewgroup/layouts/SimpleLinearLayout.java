package androidcourse.examples.viewgroup.layouts;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import androidcourse.example.viewgroup.layouts.R;

public class SimpleLinearLayout extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.linear_simple_layout);
	}



	  public void onGoBackClicked(View v) {
	 
	  Intent intent = new Intent(SimpleLinearLayout.this,
	  MainLayoutActivity.class); 
	  startActivity(intent); 
	  }
	 

}

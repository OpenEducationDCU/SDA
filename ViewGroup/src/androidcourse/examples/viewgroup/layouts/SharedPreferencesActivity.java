package androidcourse.examples.viewgroup.layouts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidcourse.example.viewgroup.layouts.R;

public class SharedPreferencesActivity extends Activity {

	private static final String TAG = "CS65";
	public static final String PREFS_MYRUNS = "MyPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.shared_preferences_layout);

		// Load user data to screen using the private helper function
		// loadProfile

		loadUserData();

	}

	public void onSaveClicked(View v) {

		// Save all information from the screen into a "shared preferences"
		// using private helper function

		saveUserData();

		Toast.makeText(getApplicationContext(),
				getString(R.string.save_message), Toast.LENGTH_SHORT).show();

		Intent mIntent = new Intent(SharedPreferencesActivity.this,
				MainLayoutActivity.class);
		startActivity(mIntent);
	}

	public void onCancelClicked(View v) {

		Toast.makeText(getApplicationContext(),
				getString(R.string.cancel_message), Toast.LENGTH_SHORT).show();

		Intent mIntent = new Intent(SharedPreferencesActivity.this,
				MainLayoutActivity.class);
		startActivity(mIntent);
	}

	// ****************** private helper functions ***************************//

	// load the user data from shared preferences if there is no data make sure
	// that we set it to something reasonable
	private void loadUserData() {

		// We can also use log.d to print to the LogCat

		Log.d(TAG, "loadUserData()");

		// Load and update all profile views

		// Get the shared preferences - create or retrieve the activity
		// preference object

		String mKey = getString(R.string.preference_name);
		SharedPreferences mPrefs = getSharedPreferences(mKey, MODE_PRIVATE);

		// Load the user email

		mKey = getString(R.string.preference_key_profile_email);
		String mValue = mPrefs.getString(mKey, " ");
		((EditText) findViewById(R.id.editEmail)).setText(mValue);

		// Please Load gender info and set radio box

		mKey = getString(R.string.preference_key_profile_gender);

		int mIntValue = mPrefs.getInt(mKey, -1);
		// In case there isn't one saved before:
		if (mIntValue >= 0) {
			// Find the radio button that should be checked.
			RadioButton radioBtn = (RadioButton) ((RadioGroup) findViewById(R.id.radioGender))
					.getChildAt(mIntValue);
			// Check the button.
			radioBtn.setChecked(true);
			Toast.makeText(getApplicationContext(),
					"number of the radioButton is : " + mIntValue,
					Toast.LENGTH_SHORT).show();
		}

	}

	// load the user data from shared preferences if there is no data make sure
	// that we set it to something reasonable
	private void saveUserData() {

		Log.d(TAG, "saveUserData()");

		// Getting the shared preferences editor

		String mKey = getString(R.string.preference_name);
		SharedPreferences mPrefs = getSharedPreferences(mKey, MODE_PRIVATE);

		SharedPreferences.Editor mEditor = mPrefs.edit();
		mEditor.clear();

		// Save email information

		mKey = getString(R.string.preference_key_profile_email);
		String mValue = (String) ((EditText) findViewById(R.id.editEmail))
				.getText().toString();
		mEditor.putString(mKey, mValue);

		// Read which index the radio is checked.

		// edit this out and use as a debug example
		// interesting bug because you try and write an int to a string

		mKey = getString(R.string.preference_key_profile_gender);

		RadioGroup mRadioGroup = (RadioGroup) findViewById(R.id.radioGender);
		int mIntValue = mRadioGroup.indexOfChild(findViewById(mRadioGroup
				.getCheckedRadioButtonId()));
		mEditor.putInt(mKey, mIntValue);

		// Commit all the changes into the shared preference
		mEditor.commit();

		Toast.makeText(getApplicationContext(), "saved name: " + mValue,
				Toast.LENGTH_SHORT).show();

	}

}

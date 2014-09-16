package androidcourse.examples.viewgroup.layouts;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import androidcourse.example.viewgroup.layouts.R;

public class DateAndTimeActivity extends Activity {

	TextView mDisplayDateTime;
	Calendar mDateAndTime = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.date_time_layout);

		mDisplayDateTime = (TextView) findViewById(R.id.dateTime);

		updateDateAndTimeDisplay();

	}

	public void onTimeClicked(View v) {

		TimePickerDialog.OnTimeSetListener mTimeListener = new TimePickerDialog.OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				mDateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
				mDateAndTime.set(Calendar.MINUTE, minute);
				updateDateAndTimeDisplay();
			}
		};

		new TimePickerDialog(DateAndTimeActivity.this, mTimeListener,
				mDateAndTime.get(Calendar.HOUR_OF_DAY),
				mDateAndTime.get(Calendar.MINUTE), true).show();

	}

	public void onDateClicked(View v) {

		DatePickerDialog.OnDateSetListener mDateListener = new DatePickerDialog.OnDateSetListener() {
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				mDateAndTime.set(Calendar.YEAR, year);
				mDateAndTime.set(Calendar.MONTH, monthOfYear);
				mDateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateDateAndTimeDisplay();
			}
		};

		new DatePickerDialog(DateAndTimeActivity.this, mDateListener,
				mDateAndTime.get(Calendar.YEAR),
				mDateAndTime.get(Calendar.MONTH),
				mDateAndTime.get(Calendar.DAY_OF_MONTH)).show();

	}

	private void updateDateAndTimeDisplay() {
		mDisplayDateTime.setText(DateUtils.formatDateTime(this,
				mDateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE
						| DateUtils.FORMAT_SHOW_TIME));
	}

}

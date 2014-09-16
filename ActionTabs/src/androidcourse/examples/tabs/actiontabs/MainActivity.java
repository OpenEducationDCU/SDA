package androidcourse.examples.tabs.actiontabs;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidcourse.example.tabs.actiontabs.R;

public class MainActivity extends Activity {

	private static final String TAB_KEY_INDEX = "tab_key";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// ActionBar
		ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// create new tabs and and set up the titles of the tabs
		ActionBar.Tab mFindTab = actionbar.newTab().setText(
				getString(R.string.ui_tabname_find));
		ActionBar.Tab mChatTab = actionbar.newTab().setText(
				getString(R.string.ui_tabname_chat));
	
	ActionBar.Tab mPartyTab = actionbar.newTab().setText(
			getString(R.string.ui_tabname_party));

		// create the fragments
		Fragment mFindFragment = new FindFragment();
		Fragment mChatFragment = new ChatFragment();
	
		Fragment mPartyFragment = new PartyFragment();

		// bind the fragments to the tabs - set up tabListeners for each tab
		mFindTab.setTabListener(new MyTabsListener(mFindFragment,
				getApplicationContext()));
		mChatTab.setTabListener(new MyTabsListener(mChatFragment,
				getApplicationContext()));
		;
		mPartyTab.setTabListener(new MyTabsListener(mPartyFragment,
				getApplicationContext()));

		// add the tabs to the action bar
		actionbar.addTab(mFindTab);
		actionbar.addTab(mChatTab);
	    actionbar.addTab(mPartyTab);

		// Crash the program -- example of debugging
		
		// Toast.makeText(getApplicationContext(),
		// "tab is " + savedInstanceState.getInt(TAB_KEY_INDEX, 0),
		// Toast.LENGTH_SHORT).show();

		// restore to navigation
		if (savedInstanceState != null) {
			Toast.makeText(getApplicationContext(),
					"tab is " + savedInstanceState.getInt(TAB_KEY_INDEX, 0),
					Toast.LENGTH_SHORT).show();

			actionbar.setSelectedNavigationItem(savedInstanceState.getInt(
					TAB_KEY_INDEX, 0));
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuitem_search:
			Toast.makeText(this, getString(R.string.ui_menu_search),
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menuitem_send:
			Toast.makeText(this, getString(R.string.ui_menu_send),
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menuitem_add:
			Toast.makeText(this, getString(R.string.ui_menu_add),
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menuitem_share:
			Toast.makeText(this, getString(R.string.ui_menu_share),
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menuitem_feedback:
			Toast.makeText(this, getString(R.string.ui_menu_feedback),
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menuitem_about:
			Toast.makeText(this, getString(R.string.ui_menu_about),
					Toast.LENGTH_SHORT).show();
			return true;
		case R.id.menuitem_quit:
			Toast.makeText(this, getString(R.string.ui_menu_quit),
					Toast.LENGTH_SHORT).show();
			finish(); // close the activity
			return true;
		}
		return false;
	}

	// onSaveInstanceState() is used to "remember" the current state when a
	// configuration change occurs such screen orientation change. This
	// is not meant for "long term persistence". We store the tab navigation

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Toast.makeText(
				this,
				"onSaveInstanceState: tab is"
						+ getActionBar().getSelectedNavigationIndex(),
				Toast.LENGTH_SHORT).show();
		outState.putInt(TAB_KEY_INDEX, getActionBar()
				.getSelectedNavigationIndex());

	}

}

// TabListenr class for managing user interaction with the ActionBar tabs. The
// application context is passed in pass it in constructor, needed for the
// toast.

class MyTabsListener implements ActionBar.TabListener {
	public Fragment fragment;
	public Context context;

	public MyTabsListener(Fragment fragment, Context context) {
		this.fragment = fragment;
		this.context = context;

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		Toast.makeText(context, "Reselected!", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) 
	{
		Toast.makeText(context, "Selected!", Toast.LENGTH_SHORT).show();
		ft.replace(R.id.fragment_container, fragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		Toast.makeText(context, "Unselected!", Toast.LENGTH_SHORT).show();
		ft.remove(fragment);
	}

}
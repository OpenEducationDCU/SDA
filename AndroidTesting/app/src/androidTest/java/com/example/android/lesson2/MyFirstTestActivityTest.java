package com.example.android.lesson2;


import android.test.ActivityInstrumentationTestCase2;

import android.widget.TextView;



public class MyFirstTestActivityTest
        extends ActivityInstrumentationTestCase2<MyFirstTestActivity> {

    private MyFirstTestActivity mFirstTestActivity;
    private TextView mFirstTestText;

    public MyFirstTestActivityTest() {
        super(MyFirstTestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Starts the activity under test using the default Intent with:
        // action = {@link Intent#ACTION_MAIN}
        // flags = {@link Intent#FLAG_ACTIVITY_NEW_TASK}
        // All other fields are null or empty.
        mFirstTestActivity = getActivity();
        mFirstTestText = (TextView) mFirstTestActivity.findViewById(R.id.my_first_test_text_view);
    }

    /**
     * Test if your test fixture has been set up correctly. You should always implement a test that
     * checks the correct setup of your test fixture. If this tests fails all other tests are
     * likely to fail as well.
     */
    public void testPreconditions() {
        //Try to add a message to add context to your assertions. These messages will be shown if
        //a tests fails and make it easy to understand why a test failed
        assertNotNull("mFirstTestActivity is null", mFirstTestActivity);
        assertNotNull("mFirstTestText is null", mFirstTestText);
    }

    /**
     * Tests the correctness of the initial text.
     */
    public void testMyFirstTestTextView_labelText() {
        //It is good practice to read the string from your resources in order to not break
        //multiple tests when a string changes.
        final String expected = mFirstTestActivity.getString(R.string.my_first_test);
        final String actual = mFirstTestText.getText().toString();
        assertEquals("mFirstTestText contains wrong text", expected, actual);
    }
}
package com.example.android.lesson5;


import android.os.Bundle;


import com.example.android.lesson2.R;

/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */




import android.app.Activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Sends a user generated message to the ReceiverActivity
 */
public class SenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);
        final Button sendMessageButton = (Button) findViewById(R.id.send_message_button);
        final EditText messageInputEditText = (EditText) findViewById(R.id.message_input_edit_text);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageInputEditText != null) {
                    final CharSequence message = messageInputEditText.getText();
                    startActivity(ReceiverActivity.makeIntent(SenderActivity.this, message));
                }
            }
        });
    }
}


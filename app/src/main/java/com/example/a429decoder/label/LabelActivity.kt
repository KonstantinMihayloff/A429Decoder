package com.example.a429decoder.label

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.a429decoder.*
import com.example.a429decoder.labelData.LabelDataListActivity

class LabelActivity : AppCompatActivity() {

    // Private variables
    private lateinit var label: TextView
    private lateinit var button: Button

    // Current Class Tag used for log
    private val TAG: String = LabelActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Label Init
        label = findViewById(R.id.textViewLabel)

        // Decode Button Init
        button = findViewById(R.id.decodeButton)
        button.setOnClickListener { decodeLabel(label.text.toString()) }
    }

    // Function to decode the label and send it to LabelDetailActivity as a parcelable extra
    private fun decodeLabel(label: String) {
        // Log for debug
        Log.d(TAG, "Decode Button Clicked: Staring label decoding")

        // Conversion of hexadecimal string to a long value that will be passed to the next
        // activity as an intent extra
        var longLabel = label.toLong(16)
        Log.d(TAG, "Long value of label is $longLabel")

        startLabelDescriptionActivity(longLabel)
    }

    // Function used to start the LabelDetailActivity
    private fun startLabelDescriptionActivity(label: Long) {
        // Log for debug
        Log.d(TAG, "Decode Button Clicked: Sending to LabelDetailActivity")

        // Creating intent with the label content as extra
        val intent = Intent(this, LabelDataListActivity::class.java)
        intent.putExtra(LabelDataListActivity.EXTRA_LABEL, label)

        // Starting LabelDetail Activity
        startActivity(intent)
    }
}
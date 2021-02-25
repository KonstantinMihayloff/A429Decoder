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
    fun decodeLabel(label: String) {
        // Log for debug
        Log.d(TAG, "Decode Button Clicked: Staring label decoding")

        val intLabel = label.hexStringToUInt()
        Log.d("Utils", "Integer value is $intLabel")

        val bitSetLabel = intLabel.toBooleanArray()
        //Log.d("Utils", "Bit value is ${bitSetLabel.get(0)}")

        startLabelDescriptionActivity(bitSetLabel)
    }

    // Function used to start the LabelDetailActivity
    fun startLabelDescriptionActivity(label: BooleanArray) {
        // Log for debug
        Log.d(TAG, "Decode Button Clicked: Sending to LabelDetailActivity")

        // Creating intent with the label content as extra
        val intent = Intent(this, LabelDataListActivity::class.java)
        intent.putExtra(LabelDataListActivity.EXTRA_LABEL, label)

        // Starting LabelDetail Activity
        startActivity(intent)
    }

    fun byteArrayOfInts(vararg ints: Int) = ByteArray(ints.size) { pos -> ints[pos].toByte() }

    fun String.hexStringToByteArrayOrig() : ByteArray {

        val result = ByteArray(length / 2)

        for (i in 0 until length step 2) {
            val firstIndex = this.indexOf(this[i]);
            val secondIndex = this.indexOf(this[i + 1]);

            val octet = firstIndex.shl(4).or(secondIndex)
            result.set(i.shr(1), octet.toByte())
        }

        return result
    }
}
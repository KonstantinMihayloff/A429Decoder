package com.example.a429decoder.labelData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a429decoder.R

class LabelDataListActivity : AppCompatActivity() {

    // Companion Object Definition
    companion object{
        val EXTRA_LABEL = "label"
    }

    // lateinit var
    private lateinit var labelData : MutableList<LabelData>
    private lateinit var adapter : LabelDataAdapter

    // TAG
    private val TAG: String = LabelDataListActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_label_data_list)

        // Get label from intent
        var longLabel = intent.getLongExtra(EXTRA_LABEL, 0)

        // Convert label to a string of bits
        var bitStringLabel = longLabel.toString(2)

        // Get label number
        val labelId = getLabelId(longLabel)

        // labelData initialisation
        labelData = mutableListOf()
        labelData.add(LabelData("Label ID", labelId))

        // Add every data to the array
        for(i in 9..LABEL_BIT_SIZE){
            if(i <= bitStringLabel.length) {
                labelData.add(LabelData(i.toString(), bitStringLabel[bitStringLabel.length - i].toString()))
            } else {
                labelData.add(LabelData(i.toString(), "0"))
            }
        }

        adapter = LabelDataAdapter(labelData)

        val recyclerView: RecyclerView = findViewById(R.id.data_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
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
        const val EXTRA_LABEL = "label"
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

        // Get label properties
        val labelId = getLabelId(longLabel)
        val labelSdi = getLabelSDI(longLabel)
        val labelSsm = getLabelSSM(longLabel)

        // labelData initialisation
        labelData = mutableListOf()
        labelData.add(LabelData("Label ID", labelId))
        labelData.add(LabelData("SDI", labelSdi))

        // Add every data to the array
        bitStringLabel = completeWithZero(bitStringLabel, LABEL_TOTAL_BIT_SIZE)

        for(i in (LABEL_ID_BIT_SIZE + LABEL_SDI_BIT_SIZE + 1) until LABEL_TOTAL_BIT_SIZE - LABEL_SSM_BIT_SIZE){
            if(i <= bitStringLabel.length) {
                labelData.add(LabelData(i.toString(), bitStringLabel[bitStringLabel.length - i].toString()))
            } else {
                labelData.add(LabelData(i.toString(), "0"))
            }
        }

        Log.d(TAG, "bitStringLabel : $bitStringLabel")
        labelData.add(LabelData("SSM", labelSsm))
        labelData.add(LabelData("Parity", bitStringLabel[0].toString()))

        adapter = LabelDataAdapter(labelData)

        val recyclerView: RecyclerView = findViewById(R.id.data_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
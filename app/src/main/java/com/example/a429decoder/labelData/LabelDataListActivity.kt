package com.example.a429decoder.labelData

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        var labelFromIntent = intent.getBooleanArrayExtra(EXTRA_LABEL)

        labelData = mutableListOf()

        var i = 0
        labelFromIntent.forEach { data ->
            i += 1
            labelData.add(LabelData(i.toString(), "PUT_LABEL_HERE",data.toString()))
        }

        adapter = LabelDataAdapter(labelData)

        val recyclerView: RecyclerView = findViewById(R.id.data_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        adapter.notifyDataSetChanged()
    }
}
package com.example.root.bitpandatesttask.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.root.bitpandatesttask.R
import kotlinx.android.synthetic.main.activity_details.*

const val DETAIL_TEXT_EXTRA = "details"

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        priceTextView.text = intent.getStringExtra(DETAIL_TEXT_EXTRA)
    }
}

package com.example.a429decoder

import android.os.Parcel
import android.os.Parcelable
import androidx.room.PrimaryKey
import java.util.*

// This file is the data class definition of a label
// A label is a word of 32 bits:
// Bits 1 to 8 : LabelId in octal
// Bits 9 and 10 : SDI
// Bits 11 to 29 : Data
// Bits 30 and 31 : SSM
// Bit 32 Parity

data class Label (
        var rawLabel: BitSet
    /*var labelId: Int?, // TODO : Remove the "?"
    var ssmType: String?, // BNR, DISC, BCD
    var description: String?
    var data: Int,
    var ssm: Int,
    var parity: Int*/
)
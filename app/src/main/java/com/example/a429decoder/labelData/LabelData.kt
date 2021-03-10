package com.example.a429decoder.labelData

val LABEL_BIT_SIZE: Int = 32
val MASK_LABEL_NUMBER = "000000FF".toLong(16)

data class LabelData (
        var contentDescription    : String,
        var value   : String
)

fun applyMask(label: Long, mask: Long) : Long { return label and mask }

fun getLabelId(label: Long) : String {
        // To get the label Id, last 8 bits must be isolated, reverted and converted to octal integer

        // Apply a mask to isolate the last 8 bits that corresponds to the label ID
        val labelId = applyMask(label, MASK_LABEL_NUMBER)
        var labelIdString = labelId.toString(2)

        // Add 0 until the word is 8 bits long
        while(labelIdString.length < 8) {
                labelIdString = "0$labelIdString"
        }

        // Reverse bits
        labelIdString = labelIdString.reversed()
        val labelIdReverted = labelIdString.toLong(2)

        // Return as Long
        return labelIdReverted.toString(8)
}
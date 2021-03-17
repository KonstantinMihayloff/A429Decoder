package com.example.a429decoder.labelData

const val LABEL_TOTAL_BIT_SIZE: Int = 32
const val LABEL_ID_BIT_SIZE: Int = 8
const val LABEL_SDI_BIT_SIZE: Int = 2
const val LABEL_SSM_BIT_SIZE: Int = 2
const val LABEL_PARITY_BIT_SIZE: Int = 1

val MASK_LABEL_NUMBER = "000000FF".toLong(16)
val MASK_SDI = "00000300".toLong(16)
val MASK_SSM = "60000000".toLong(16)

data class LabelData (
        var contentDescription    : String,
        var value   : String
)

// Function designed to isolate bits to get precise label information
// It is done through a logical AND operation
fun applyMask(label: Long, mask: Long) : Long { return label and mask }

// Get the label ID from a label
// It is the first 8 bits of a label reversed and read as octal
fun getLabelId(label: Long) : String {
        // To get the label Id, last 8 bits must be isolated, reverted and converted to octal integer

        // Apply a mask to isolate the last 8 bits that corresponds to the label ID
        val labelId = applyMask(label, MASK_LABEL_NUMBER)
        var labelIdString = labelId.toString(2)

        // Add 0 until the word is 8 bits long
        labelIdString = completeWithZero(labelIdString, 8)

        // Reverse bits
        labelIdString = labelIdString.reversed()
        val labelIdReverted = labelIdString.toLong(2)

        // Return as Long
        return labelIdReverted.toString(8)
}

// Get the SDI labels. It is bits 9 and 10 of a label
fun getLabelSDI(label: Long) : String {

        var labelSsm = applyMask(label, MASK_SDI)

        var labelSsmString = labelSsm.toString(2)

        // Add 0 until the word is 10 bits long (label id + SDI)
        labelSsmString = completeWithZero(labelSsmString, 10)

        // Take only first 2 bits
        labelSsmString = labelSsmString.take(2)

        // Convert to a decimal number
        labelSsm = labelSsmString.toLong(2)
        return labelSsm.toString(10)
}

// Get the SSM labels. It is bits 30 and 31 of a label
fun getLabelSSM(label: Long) : String {

        var labelSdi = applyMask(label, MASK_SSM)

        var labelSdiString = labelSdi.toString(2)
        // Add 0 until the word is 31 bits long
        labelSdiString = completeWithZero(labelSdiString, 31)

        // Get the first 2 bits
        labelSdiString = labelSdiString.take(2)

        // Convert to a decimal number
        labelSdi = labelSdiString.toLong(2)
        return labelSdi.toString(10)
}

// Functions to complete a string of binary language with zeros to match the desired size
fun completeWithZero(label: String, finalSize: Int) : String {
        var result = label

        // Adding zeros while the binary word does not match the desired size
        while(result.length < finalSize) {
                result = "0$result"
        }

        return result
}
package com.example.a429decoder

import java.io.ByteArrayInputStream
import java.util.*
import kotlin.math.pow

fun String.hexStringToUInt(): UInt{

    val hexadecimalAlphabet = "0123456789ABCDEF"
    var integerValueOfString: Int = 0
    var lengthOfString = this.length - 1 // Starting from 0

    // Conversion of Hex String to Int
    for (i in 0 until length) {
        for(j in 0 until hexadecimalAlphabet.length) {
            if (this[i] == hexadecimalAlphabet[j]) {
                integerValueOfString += j * (16.0f.pow(lengthOfString - i)).toInt()
            }
        }
    }
    var uIntReturnValue = integerValueOfString.toUInt()
    return uIntReturnValue
}

fun UInt.toBitSet() : BitSet{
    var valueenter = this
    var remain = 0u
    var result = BitSet(32)
    var cnt = 0

    while (valueenter != 0u) {
        remain = valueenter.rem(2u)
        //result[cnt] = remain
        val bit: Boolean
        if(remain != 0u) bit = true else bit = false
        result.set(cnt, bit)
        valueenter = (valueenter / 2u)
        cnt += 1
    }

    return result
}

fun UInt.toBooleanArray() : BooleanArray{
    var value = this
    var remain = 0u
    var result: BooleanArray = BooleanArray(32)
    var cnt = 0

    while (value != 0u) {
        remain = value.rem(2u)
        val bit: Boolean
        if(remain != 0u) bit = true else bit = false
        result.set(cnt, bit)
        value = (value / 2u)
        cnt += 1
    }

    return result
}
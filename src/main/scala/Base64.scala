object Base64 {
  private val BASE_64_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

  def encode(input: String): String = {
    // modding by 3 because we will be working with 3 values each iteration
    val postfixSize = input.length % 3

    val paddedInput = input + ("0" * (postfixSize) * 2) // multiply by 2 because HEX is 2 digits each

    var intValues = paddedInput.grouped(2).map(hexDigit => Integer.parseInt(hexDigit, 16)).toList

    // get groups of 3 values, each is 8 bits
    val bitSets = intValues.grouped(3).toList

    val encodedStringBuilder = new StringBuilder
    bitSets.foreach(bitSet => {
      // combine them together into a 24 bit number, this gives us 4 6-bit values to encode
      val twentyFourBitNumber = (bitSet(0) << 16) + (bitSet(1) << 8) + bitSet(2)

      // convert each number to a 6-bit number, 0x3F = 111111 mask to ensure we get only the 6-bits we want
      val sixBitNumber1 = (twentyFourBitNumber >> 18) & 0x3F
      val sixBitNumber2 = (twentyFourBitNumber >> 12) & 0x3F
      val sixBitNumber3 = (twentyFourBitNumber >> 6) & 0x3F
      val sixBitNumber4 = twentyFourBitNumber & 0x3F

      encodedStringBuilder.append(BASE_64_CHARS.charAt(sixBitNumber1))
      encodedStringBuilder.append(BASE_64_CHARS.charAt(sixBitNumber2))
      encodedStringBuilder.append(BASE_64_CHARS.charAt(sixBitNumber3))
      encodedStringBuilder.append(BASE_64_CHARS.charAt(sixBitNumber4))
    })

    val almostEncodedString = encodedStringBuilder.toString()
    // Remove the ending 0's that we padded with and replace with the padding char '='
    almostEncodedString.substring(0, almostEncodedString.length - postfixSize) + ("=" * postfixSize)
  }

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      println("Expecting a single argument")
    } else if (args(0).length % 2 != 0) {
      println("Expecting an even number of hex digits")
    } else {
      println(encode(args(0)))
    }
  }
}

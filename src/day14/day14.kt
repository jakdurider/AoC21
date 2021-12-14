package day14

import java.io.File


fun main() {
    val testInput = File("src/day14/day14.txt").readLines()
    val pairs_input = testInput.subList(2, testInput.size).map{it.split(" -> ")}
    fun part1(): Long {
        val pairs = HashMap<String, Char>()
        for(pair in pairs_input){
            pairs[pair[0]] = pair[1][0]
        }
        var string_num = HashMap<String, Long>()
        val char_num = HashMap<Char, Long>()
        for(i in 0..testInput[0].lastIndex-1){
            string_num[String(charArrayOf(testInput[0][i],testInput[0][i+1]))] = (string_num[String(charArrayOf(testInput[0][i],testInput[0][i+1]))]?:0) + 1
            char_num[testInput[0][i]] = (char_num[testInput[0][i]]?:0) + 1
        }
        char_num[testInput[0][testInput[0].lastIndex]] = (char_num[testInput[0][testInput[0].lastIndex]]?:0) + 1
        for(step in 1..40){
            val new_string_num = HashMap<String, Long>()
            for(str in string_num.keys){
                if(pairs[str] != null){
                    val pair0 = String(charArrayOf(str[0],pairs[str]!!))
                    val pair1 = String(charArrayOf(pairs[str]!!, str[1]))
                    new_string_num[pair0] = (new_string_num[pair0]?:0) + string_num[str]!!
                    new_string_num[pair1] = (new_string_num[pair1]?:0) + string_num[str]!!
                    char_num[pairs[str]!!] = (char_num[pairs[str]!!]?:0) + string_num[str]!!
                }
            }
            string_num = new_string_num
        }

        return char_num.maxOf { it.value } - char_num.minOf{it.value}
    }


    println(part1())
}

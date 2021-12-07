package day07

import java.io.File
import java.lang.Math.abs
import kotlin.math.roundToInt

fun main() {
    val testInput = File("src/day07/day07.txt").readLines()[0].split(",").filter{it!=""}.map{it.toInt()}
    fun part1(): Int {
        val median = testInput.sorted()[testInput.size/2]
        val result = testInput.fold(0){ acc, num ->
            acc + abs(num-median)
        }
        return result
    }

    fun part2(): Int {
        val size = testInput.size
        val sum = testInput.sum()
        val square_sum = testInput.sumOf{it*it}
        val sorted = testInput.sorted()
        var ret = Int.MAX_VALUE
        for(i in 0..size){
            val min_x  = ((2*sum + size - 2*i).toDouble()/(2*size)).roundToInt()
            if(i!=0 && (min_x < sorted[i-1] || min_x > sorted[i])) continue
            val result = testInput.fold(0){acc, num->
                acc + ((num-min_x)*(num-min_x) + abs(num-min_x))/2
            }
            if(ret > result) ret = result
        }
        return ret
    }

    println(part1())
    println(part2())
}

package day06

import java.io.File

fun main() {
    var testInput = File("src/day06/day06.txt").readLines()[0].split(",").filter{it!=""}.map{it.toLong()}.toMutableList()
    fun part1(): Long {
        var days = MutableList<Long>(9, {0})
        for(i in 0..8){
            days[i] = testInput.count{it==i.toLong()}.toLong()
        }
        val day = 256
        for(i in 1..day){
            val new = days[0]
            for(i in 1 .. 8) days[i-1] = days[i];
            days[6] += new
            days[8] = new
        }
        return days.sum().toLong()
    }
/*
    fun part2(): Int {

    }
*/
    println(part1())
  //  println(part2())
}

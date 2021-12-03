package day03

import java.io.File

fun main() {
    val testInput = File("src/day03/Day03.txt").readLines()
    fun part1(): Int {
        val input_size = testInput[0].length
        val report = testInput.fold(IntArray(input_size)){
                acc, each_report ->
            val temp = each_report.toCharArray().map{Character.getNumericValue(it) * 2 - 1}
            for(i in 0..acc.lastIndex) acc[i] += temp[i]
            acc
        }
        var gamma = 0
        var epsilon = 0
        val size = report.size
        for(i in 0..report.lastIndex){
            if(report[i]>=0) gamma += 1 shl (size - 1- i)
            else epsilon += (1.shl(size-1-i))
        }
        return gamma*epsilon
    }

    fun part2(): Int {
        var input = testInput
        val size = testInput[0].length
        for(i in 0 until size){

            var total = 0
            var input_new = mutableListOf<String>()
            for(report in input){
                if(report[i] == '1') ++total;
                else --total;
            }
            val majority = if(total>=0) 1 else 0
            for(report in input){
                if(Character.getNumericValue(report[i]) == majority) input_new.add(report)
            }
            input = input_new
            if(input.size == 1) break
        }
        var oxygen = 0
        for(i in 0 until input[0].length){
            oxygen += Character.getNumericValue(input[0][i]) shl size-1-i
        }

        input = testInput
        for(i in 0 until size){
            var total = 0
            var input_new = mutableListOf<String>()
            for(report in input){
                if(report[i] == '1') ++total;
                else --total;
            }
            val minority = if(total < 0) 1 else 0
            for(report in input){
                if(Character.getNumericValue(report[i]) == minority) input_new.add(report)
            }
            input = input_new
            if(input.size == 1) break
        }
        var carbon = 0
        for(i in 0 until input[0].length){
            carbon += Character.getNumericValue(input[0][i]) shl size-1-i
        }
        return oxygen*carbon
    }

    println(part1())
    println(part2())
}

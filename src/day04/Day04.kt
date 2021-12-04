package day04

import java.io.File

class obj(
    var num: Int,
    var marked : Boolean
)

fun main() {
    val testInput = File("src/day04/Day04.txt").readLines()
    fun makematrix(start: Int, end: Int) : MutableList<List<obj>> {
        var matrix = mutableListOf<List<obj>>()
        for(i in start..end){
            matrix.add(testInput[i].split(" ").filter{it!=""}.map{obj(num = it.toInt(), marked = false)})
        }
        return matrix
    }
    fun mark(matrix: MutableList<List<obj>>, num: Int){
        for(row in matrix){
            for(element in row){
                if(element.num == num) element.marked = true
            }
        }
    }
    fun checkbingo(matrix: MutableList<List<obj>>) : Boolean{
        for(row in matrix){
            if(row.all{it.marked}) return true
        }
        for(column_idx in 0..4){
            var bingo = true
            for(row in matrix){
                if(!row[column_idx].marked){
                    bingo = false
                    break
                }
            }
            if(bingo) return true
        }
        return false
    }
    val draws = testInput[0].split(",").filter{it!=""}.map{it.toInt()}
    fun part1(): Int {
        var ret = 0
        var last = Int.MAX_VALUE
        for(i in 1..testInput.lastIndex step 6){
            val matrix = makematrix(i+1, i+5)
            for(i in 0..draws.lastIndex){
                mark(matrix, draws[i])
                if(checkbingo(matrix)){
                    if(i < last) {
                        last = i
                        ret = draws[i] * matrix.flatten().filter{!it.marked}.sumOf{it.num}
                    }
                    break
                }
            }
        }
        return ret
    }

    fun part2(): Int {
        var ret = 0
        var last = Int.MIN_VALUE
        for(i in 1..testInput.lastIndex step 6){
            val matrix = makematrix(i+1, i+5)
            for(i in 0..draws.lastIndex){
                mark(matrix, draws[i])
                if(checkbingo(matrix)){
                    if(last < i) {
                        last = i
                        ret = draws[i] * matrix.flatten().filter{!it.marked}.sumOf{it.num}
                    }
                    break
                }
            }
        }
        return ret
    }

    println(part1())
    println(part2())
}
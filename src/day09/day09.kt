package day09

import java.io.File

class obj(
        var num: Char,
        var marked: Boolean,
)

fun main() {
    val values = File("src/day09/day09.txt").readLines()
    var obj_list = mutableListOf<MutableList<obj>>()
    for(i in 0..values.lastIndex){
        obj_list += mutableListOf<obj>()
        for(j in 0..values[i].length-1){
            obj_list[i] += obj(values[i][j],false)
        }
    }
    fun part1(): Int {
        var result = 0
        for(i in 0..values.lastIndex){
            for(j in 0..values[i].length-1){
                if(i<=values.lastIndex-1 && values[i][j] >= values[i+1][j]) continue
                if(i>=1 && values[i][j] >= values[i-1][j]) continue
                if(j<=values[i].length-2 && values[i][j] >= values[i][j+1]) continue
                if(j>=1 && values[i][j] >= values[i][j-1]) continue
                result += Character.getNumericValue(values[i][j])+ 1
            }
        }
        return result
    }
    fun reset(){
        for(i in 0..obj_list.lastIndex){
            for(j in 0..obj_list[i].lastIndex) obj_list[i][j].marked = false
        }
    }
    fun basin(i: Int, j: Int): Int{
        var result = 0
        if(Character.getNumericValue(values[i][j]) == 9 || obj_list[i][j].marked) return 0
        else result = 1
        obj_list[i][j].marked = true
        if(i<=values.lastIndex-1 && values[i][j] < values[i+1][j]) result += basin(i+1, j)
        if(i>=1 && values[i][j] < values[i-1][j]) result += basin(i-1,j)
        if(j<=values[i].length-2 && values[i][j] < values[i][j+1]) result += basin(i,j+1)
        if(j>=1 && values[i][j] < values[i][j-1]) result += basin(i,j-1)
        return result
    }
    fun part2(): Int {
        var result = mutableListOf<Int>()
        for(i in 0..values.lastIndex){
            for(j in 0..values[i].length-1){
                if(i<=values.lastIndex-1 && values[i][j] >= values[i+1][j]) continue
                if(i>=1 && values[i][j] >= values[i-1][j]) continue
                if(j<=values[i].length-2 && values[i][j] >= values[i][j+1]) continue
                if(j>=1 && values[i][j] >= values[i][j-1]) continue
                if(result.size < 3){
                    val basin_size = basin(i,j)
                    result += basin_size
                    result = result.sorted().toMutableList()
                }
                else{
                    val basin_size = basin(i,j)
                    for(a in 2 downTo 0){
                        if(result[a] < basin_size){
                            result.add(a+1,basin_size)
                            result.removeAt(0)
                            break
                        }
                    }
                }
            }
        }
        return result[0]*result[1]*result[2]
    }

    println(part1())
    println(part2())
}
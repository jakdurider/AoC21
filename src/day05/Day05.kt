package day05

import java.io.File
import kotlin.math.*

fun main() {
    val testInput = File("src/day05/Day05.txt").readLines().map{it->it.split(" ", ",", "->").filter{it!=""}.map{it.toInt()}}
    fun part1(): Int {
        var hashmap = HashMap<Pair<Int,Int>, Int>()
        val lines = testInput.filter{it[0]==it[2] || it[1]==it[3]}
        var total = 0
        for(line in lines){
            if(line[0] == line[2]){
                for(i in min(line[1],line[3])..max(line[1],line[3])){
                    val pair = Pair(line[0],i)
                    if(hashmap.containsKey(pair)){
                        if(hashmap[pair] == 1) ++total
                        hashmap[pair] = hashmap[pair]!! + 1
                    }
                    else hashmap[pair] = 1
                }
            }
            else if(line[1] == line[3]){
                for(i in min(line[0],line[2])..max(line[0],line[2])){
                    val pair = Pair(i,line[1])
                    if(hashmap.containsKey(pair)){
                        if(hashmap[pair] == 1) ++total
                        hashmap[pair] = hashmap[pair]!! + 1
                    }
                    else hashmap[pair] = 1
                }
            }
        }
        return total
    }

    fun part2(): Int {
        var hashmap = HashMap<Pair<Int,Int>, Int>()
        var total = 0
        for(line in testInput){
            if(line[0] == line[2]){
                for(i in min(line[1],line[3])..max(line[1],line[3])){
                    val pair = Pair(line[0],i)
                    if(hashmap.containsKey(pair)){
                        if(hashmap[pair] == 1) ++total
                        hashmap[pair] = hashmap[pair]!! + 1
                    }
                    else hashmap[pair] = 1
                }
            }
            else if(line[1] == line[3]){
                for(i in min(line[0],line[2])..max(line[0],line[2])){
                    val pair = Pair(i,line[1])
                    if(hashmap.containsKey(pair)){
                        if(hashmap[pair] == 1) ++total
                        hashmap[pair] = hashmap[pair]!! + 1
                    }
                    else hashmap[pair] = 1
                }
            }
            else{
                val x_sign = (line[2]-line[0]).sign; val y_sign = (line[3]-line[1]).sign
                var x = line[0]; var y = line[1];
                while(min(line[0],line[2]) <= x && x <= max(line[0],line[2])){
                    val pair = Pair(x,y)
                    if(hashmap.containsKey(pair)){
                        if(hashmap[pair] == 1) ++total;
                        hashmap[pair] = hashmap[pair]!! + 1
                    }
                    else hashmap[pair] = 1
                    x += x_sign
                    y += y_sign
                }
            }

        }
        return total
    }

    println(part1())
    println(part2())
}
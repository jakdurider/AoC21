package day08

import java.io.File
import kotlin.math.roundToInt

fun main() {
    val testInput = File("src/day08/day08.txt").readLines().map{it.split(" ").filter{it!=""}}
    fun part1(): Int {
        val result = testInput.fold(0){ acc, line->
            var total = 0
            for(i in line.indexOf("|")+1..line.lastIndex){
                when(line[i].length){
                    2,3,4,7 -> ++total
                }
            }
            acc + total
        }
        return result
    }

    fun xor(str1: String, str2: String) : List<Char>{
        var str1_list = str1.toCharArray().toMutableList()
        var str2_list = str2.toCharArray().toMutableList()
        str1_list.removeAll{str2.contains(it)}
        str2_list.removeAll{str1.contains(it)}
        return str1_list + str2_list
    }

    fun and(str1: String, str2: String) : List<Char>{
        val str1_list = str1.toCharArray().toMutableList()
        str1_list.removeAll{!str2.contains(it)}
        return str1_list
    }

    fun part2(): Int {
        val hashmap = HashMap<Char, MutableList<Char>>()
        val result = testInput.fold(0){ acc,line->
            for(i in 'a'..'g'){
                hashmap[i] = mutableListOf('a','b','c','d','e','f','g')
            }
            var five_list = mutableListOf<String>()
            var six_list = mutableListOf<String>()
            for(str in line){
                if(str == "|") break
                when(str.length){
                    2 -> {
                        for(i in 0..str.length-1) hashmap[str[i]]!!.removeAll{!listOf('c','f').contains(it)}
                        val xor_list = xor("abcdefg", str)
                        for(i in xor_list) hashmap[i]!!.removeAll{listOf('c','f').contains(it)}
                    }
                    3 -> {
                        for(i in 0..str.length-1) hashmap[str[i]]!!.removeAll{!listOf('a','c','f').contains(it)}
                        val xor_list = xor("abcdefg", str)
                        for(i in xor_list) hashmap[i]!!.removeAll{listOf('a','c','f').contains(it)}
                    }
                    4 -> {
                        for(i in 0..str.length-1) hashmap[str[i]]!!.removeAll{!listOf('b','d','c','f').contains(it)}
                        val xor_list = xor("abcdefg", str)
                        for(i in xor_list) hashmap[i]!!.removeAll{listOf('b','d','c','f').contains(it)}
                    }
                    5 -> {
                        val str_sorted = str.toCharArray().sorted().joinToString("")
                        if(five_list.isEmpty() || five_list.all{it!=str_sorted}) five_list += str_sorted
                        if(five_list.size == 3){
                            val and_list = and(and(five_list[0],five_list[1]).toString(), five_list[2])
                            for(i in and_list) hashmap[i]!!.removeAll{!listOf('a','d','g').contains(it)}
                            for(i in xor("abcdefg", and_list.joinToString(""))) hashmap[i]!!.removeAll{listOf('a','d','g').contains(it)}
                        }
                    }
                    6 -> {
                        val str_sorted = str.toCharArray().sorted().joinToString("")
                        if(six_list.isEmpty() || six_list.all{it!=str_sorted}) six_list += str_sorted
                        if(six_list.size == 3){
                            val and_list = and(and(six_list[0],six_list[1]).toString(), six_list[2])
                            for(i in and_list) hashmap[i]!!.removeAll{!listOf('a','b','f','g').contains(it)}
                            for(i in xor("abcdefg", and_list.joinToString(""))) hashmap[i]!!.removeAll{listOf('a','b','f','g').contains(it)}
                        }
                    }
                }
            }
            val a = hashmap.filterValues { it[0] == 'a' }.keys.elementAt(0)
            val b = hashmap.filterValues { it[0] == 'b' }.keys.elementAt(0)
            val c = hashmap.filterValues { it[0] == 'c' }.keys.elementAt(0)
            val d = hashmap.filterValues { it[0] == 'd' }.keys.elementAt(0)
            val e = hashmap.filterValues { it[0] == 'e' }.keys.elementAt(0)
            val f = hashmap.filterValues { it[0] == 'f' }.keys.elementAt(0)
            val g = hashmap.filterValues { it[0] == 'g' }.keys.elementAt(0)

            val intmap = HashMap<String, Int>()
            intmap[listOf(a,b,c,e,f,g).sorted().joinToString("")] = 0
            intmap[listOf(c,f).sorted().joinToString("")] = 1
            intmap[listOf(a,c,d,e,g).sorted().joinToString("")] = 2
            intmap[listOf(a,c,d,f,g).sorted().joinToString("")] = 3
            intmap[listOf(b,c,d,f).sorted().joinToString("")] = 4
            intmap[listOf(a,b,d,f,g).sorted().joinToString("")] = 5
            intmap[listOf(a,b,e,g,f,d).sorted().joinToString("")] = 6
            intmap[listOf(a,c,f).sorted().joinToString("")] = 7
            intmap[listOf(a,b,c,d,e,f,g).sorted().joinToString("")] = 8
            intmap[listOf(a,b,c,d,f,g).sorted().joinToString("")] = 9

            val output = line.indexOf("|")
            var total = 0
            var tenth = 1000
            for(i in output+1..line.lastIndex){
                total += intmap[line[i].toCharArray().toMutableList().sorted().joinToString("")]!! * tenth
                tenth /= 10
            }
            acc + total
        }
        return result
    }

    println(part1())
    println(part2())
}

package test

import main.ExampleTreeMap
import org.testng.annotations.Test

class Tests {

    @Test fun first(){
        val tree = ExampleTreeMap<Int, Int>()
        tree.put(5, 1)
        tree.put(4, 2)
        tree.put(8, 3)
        tree.put(9, 4)
        tree.put(10, 5)
        tree.put(6, 6)
        println(tree.getSize())
        println(tree)
        tree.remove(8)
        println(tree.getSize())
        println(tree)
        tree.remove(10)
        println(tree.getSize())
        println(tree)
        tree.remove(4)
        println(tree.getSize())
        println(tree)
        tree.remove(5)
        println(tree.getSize())
        println(tree)
        println("Get:")
        println(tree.get(9))
        println(tree.get(6))
    }

    @Test fun second() {
        val tree = ExampleTreeMap<Int, Int>()
        tree.put(3, 3)
        tree.put(4, 4)
        tree.put(2, 2)
        tree.put(1, 1)
        tree.put(5, 5)
        tree.put(6, 6)
        tree.put(7, 7)
        tree.put(8, 8)
        tree.put(9, 9)
        println(tree)
        println("_________________")
        tree.prn()
    }
}
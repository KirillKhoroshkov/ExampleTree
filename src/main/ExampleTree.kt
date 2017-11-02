package main

class ExampleTree<K : Comparable<K>, V> {

    private var root: Vertex<K, V>? = null

    inner private class Vertex<T : Comparable<T>, U>(val key: T, var value: U) {
        var left: Vertex<T, U>? = null
        var right: Vertex<T, U>? = null
    }

    private fun <K : Comparable<K>, V> search(key: K, vertex: Vertex<K, V>?): V? {
        return if (vertex == null || key == vertex.key) {
            vertex?.value
        }
        else if (key <= vertex.key) {
            search(key, vertex.left)
        }
        else if (key >= vertex.key) {
            search(key, vertex.right)
        }
        else {
            null
        }
    }

    fun get(key: K): V? {
        return search(key, root)
    }

    private fun put(key: K, value: V, vertex: Vertex<K, V>): V?{
        if (key < vertex.key) {
            return if (vertex.left == null){
                vertex.left = Vertex(key, value)
                null
            } else {
                put(key, value, vertex.left!!)
            }
        }
        else if (key > vertex.key) {
            return if (vertex.right == null){
                vertex.right = Vertex(key, value)
                null
            } else {
                put(key, value, vertex.right!!)
            }
        }
        else{
            val lastValue = vertex.value
            vertex.value = value
            return lastValue
        }
    }

    fun put(key: K, value: V): V?{
        if (isEmpty()){
            root = Vertex(key, value)
            return null
        }
        else{
            put(key, value, root!!)
        }
    }

    fun isEmpty(): Boolean{
        return root == null
    }
}
package main

class ExampleTree<K : Comparable<K>, V> {

    private var root: Node<K, V>? = null

    inner private class Node<T : Comparable<T>, U>(val key: T, var value: U) {
        var left: Node<T, U>? = null
        var right: Node<T, U>? = null
    }

    private fun <K : Comparable<K>, V> search(key: K, node: Node<K, V>?): Node<K, V>? {
        return if (node == null || key == node.key) {
            node
        }
        else if (key <= node.key) {
            search(key, node.left)
        }
        else if (key >= node.key) {
            search(key, node.right)
        }
        else {
            null
        }
    }

    fun get(key: K): V? {
        return search(key, root)?.value
    }

    private fun put(key: K, value: V, node: Node<K, V>): V?{
        when {
            key < node.key -> return if (node.left == null){
                node.left = Node(key, value)
                null
            } else {
                put(key, value, node.left!!)
            }
            key > node.key -> return if (node.right == null){
                node.right = Node(key, value)
                null
            } else {
                put(key, value, node.right!!)
            }
            else -> {
                val lastValue = node.value
                node.value = value
                return lastValue
            }
        }
    }

    fun put(key: K, value: V): V?{
        return if (root == null){
            root = Node(key, value)
            null
        }
        else{
            put(key, value, root!!)
        }
    }

    private fun remove(key: K, vertex: Node<K, V>?): V?{

    }

    fun remove(key: K): V?{
        if (root == null){
            return null
        }
        else if (key < root!!.key){
            return remove(key, root!!.left)
        }
        else if (key > root!!.key){
            return remove(key, root!!.right)
        }
        else if (root!!.key == key ){
            if (root!!.left == null && root!!.right == null){
                val lastValue = root!!.value
                root = null
                return lastValue
            }
            else if (root!!.left == null){
                val lastValue = root!!.value
                root = root!!.right
                return lastValue
            }
            else if (root!!.right == null){
                val lastValue = root!!.value
                root = root!!.left
                return lastValue
            }
            else{
                if (root!!.right!!.left == null){

                }
            }
        }
    }
}
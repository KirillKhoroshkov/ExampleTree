package main

import java.util.*

class ExampleTreeMap<K : Comparable<K>, V> {

    private var root: Node<K, V>? = null
    private var size = 0

    fun getSize() = size

    private class Node<T : Comparable<T>, U>(val key: T, var value: U, var parent: Node<T, U>?) {
        var left: Node<T, U>? = null
        var right: Node<T, U>? = null

        override fun equals(other: Any?): Boolean {
            return if (other !is Node<*, *>) {
                false
            } else (this.key == other.key &&
                    this.value == other.value)
        }

        override fun toString(): String {
            return key.toString() + "=" + value.toString()
        }
    }

    private fun <K : Comparable<K>, V> search(key: K, node: Node<K, V>?): Node<K, V>? {
        return if (node == null || key == node.key) {
            node
        } else if (key < node.key) {
            search(key, node.left)
        } else {
            search(key, node.right)
        }
    }

    fun get(key: K): V? {
        return search(key, root)?.value
    }

    private fun put(key: K, value: V, node: Node<K, V>): V? {
        when {
            key < node.key -> return if (node.left == null) {
                node.left = Node(key, value, node)
                size++
                null
            } else {
                put(key, value, node.left!!)
            }
            key > node.key -> return if (node.right == null) {
                node.right = Node(key, value, node)
                size++
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

    fun put(key: K, value: V): V? {
        return if (root == null) {
            root = Node(key, value, null)
            size++
            null
        } else {
            put(key, value, root!!)
        }
    }

    fun remove(key: K): V? {
        val node = search(key, root)
        if (node == null){
            return null
        } else {
            val newNode: Node<K, V>?
            if (node.left != null && node.right != null) {
                var leftmostOfRight = node.right
                while (leftmostOfRight!!.left != null) {
                    leftmostOfRight = leftmostOfRight.left
                }
                if (node.right != leftmostOfRight) {
                    leftmostOfRight.right = node.right
                    leftmostOfRight.parent!!.left = leftmostOfRight.right
                }
                leftmostOfRight.parent = node.parent
                leftmostOfRight.left = node.left
                changeChild(node, leftmostOfRight)
                newNode = leftmostOfRight
            } else if (node.left != null) {
                node.left!!.parent = node.parent
                changeChild(node, node.left)
                newNode = node.left
            } else if (node.right != null) {
                node.right!!.parent = node.parent
                changeChild(node, node.right)
                newNode = node.right
            } else {
                changeChild(node, null)
                newNode = null
            }
            if (node == root) {
                root = newNode
            }
            size--
            return node.value
        }
    }

    private fun changeChild(oldNode: Node<K, V>, newNode: Node<K, V>?){
        if (oldNode.parent != null){
            if (oldNode.parent!!.left == oldNode){
                oldNode.parent!!.left = newNode
            } else {
                oldNode.parent!!.right = newNode
            }
        }
    }

    override fun toString(): String {
        if (root == null){
            return ""
        } else {
            val deque = ArrayDeque<Node<K, V>>()
            val sb = StringBuilder()
            val visited = mutableListOf<Node<K, V>>()
            sb.append("{")
            deque.addFirst(root)
            while (!deque.isEmpty()){
                val current = deque.first
                if (current.left != null && !visited.contains(current.left!!)){
                    deque.addFirst(current.left)
                } else {
                    deque.removeFirst()
                    visited.add(current)
                    sb.append(current)
                    sb.append(", ")
                    if (current.right != null){
                        deque.addFirst(current.right)
                    }
                }
            }
            sb.delete(sb.length - 2, sb.length)
            sb.append("}")
            return sb.toString()
        }
    }
}
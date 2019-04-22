package com.ivan.datastructures.tree

abstract class BaseBinaryTree<K : Comparable<K>, V> : Tree<K, V> {

    protected var root: Entry<K, V>? = null

    override fun root(): Entry<K, V>? {
        return root
    }

    override fun find(key: K): V? {
        return findNode(key)?.value
    }

    protected abstract fun createEntry(key: K, value: V): Entry<K, V>;

    protected fun findNode(key: K): Entry<K, V>? {
        if (root == null) {
            return null
        }

        var tmp = root
        while (tmp != null) {
            val compareResult = key.compareTo(tmp.key)

            if (compareResult < 0) {
                tmp = tmp.left
            } else if (compareResult > 0) {
                tmp = tmp.right
            } else {
                // key is found
                return tmp
            }
        }
        // key does not exist in the tree
        return null
    }

    protected fun insertNode(key: K, value: V): Entry<K, V> {
        if (root == null) {
            root = createEntry(key, value)
            return root!!
        }

        var parent = root
        while (parent != null) {
            val compareResult = key.compareTo(parent.key)

            when {
                compareResult < 0 -> {
                    if (parent.left == null) {
                        val newNode = createEntry(key, value)
                        parent.left = newNode
                        newNode.parent = parent
                        return newNode
                    }
                    parent = parent.left
                }
                compareResult > 0 -> {
                    if (parent.right == null) {
                        val newNode = createEntry(key, value)
                        parent.right = newNode
                        newNode.parent = parent
                        return newNode
                    }
                    parent = parent.right
                }
                else -> {
                    // key already exists in the tree, just substitute the value
                    parent.value = value
                    return parent
                }
            }
        }
        throw Exception("Unreachable situation")
    }
}
package com.ivan.datastructures.tree.bst

import com.ivan.datastructures.tree.Entry
import com.ivan.datastructures.tree.Tree

class BinarySearchTree<K: Comparable<K>, V> : Tree<K, V> {

    var root: Entry<K, V>? = null

    override fun root(): Entry<K, V>? {
        return root
    }

    override fun find(key: K): V? {
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
                return tmp.value
            }
        }
        // kew was not found
        return null
    }

    override fun insert(key: K, value: V) {
        if (root == null) {
            root = Entry(key, value)
            return
        }

        var parent = root
        while (parent != null) {
            val compareResult = key.compareTo(parent.key)

            if (compareResult < 0) {
                if (parent.left == null) {
                    parent.left = Entry(key, value, parent)
                    return
                }
                parent = parent.left
            } else if (compareResult > 0) {
                if (parent.right == null) {
                    parent.right = Entry(key, value, parent)
                }
                parent = parent.right
            } else {
                // key already exists in the tree, just substitute the value
                parent.value = value
                return
            }
        }
    }

    override fun remove(key: K) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
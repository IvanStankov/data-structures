package com.ivan.datastructures.tree

interface Tree<K, V> {

    fun root(): Entry<K, V>?

    fun find(key: K): V?

    fun insert(key: K, value: V)

    fun remove(key: K): V?

}

data class Entry<K, V>(
    val key: K,
    var value: V,
    var parent: Entry<K, V>? = null,
    var left: Entry<K, V>? = null,
    var right: Entry<K, V>? = null
) {
    override fun toString(): String {
        return key.toString()
    }
}
package org.themoviedb

data class Movie(val icon: Array<Int>, val header: Array<String>, val description: Array<String>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (!icon.contentEquals(other.icon)) return false
        if (!header.contentEquals(other.header)) return false
        if (!description.contentEquals(other.description)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = icon.contentHashCode()
        result = 31 * result + header.contentHashCode()
        result = 31 * result + description.contentHashCode()
        return result
    }
}
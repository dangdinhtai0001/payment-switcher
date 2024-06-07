package vn.eclipse.pluginswitcher.core

import org.springframework.core.annotation.AnnotationAwareOrderComparator
import java.util.*
import kotlin.collections.ArrayList

class OrderAwarePluginRegistry<T : Plugin<S>, S>(plugins: List<T>, private val comparator: Comparator<in T>) :
    SimplePluginRegistry<T, S>(plugins) {


    companion object {
        val DEFAULT_COMPARATOR: Comparator<Any> = AnnotationAwareOrderComparator()
        private val DEFAULT_REVERSE_COMPARATOR: Comparator<Any> = DEFAULT_COMPARATOR.reversed()

        fun <S, T : Plugin<S>> of(plugins: List<T>, comparator: Comparator<in T>): OrderAwarePluginRegistry<T, S> {
            return OrderAwarePluginRegistry(plugins, comparator)
        }

        fun <S, T : Plugin<S>> of(comparator: Comparator<in T?>): OrderAwarePluginRegistry<T, S> {
            return of(emptyList(), comparator)
        }

        fun <S, T : Plugin<S>> of(vararg plugins: T): OrderAwarePluginRegistry<T, S> {
            return of(plugins.toList(), DEFAULT_COMPARATOR)
        }


        fun <S, T : Plugin<S>> of(plugins: List<T>): OrderAwarePluginRegistry<T, S> {
            return of(plugins, DEFAULT_COMPARATOR)
        }

        fun <S, T : Plugin<S>> ofReverse(plugins: List<T>): OrderAwarePluginRegistry<T, S> {
            return of(plugins, DEFAULT_REVERSE_COMPARATOR)
        }
    }


    override fun initialize(plugins: List<T>): List<T> {
        val result = super.initialize(plugins)
        Collections.sort(result, comparator)
        return result
    }

    fun reverse(): OrderAwarePluginRegistry<T, S> {
        val copy: List<T> = ArrayList(getPlugins())
        return of(copy, comparator.reversed())
    }
}
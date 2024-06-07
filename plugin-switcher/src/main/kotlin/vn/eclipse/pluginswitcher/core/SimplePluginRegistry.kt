package vn.eclipse.pluginswitcher.core

import java.util.*
import java.util.stream.Collectors
import kotlin.collections.ArrayList

open class SimplePluginRegistry<T : Plugin<S>, S>(plugins: List<T>) : PluginRegistrySupport<T, S>(plugins) {
    companion object {
        fun <S, T : Plugin<S>> empty(): SimplePluginRegistry<T, S> {
            return of(Collections.emptyList());
        }

        fun <S, T : Plugin<S>> of(plugins: List<T>): SimplePluginRegistry<T, S> {
            return SimplePluginRegistry(plugins)
        }

        fun <S, T : Plugin<S>> of(vararg plugins: T): SimplePluginRegistry<T, S> {
            return of(plugins.asList())
        }
    }

    // region get single plugin
    override fun getPluginFor(delimiter: S): T? {
        return getPlugins().find { it.supports(delimiter) }
    }

    override fun <E : Exception> getPluginFor(delimiter: S, ex: () -> E): T {
        val plugin: T? = getPluginFor(delimiter)

        return plugin ?: throw ex()
    }

    // endregion

    // region require single plugin
    override fun getRequiredPluginFor(delimiter: S): T {
        return getRequiredPluginFor(delimiter) {
            "No plugin found for delimiter $delimiter! Registered plugins: ${getPlugins()}."
        }
    }

    override fun getRequiredPluginFor(delimiter: S, message: () -> String): T {
        return getPluginFor(delimiter) { IllegalArgumentException(message()) }
    }
    // endregion

    // region get single plugin or default
    override fun getPluginOrDefaultFor(delimiter: S, plugin: T): T {
        return getPluginOrDefaultFor(delimiter) { plugin }
    }

    override fun getPluginOrDefaultFor(delimiter: S, defaultSupplier: () -> T): T {
        val plugin: T? = getPluginFor(delimiter)

        return plugin ?: defaultSupplier()
    }
    // endregion

    // region get list plugins
    override fun getPluginsFor(delimiter: S): List<T> {
        return super.getPlugins().stream().filter { it: T -> it.supports(delimiter) }.collect(Collectors.toList())
    }

    override fun getPluginsFor(delimiter: S, plugins: List<T>): List<T> {
        val candidates: List<T> = getPluginsFor(delimiter)

        return candidates.ifEmpty {
            ArrayList<T>(plugins)
        }
    }

    override fun <E : Exception> getPluginsFor(delimiter: S, ex: () -> E): List<T> {
        val plugins = getPluginsFor(delimiter)

        if (plugins.isEmpty()) {
            throw ex()
        }

        return plugins
    }

    // endregion

    override fun countPlugins(): Int {
        return getPlugins().size
    }

    override fun hasPluginFor(delimiter: S): Boolean {
        return getPluginFor(delimiter) != null
    }

    override fun contains(plugin: T): Boolean {
        return getPlugins().contains(plugin)
    }
}
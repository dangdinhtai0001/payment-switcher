package vn.eclipse.pluginswitcher.core

import java.util.stream.Collectors

abstract class PluginRegistrySupport<T : Plugin<S>, S>(private var plugins: List<T> = ArrayList()) :
    PluginRegistry<T, S>, Iterable<T> {

    private var initialized: Boolean = false

    override fun getPlugins(): List<T> {
        if (!initialized) {
            this.plugins = initialize(this.plugins);
            this.initialized = true;
        }

        return plugins;
    }

    @Synchronized
    open fun initialize(plugins: List<T>): List<T> {
        return plugins.stream().filter { it != null }.collect(Collectors.toList())
    }

    override fun iterator(): Iterator<T> {
        return getPlugins().iterator()
    }
}

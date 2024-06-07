package vn.eclipse.pluginswitcher.core

import java.util.*


interface PluginRegistry<T : Plugin<S>, S> : Iterable<T> {
    companion object {
        fun <S, T : Plugin<S>> empty(): PluginRegistry<T, S> {
            return of(emptyList())
        }

        // region fun of
        fun <S, T : Plugin<S>> of(comparator: Comparator<in T>): SimplePluginRegistry<T, S> {
            return of(emptyList(), comparator);
        }

        fun <S, T : Plugin<S>> of(vararg plugins: T): SimplePluginRegistry<T, S> {
            val comparator: Comparator<in T> = OrderAwarePluginRegistry.DEFAULT_COMPARATOR
            return of(plugins.asList(), comparator)
        }

        fun <S, T : Plugin<S>> of(plugins: List<T>): SimplePluginRegistry<T, S> {
            return of(plugins, OrderAwarePluginRegistry.DEFAULT_COMPARATOR);
        }

        fun <S, T : Plugin<S>> of(plugins: List<T>, comparator: Comparator<in T>): SimplePluginRegistry<T, S> {
            return OrderAwarePluginRegistry.of(plugins, comparator);
        }
        // endregion
    }

    // region getRequiredPluginFor

    /**
     * Retrieves a required plugin for the given delimiter.
     *
     * @param delimiter the delimiter identifying the originating system. Must not be {@literal null}.
     * @return a {@link Plugin} for the given originating system or {@literal null} if none found.
     * @throws IllegalArgumentException if no {@link Plugin} is found for the given delimiter.
     */
    @Throws(IllegalArgumentException::class)
    fun getRequiredPluginFor(delimiter: S): T?

    /**
     * Retrieves a required plugin for the given delimiter with a custom exception message supplier.
     *
     * @param delimiter the delimiter identifying the originating system. Must not be {@literal null}.
     * @param message a {@link Supplier} to produce an exception message if no plugin is found.
     * @return a {@link Plugin} for the given originating system or {@literal null} if none found.
     * @throws IllegalArgumentException if no {@link Plugin} is found for the given delimiter.
     */
    @Throws(java.lang.IllegalArgumentException::class)
    fun getRequiredPluginFor(delimiter: S, message: () -> String): T?
    // endregion

    // region getPluginsFor

    /**
     * Returns all plugins for the given delimiter.
     *
     * @param delimiter must not be {@literal null}.
     * @return a list of plugins or an empty list if none found
     */
    fun getPluginsFor(delimiter: S): List<T?>?

    @Throws(Exception::class)
    fun <E : Exception> getPluginsFor(delimiter: S, ex: () -> E): List<T>


    fun getPluginsFor(delimiter: S, plugins: List<T>): List<T>
    // endregion

    // region getPluginFor
    fun getPluginFor(delimiter: S): T?

    @Throws(Exception::class)
    fun <E : Exception> getPluginFor(delimiter: S, ex: () -> E): T
    // endregion

    // region getPluginOrDefaultFor
    fun getPluginOrDefaultFor(delimiter: S, defaultSupplier: () -> T): T

    fun getPluginOrDefaultFor(delimiter: S, plugin: T): T
    // endregion

    fun countPlugins(): Int

    fun contains(plugin: T): Boolean

    fun hasPluginFor(delimiter: S): Boolean

    fun getPlugins(): List<T>
}
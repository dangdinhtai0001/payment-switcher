package vn.eclipse.pluginswitcher.core

interface Plugin<S> {
    /**
     * Returns if a plugin should be invoked according to the given delimiter.
     *
     * @param delimiter must not be null.
     * @return if the plugin should be invoked
     */
    fun supports(delimiter: S): Boolean
}
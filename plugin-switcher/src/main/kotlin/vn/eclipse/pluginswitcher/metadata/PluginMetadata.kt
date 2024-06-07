package vn.eclipse.pluginswitcher.metadata

interface PluginMetadata {

    /**
     * Returns a unique plugin name. Plugins returning a metadata implementation have to ensure uniqueness of this name.
     *
     * @return the name of the plugin
     */
    fun getName(): String

    /**
     * Returns the plugin version. This allows rudimentary versioning possibilities.
     *
     * @return the version of the plugin
     */
    fun getVersion(): String
}
package vn.eclipse.pluginswitcher.metadata

interface MetadataProvider {
    /**
     * Returns the plugins metadata.
     *
     * @return the plugins metadata
     */
    fun getMetadata(): PluginMetadata?
}
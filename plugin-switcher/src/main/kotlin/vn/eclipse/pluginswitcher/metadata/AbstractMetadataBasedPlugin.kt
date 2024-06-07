package vn.eclipse.pluginswitcher.metadata

import vn.eclipse.pluginswitcher.core.Plugin

/**
 * Abstract base class for plugins based on [PluginMetadata]. Plugins based on this class can be selected from the
 * [PluginRegistry] via an instance of [PluginMetadata]. Therefore you can regard this as a role model
 * implementation of a base class for certain delimiter implementations.
 *
 * @author Oliver Gierke
 */
abstract class AbstractMetadataBasedPlugin(name: String, version: String) : Plugin<PluginMetadata>, MetadataProvider {

    private val metadata: PluginMetadata = SimplePluginMetadata(name, version)

    /**
     * Checks if the plugin supports the given [PluginMetadata].
     *
     * @param delimiter the plugin metadata to check
     * @return true if the plugin supports the given metadata, false otherwise
     */
    override fun supports(delimiter: PluginMetadata): Boolean {
        return metadata == delimiter
    }

    /**
     * Returns the plugin metadata.
     *
     * @return the plugin metadata
     */
    override fun getMetadata(): PluginMetadata {
        return metadata
    }
}
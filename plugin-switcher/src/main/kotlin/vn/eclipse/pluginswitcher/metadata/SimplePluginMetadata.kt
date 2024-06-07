package vn.eclipse.pluginswitcher.metadata

/**
 * Value object style implementation of [PluginMetadata].
 *
 */
class SimplePluginMetadata(name: String, version: String) : PluginMetadata {

    private val name: String
    private val version: String

    init {
        require(name.isNotEmpty()) { "Name must not be null or empty!" }
        require(version.isNotEmpty()) { "Version must not be null or empty!" }
        this.name = name
        this.version = version
    }

    /**
     * Returns the name of the plugin.
     *
     * @return the name of the plugin
     */
    override fun getName(): String {
        return name
    }

    /**
     * Returns the version of the plugin.
     *
     * @return the version of the plugin
     */
    override fun getVersion(): String {
        return version
    }

    override fun toString(): String {
        return "$name:$version"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PluginMetadata) return false

        return name == other.getName() && version == other.getVersion()
    }

    override fun hashCode(): Int {
        return name.hashCode() * 31 + version.hashCode()
    }
}

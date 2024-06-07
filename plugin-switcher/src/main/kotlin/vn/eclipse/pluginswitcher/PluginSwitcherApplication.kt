package vn.eclipse.pluginswitcher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PluginSwitcherApplication

fun main(args: Array<String>) {
    runApplication<PluginSwitcherApplication>(*args)
}

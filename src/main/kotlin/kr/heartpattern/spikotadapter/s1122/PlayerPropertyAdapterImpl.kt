package kr.heartpattern.spikotadapter.s1122

import kr.heartpattern.spikot.adapter.Adapter
import kr.heartpattern.spikot.adapter.SupportedVersion
import kr.heartpattern.spikot.adapters.PlayerPropertyAdapter
import kr.heartpattern.spikot.misc.MutableProperty
import kr.heartpattern.spikot.misc.MutablePropertyMap
import kr.heartpattern.spikot.misc.Property
import kr.heartpattern.spikot.module.AbstractModule
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.*
import kotlin.collections.HashMap

@Adapter
@SupportedVersion(version = "all")
class PlayerPropertyAdapterImpl : AbstractModule(), PlayerPropertyAdapter {
    private val map = HashMap<UUID, MutablePropertyMap>()

    override fun contains(player: Player, property: Property<*>): Boolean {
        return property in map[player.uniqueId]!!
    }

    override fun <T> get(player: Player, property: Property<T>): T {
        return map[player.uniqueId]!![property]!!
    }

    override fun <T> remove(player: Player, property: MutableProperty<T>): T? {
        val old = map[player.uniqueId]!![property]
        map[player.uniqueId]!![property] = null
        return old
    }

    override fun <T> set(player: Player, property: MutableProperty<T>, value: T?) {
        map[player.uniqueId]!![property] = value
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun PlayerJoinEvent.onPlayerJoin() {
        map[player.uniqueId] = MutablePropertyMap()
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun PlayerQuitEvent.onPlayerQuit() {
        map.remove(player.uniqueId)
    }
}
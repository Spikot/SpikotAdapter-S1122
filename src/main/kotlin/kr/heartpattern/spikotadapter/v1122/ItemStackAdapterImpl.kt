package kr.heartpattern.spikotadapter.v1122

import kr.heartpattern.spikot.adapter.Adapter
import kr.heartpattern.spikot.adapter.SupportedVersion
import kr.heartpattern.spikot.adapters.ItemStackAdapter
import kr.heartpattern.spikot.adapters.NBTAdapter
import kr.heartpattern.spikot.module.AbstractModule
import kr.heartpattern.spikot.nbt.WrapperNBTCompound
import net.minecraft.server.v1_12_R1.ItemStack
import net.minecraft.server.v1_12_R1.NBTTagCompound
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack

@Adapter
@SupportedVersion("all")
class ItemStackAdapterImpl : AbstractModule(), ItemStackAdapter {
    private companion object {
        val handleField = CraftItemStack::class.java.getDeclaredField("handle")
    }

    private val org.bukkit.inventory.ItemStack.tag: NBTTagCompound?
        get() = if (this is CraftItemStack) (handleField.get(this) as ItemStack?)?.tag else null

    override fun getWrappedTag(itemStack: org.bukkit.inventory.ItemStack): WrapperNBTCompound? {
        return itemStack.tag?.let { NBTAdapter.wrapNBTCompound(it) }
    }

    override fun hasTag(itemStack: org.bukkit.inventory.ItemStack): Boolean {
        return itemStack.tag != null
    }

    override fun isCraftItemStack(itemStack: org.bukkit.inventory.ItemStack): Boolean {
        return itemStack is CraftItemStack
    }

    override fun toCraftItemStack(itemStack: org.bukkit.inventory.ItemStack): org.bukkit.inventory.ItemStack {
        return if (itemStack is CraftItemStack) itemStack
        else CraftItemStack.asCraftMirror(CraftItemStack.asNMSCopy(itemStack))
    }
}
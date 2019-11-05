package kr.heartpattern.spikotadapter.v1122

import kr.heartpattern.spikot.adapter.Adapter
import kr.heartpattern.spikot.adapter.SupportedVersion
import kr.heartpattern.spikot.adapters.NBTAdapter
import kr.heartpattern.spikot.module.AbstractModule
import kr.heartpattern.spikot.nbt.*
import net.minecraft.server.v1_12_R1.*
import java.io.ByteArrayOutputStream

@Adapter
@SupportedVersion(version = "all")
class NBTAdapterImpl : AbstractModule(), NBTAdapter {
    private val nbtTagEnd: NBTTagEnd

    init {
        val constructor = NBTTagEnd::class.java.getConstructor()
        constructor.isAccessible = true
        nbtTagEnd = constructor.newInstance()
        constructor.isAccessible = false
    }

    override fun compressNBT(nbt: WrapperNBTCompound): ByteArray {
        val outputStream = ByteArrayOutputStream()
        NBTCompressedStreamTools.a(nbt.tag as NBTTagCompound, outputStream)
        return outputStream.toByteArray()
    }

    override fun decompressNBT(array: ByteArray): WrapperNBTCompound {
        val inputStream = array.inputStream()
        val nbt = NBTCompressedStreamTools.a(inputStream)
        return wrapNBTCompound(nbt)
    }

    override fun createNBTByte(value: Byte): WrapperNBTByte = WrapperNBTByteImpl(value)

    override fun createNBTByteArray(value: ByteArray): WrapperNBTByteArray = WrapperNBTByteArrayImpl(value)

    override fun createNBTDouble(value: Double): WrapperNBTDouble = WrapperNBTDoubleImpl(value)

    override fun createNBTEnd(value: Unit): WrapperNBTEnd = WrapperNBTEndImpl()

    override fun createNBTFloat(value: Float): WrapperNBTFloat = WrapperNBTFloatImpl(value)

    override fun createNBTInt(value: Int): WrapperNBTInt = WrapperNBTIntImpl(value)

    override fun createNBTIntArray(value: IntArray): WrapperNBTIntArray = WrapperNBTIntArrayImpl(value)

    override fun createNBTLong(value: Long): WrapperNBTLong = WrapperNBTLongImpl(value)

    override fun createNBTLongArray(value: LongArray): WrapperNBTLongArray = WrapperNBTLongArrayImpl(value)

    override fun createNBTShort(value: Short): WrapperNBTShort = WrapperNBTShortImpl(value)

    override fun createNBTString(value: String): WrapperNBTString = WrapperNBTStringImpl(value)

    override fun <W : WrapperNBTBase<*>> createNBTList(value: List<W>): WrapperNBTList<W> = WrapperNBTListImpl(value)

    override fun createNBTCompound(value: Map<String, WrapperNBTBase<*>>): WrapperNBTCompound =
        WrapperNBTCompoundImpl(value)

    override fun getType(tag: Any): TagType<*> {
        return TagType.ofId((tag as NBTBase).typeId.toInt())
    }

    override fun wrapNBTByte(tag: Any): WrapperNBTByte = WrapperNBTByteImpl(tag as NBTTagByte)

    override fun wrapNBTByteArray(tag: Any): WrapperNBTByteArray = WrapperNBTByteArrayImpl(tag as NBTTagByteArray)

    override fun wrapNBTCompound(tag: Any): WrapperNBTCompound = WrapperNBTCompoundImpl(tag as NBTTagCompound)

    override fun wrapNBTDouble(tag: Any): WrapperNBTDouble = WrapperNBTDoubleImpl(tag as NBTTagDouble)

    override fun wrapNBTEnd(tag: Any): WrapperNBTEnd = WrapperNBTEndImpl()

    override fun wrapNBTFloat(tag: Any): WrapperNBTFloat = WrapperNBTFloatImpl(tag as NBTTagFloat)

    override fun wrapNBTInt(tag: Any): WrapperNBTInt = WrapperNBTIntImpl(tag as NBTTagInt)

    override fun wrapNBTIntArray(tag: Any): WrapperNBTIntArray = WrapperNBTIntArrayImpl(tag as NBTTagIntArray)

    override fun <W : WrapperNBTBase<*>> wrapNBTList(tag: Any): WrapperNBTList<W> =
        WrapperNBTListImpl(tag as NBTTagList)

    override fun wrapNBTLong(tag: Any): WrapperNBTLong = WrapperNBTLongImpl(tag as NBTTagLong)

    override fun wrapNBTLongArray(tag: Any): WrapperNBTLongArray = WrapperNBTLongArrayImpl(tag as NBTTagLongArray)

    override fun wrapNBTShort(tag: Any): WrapperNBTShort = WrapperNBTShortImpl(tag as NBTTagShort)

    override fun wrapNBTString(tag: Any): WrapperNBTString = WrapperNBTStringImpl(tag as NBTTagString)
}
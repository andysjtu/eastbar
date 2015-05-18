package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/18.
 */
public class EntryKey {
    private final short recMessageId;
    private final short recMessageType;

    public EntryKey(short recMessageId, short recMessageType) {
        this.recMessageId = recMessageId;
        this.recMessageType = recMessageType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntryKey entryKey = (EntryKey) o;

        if (recMessageId != entryKey.recMessageId) return false;
        return recMessageType == entryKey.recMessageType;

    }

    @Override
    public int hashCode() {
        int result = (int) recMessageId;
        result = 31 * result + (int) recMessageType;
        return result;
    }

    @Override
    public String toString() {
        return "EntryKey{" +
                "recMessageId=" + recMessageId +
                ", recMessageType=" + recMessageType +
                '}';
    }
}

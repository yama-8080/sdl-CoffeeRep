package ymz.coffeerep.data.raw_beans;

import androidx.room.Entity;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@Entity(tableName="rawbeans")
public class RawBeans {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rawbeans_id")
    private int rawbeans_id = 0;

    @ColumnInfo(name = "rawbeans_name")
    private String rawbeans_name;

    @ColumnInfo(name = "registered_time")
    private long registered_time;

    public RawBeans(String rawbeans_name, long registered_time) {
        this.rawbeans_name = rawbeans_name;
        this.registered_time = registered_time;
    }

    public int getRawBeans_id() { return rawbeans_id; }
    public void setRawBeans_id(int id) { this.rawbeans_id = id; }

    public String getRawBeans_name() { return rawbeans_name; }
    public void setUser_name(String name) { this.rawbeans_name = name; }

    public long getUser_pass() { return registered_time; }
    public void setUser_pass(long time) { this.registered_time = time; }
}

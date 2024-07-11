package ymz.coffeerep.data.raw_beans;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

@Entity(tableName="rawbeans")
public class RawBeans {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "rawbeans_id")
    int rawbeans_id = 0;

    @ColumnInfo(name = "rawbeans_name")
    @NonNull
    String rawbeans_name;

    @ColumnInfo(name = "rawbeans_country")
    String rawbeans_country;

    @ColumnInfo(name = "registered_time")
    long registered_time;

    //constructor
    public RawBeans(String rawbeans_name, String rawbeans_country, long registered_time) {
        this.rawbeans_name = rawbeans_name;
        this.rawbeans_country = rawbeans_country;
        this.registered_time = registered_time;
    }

    //getters and setters below
    @NonNull
    public int getRawBeansId() { return rawbeans_id; }
    public void setRawBeansId(int id) { this.rawbeans_id = id; }

    @NonNull
    public String getRawBeansName() { return rawbeans_name; }
    public void setRawBeansName(String name) { this.rawbeans_name = name; }

    public String getRawBeansCountry() { return rawbeans_country; }
    public void setRawBeansCountry(String country) { this.rawbeans_country = country; }

    public long getRegisteredTime() { return registered_time; }
    public void setRegisteredTime(long time) { this.registered_time = time; }
}

package ymz.coffeerep.data.rawbeans;

import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName="rawbeans")
public class RawBeans implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_id")
    int rawbeans_id = 0;

    @ColumnInfo(name = "rawbeans_name")
    @NonNull
    @Getter
    @Setter
    String rawbeans_name;

    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_country")
    String rawbeans_country;

    @Getter
    @Setter
    @ColumnInfo(name = "registered_time")
    long registered_time;

    //constructor
    public RawBeans(String rawbeans_name, String rawbeans_country, long registered_time) {
        this.rawbeans_name = rawbeans_name;
        this.rawbeans_country = rawbeans_country;
        this.registered_time = registered_time;
    }
}

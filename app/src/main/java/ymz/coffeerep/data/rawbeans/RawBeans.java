package ymz.coffeerep.data.rawbeans;

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
}

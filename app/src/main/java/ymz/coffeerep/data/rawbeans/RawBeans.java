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

    //登録日時
    @Getter
    @Setter
    @ColumnInfo(name = "registered_time")
    long registered_time;

    //名前
    @ColumnInfo(name = "rawbeans_name")
    @NonNull
    @Getter
    @Setter
    String rawbeans_name;

    //写真１
    @ColumnInfo(name = "rawbeans_picture1")
    @Getter
    @Setter
    String rawbeans_picture1;

    //写真２
    @ColumnInfo(name = "rawbeans_picture2")
    @Getter
    @Setter
    String rawbeans_picture2;

    //写真３
    @ColumnInfo(name = "rawbeans_picture3")
    @Getter
    @Setter
    String rawbeans_picture3;

    //購入日
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_purchased_date")
    String rawbeans_purchased_date;

    //購入店
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_purchased_shop")
    String rawbeans_purchased_shop;

    //内容量（単位[g]）
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_amount")
    int rawbeans_amount;

    //原産国
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_country")
    String rawbeans_country;

    //原産地
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_place")
    String rawbeans_place;

    //農園
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_farm")
    String rawbeans_farm;

    //品種
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_variety")
    String rawbeans_variety;

    //精製方法
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_process")
    String rawbeans_process;

    //カフェインレス
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_caffeineless")
    boolean rawbeans_caffeineless;

    //総合評価
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_review")
    int rawbeans_review;

    //メモ
    @Getter
    @Setter
    @ColumnInfo(name = "rawbeans_memo")
    String rawbeans_memo;
}

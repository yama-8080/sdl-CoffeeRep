package ymz.coffeerep.data.roastbeans;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName="roastbeans")
public class RoastBeans implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_id")
    int roastbeans_id = 0;

    //登録日時
    @Getter
    @Setter
    @ColumnInfo(name = "registered_time")
    long registered_time;

    //名前
    @ColumnInfo(name = "roastbeans_name")
    @NonNull
    @Getter
    @Setter
    String roastbeans_name;

    //写真１
    @ColumnInfo(name = "roastbeans_picture1")
    @Getter
    @Setter
    String roastbeans_picture1;

    //写真２
    @ColumnInfo(name = "roastbeans_picture2")
    @Getter
    @Setter
    String roastbeans_picture2;

    //写真３
    @ColumnInfo(name = "roastbeans_picture3")
    @Getter
    @Setter
    String roastbeans_picture3;

    //購入日
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_purchased_date")
    String roastbeans_purchased_date;

    //購入店
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_purchased_shop")
    String roastbeans_purchased_shop;

    //内容量（単位[g]）
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_amount")
    int roastbeans_amount;

    //原産国
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_country")
    String roastbeans_country;

    //原産地
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_place")
    String roastbeans_place;

    //農園
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_farm")
    String roastbeans_farm;

    //品種
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_variety")
    int roastbeans_variety;

    //精製方法
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_process")
    int roastbeans_process;

    //カフェインレス
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_caffeineless")
    Boolean roastbeans_caffeineless;

    //配合
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_brend")
    int roastbeans_brend;

    //自家焙煎
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_self_roast")
    Boolean roastbeans_self_roast;

    //焙煎した生豆
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_roast_rawbeans_id")
    int roastbeans_roast_rawbeans_id;

    //焙煎度
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_roast_level")
    int roastbeans_roast_level;

    //総合評価
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_review")
    int roastbeans_review;

    //メモ
    @Getter
    @Setter
    @ColumnInfo(name = "roastbeans_memo")
    String roastbeans_memo;
}

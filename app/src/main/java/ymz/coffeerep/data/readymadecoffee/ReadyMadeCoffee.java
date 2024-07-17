package ymz.coffeerep.data.readymadecoffee;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName="readymadecoffee")
public class ReadyMadeCoffee implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_id")
    int readymadecoffee_id = 0;

    //登録日時
    @Getter
    @Setter
    @ColumnInfo(name = "registered_time")
    long registered_time;

    //名前
    @ColumnInfo(name = "readymadecoffee_name")
    @NonNull
    @Getter
    @Setter
    String readymadecoffee_name;

    //写真１
    @ColumnInfo(name = "readymadecoffee_picture1")
    @Getter
    @Setter
    String readymadecoffee_picture1;

    //写真２
    @ColumnInfo(name = "readymadecoffee_picture2")
    @Getter
    @Setter
    String readymadecoffee_picture2;

    //写真３
    @ColumnInfo(name = "readymadecoffee_picture3")
    @Getter
    @Setter
    String readymadecoffee_picture3;

    //飲んだ日時
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_drink_date")
    String readymadecoffee_drink_date;

    //飲んだ場所
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_drink_place")
    String readymadecoffee_drink_place;

    //飲んだ量[mL]
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_drink_amount")
    int readymadecoffee_drink_amount;

    //価格[円]
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_price")
    int readymadecoffee_price;

    //飲み方-温度
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_drink_method_temperature")
    int readymadecoffee_drink_method_temperature;

    //飲み方-種類
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_drink_method_type")
    int readymadecoffee_drink_method_type;

    //甘味
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_taste_sweet")
    int readymadecoffee_taste_sweet;

    //苦味
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_taste_bitter")
    int readymadecoffee_taste_bitter;

    //酸味
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_taste_sour")
    int readymadecoffee_taste_sour;

    //香り
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_taste_scent")
    int readymadecoffee_taste_scent;

    //ボディ
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_taste_body")
    int readymadecoffee_taste_body;

    //コク
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_taste_rich")
    int readymadecoffee_taste_rich;

    //フレーバー
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_flavor")
    int readymadecoffee_flavor;

    //相性の良い食べ物
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_match_food")
    int readymadecoffee_match_food;

    //雰囲気
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_atmosphere")
    int readymadecoffee_atmosphere;

    //カフェインレス
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_caffeineless")
    Boolean readymadecoffee_caffeineless;

    //総合評価
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_review")
    int readymadecoffee_review;

    //メモ
    @Getter
    @Setter
    @ColumnInfo(name = "readymadecoffee_memo")
    String readymadecoffee_memo;
}

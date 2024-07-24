package ymz.coffeerep.data.selfdripcoffee;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName="selfdripcoffee")
public class SelfDripCoffee implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_id")
    int selfdripcoffee_id = 0;

    //登録日時
    @Getter
    @Setter
    @ColumnInfo(name = "registered_time")
    long registered_time;

    //名前
    @ColumnInfo(name = "selfdripcoffee_name")
    @NonNull
    @Getter
    @Setter
    String selfdripcoffee_name;

    //写真１
    @ColumnInfo(name = "selfdripcoffee_picture1")
    @Getter
    @Setter
    String selfdripcoffee_picture1;

    //写真２
    @ColumnInfo(name = "selfdripcoffee_picture2")
    @Getter
    @Setter
    String selfdripcoffee_picture2;

    //写真３
    @ColumnInfo(name = "selfdripcoffee_picture3")
    @Getter
    @Setter
    String selfdripcoffee_picture3;

    //飲んだ日時
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_drink_date")
    String selfdripcoffee_drink_date;

    //飲んだ場所
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_drink_place")
    String selfdripcoffee_drink_place;

    //飲んだ量[mL]
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_drink_amount")
    int selfdripcoffee_drink_amount;

    //価格[円]
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_price")
    int selfdripcoffee_price;

    //焙煎豆
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_roastbeans")
    int selfdripcoffee_roastbeans;

    //挽き目
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_grind_level")
    int selfdripcoffee_grind_level;

    //抽出方法
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_dripmethod")
    int selfdripcoffee_dripmethod;

    //飲み方-温度
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_drink_method_temperature")
    int selfdripcoffee_drink_method_temperature;

    //飲み方-種類
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_drink_method_type")
    int selfdripcoffee_drink_method_type;

    //甘味
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_taste_sweet")
    int selfdripcoffee_taste_sweet;

    //苦味
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_taste_bitter")
    int selfdripcoffee_taste_bitter;

    //酸味
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_taste_sour")
    int selfdripcoffee_taste_sour;

    //香り
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_taste_scent")
    int selfdripcoffee_taste_scent;

    //ボディ
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_taste_body")
    int selfdripcoffee_taste_body;

    //コク
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_taste_rich")
    int selfdripcoffee_taste_rich;

    //フレーバー
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_flavor")
    int selfdripcoffee_flavor;

    //相性の良い食べ物
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_match_food")
    int selfdripcoffee_match_food;

    //雰囲気
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_atmosphere")
    int selfdripcoffee_atmosphere;

    //カフェインレス
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_caffeineless")
    Boolean selfdripcoffee_caffeineless;

    //総合評価
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_review")
    int selfdripcoffee_review;

    //メモ
    @Getter
    @Setter
    @ColumnInfo(name = "selfdripcoffee_memo")
    String selfdripcoffee_memo;
}

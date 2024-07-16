package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class DrinkMethodTypeItem {
    public enum DrinkMethodType{
        DEFAULT(0, "-"),
        BLACK(1, "ブラック"),
        ESPRESSO(2, "エスプレッソ"),
        AMERICANO(3, "アメリカーノ"),
        CAFEAULAIT(4, "カフェオレ"),
        CAFELATTE(5, "カフェラテ"),
        FLATWHITE(6, "フラットホワイト"),
        MACCHIATO(7, "マキアート"),
        CAPPUCCINO(8, "カプチーノ"),
        CAFEMOCHA(9, "カフェモカ"),
        CARAMEL(10, "キャラメル"),
        VIENNA(11, "ウィンナー"),
        CONPANNA(12, "エスプレッソコンパナ"),
        FLOAT(13, "フロート"),
        DALGONA(14, "ダルゴナコーヒー"),
        RUSSIAN(15, "ルシアンコーヒー"),
        VIETNAM(16, "ベトナムコーヒー"),
        TURKISH(17, "トルココーヒー"),
        OTHER(18, "その他"),
        ;

        private final int id;
        private final String text;

        private DrinkMethodType(final int id, final String text) {
            this.id = id;
            this.text = text;
        }

        public int getInt() {
            return this.id;
        }

        public String getString() {
            return this.text;
        }

        //search items by id
        public static DrinkMethodTypeItem.DrinkMethodType getType(final int id) {
            DrinkMethodTypeItem.DrinkMethodType[] items = DrinkMethodTypeItem.DrinkMethodType.values();
            for (DrinkMethodTypeItem.DrinkMethodType item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return DrinkMethodTypeItem.DrinkMethodType.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = DrinkMethodTypeItem.DrinkMethodType.getNumber();
        for(int i=0; i<loop; i++){
            if(DrinkMethodTypeItem.DrinkMethodType.getType(i) != null)
                adapter.add(DrinkMethodTypeItem.DrinkMethodType.getType(i).getString());
        }
    }
}

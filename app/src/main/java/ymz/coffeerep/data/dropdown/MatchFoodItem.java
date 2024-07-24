package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class MatchFoodItem {

    public enum MatchFood{
        DEFAULT(0, "-"),
        BANANA(1, "バナナ"),
        STRAWBERRY(2, "いちご"),
        KIWI(3, "キウイ"),
        APPLE(4, "りんご"),
        PINEAPPLE(5, "パイナップル"),
        CHOCO(6, "チョコ"),
        NUTS(7, "ナッツ類"),
        COOKIE(8, "クッキー"),
        POUNDCAKE(9, "パウンドケーキ"),
        MUFFIN(10, "マフィン"),
        WAFFLE(11, "ワッフル"),
        TART(12, "タルト"),
        GALETTE(13, "ガレット"),
        LEMONCAKE(14, "レモンケーキ"),
        CHEESECAKE(15, "チーズケーキ"),
        SANDWICH(16, "サンドイッチ"),
        TOAST(17, "トースト"),
        BAGEL(18, "ベーグル"),
        NEAPOLITAN(19, "ナポリタン"),
        CAMEMBERT(20, "カマンベールチーズ"),
        TOMATO(21, "トマト"),
        OTHER(22, "その他"),
        ;

        private final int id;
        private final String text;

        private MatchFood(final int id, final String text) {
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
        public static MatchFoodItem.MatchFood getType(final int id) {
            MatchFoodItem.MatchFood[] items = MatchFoodItem.MatchFood.values();
            for (MatchFoodItem.MatchFood item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return MatchFoodItem.MatchFood.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = MatchFoodItem.MatchFood.getNumber();
        for(int i=0; i<loop; i++){
            if(MatchFoodItem.MatchFood.getType(i) != null)
                adapter.add(MatchFoodItem.MatchFood.getType(i).getString());
        }
    }
}


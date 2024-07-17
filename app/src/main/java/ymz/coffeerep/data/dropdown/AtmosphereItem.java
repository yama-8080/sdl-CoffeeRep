package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class AtmosphereItem {

    public enum Atmosphere{
        DEFAULT(0, "-"),
        RELAXING(1, "落ち着く"),
        EXCITING(2, "刺激的"),
        CONCENTRATING(3, "集中が高まる"),
        CREATIVE(4, "クリエイティブ"),
        NOVELTY(5, "斬新"),
        DIFFERENTCULTURAL(6, "異文化的"),
        CLASSIC(7, "昔ながらの"),
        JUNKIE(8, "ジャンキー"),
        FRIENDLY(9, "フレンドリー"),
        NOBLE(10, "気高い"),
        ELEGANT(11, "優雅"),
        ADULT(12, "アダルト"),
        UNSOPHISTICATED(13, "泥臭い"),
        NATURAL(14, "自然体"),
        HISTORICAL(15, "歴史深い"),
        MYSTERIOUS(16, "謎の多い"),
        ODD(17, "奇妙"),
        OTHER(18, "その他"),
        ;

        private final int id;
        private final String text;

        private Atmosphere(final int id, final String text) {
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
        public static AtmosphereItem.Atmosphere getType(final int id) {
            AtmosphereItem.Atmosphere[] items = AtmosphereItem.Atmosphere.values();
            for (AtmosphereItem.Atmosphere item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return AtmosphereItem.Atmosphere.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = AtmosphereItem.Atmosphere.getNumber();
        for(int i=0; i<loop; i++){
            if(AtmosphereItem.Atmosphere.getType(i) != null)
                adapter.add(AtmosphereItem.Atmosphere.getType(i).getString());
        }
    }
}

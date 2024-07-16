package ymz.coffeerep.data.dropdown;

import android.widget.ArrayAdapter;

public class DrinkMethodTemperatureItem {
    public enum DrinkMethodTemperature{
        DEFAULT(0, "-"),
        HOT(1, "ホット"),
        ICED(2, "アイス"),
        ;

        private final int id;
        private final String text;

        private DrinkMethodTemperature(final int id, final String text) {
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
        public static DrinkMethodTemperatureItem.DrinkMethodTemperature getType(final int id) {
            DrinkMethodTemperatureItem.DrinkMethodTemperature[] items = DrinkMethodTemperatureItem.DrinkMethodTemperature.values();
            for (DrinkMethodTemperatureItem.DrinkMethodTemperature item : items) {
                if (item.getInt() == id) {
                    return item;
                }
            }
            return null;
        }

        //return the number of items
        public static int getNumber(){
            return DrinkMethodTemperatureItem.DrinkMethodTemperature.values().length;
        }
    }

    //set items to dropdown list
    public static void setItemsToAdapter(ArrayAdapter<String> adapter){
        int loop = DrinkMethodTemperatureItem.DrinkMethodTemperature.getNumber();
        for(int i=0; i<loop; i++){
            if(DrinkMethodTemperatureItem.DrinkMethodTemperature.getType(i) != null)
                adapter.add(DrinkMethodTemperatureItem.DrinkMethodTemperature.getType(i).getString());
        }
    }
}
